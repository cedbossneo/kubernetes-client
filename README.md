# kubernetes-client
Fluent Kubernetes Client

## To create a pod :

```groovy
def client = KubernetesClient.create('http://localhost:8080')
client.pod()
        .assignMetadata(new Metadata(name: 'test'))
        .assignSpec(new PodSpec(containers: [new Container(name: 'testcontainer', image: 'nginx')]))
        .create()
```

## To List pods :

```groovy
def client = KubernetesClient.create('http://localhost:8080')
client.pods()
```

## To get a pod :

```groovy
def client = KubernetesClient.create('http://localhost:8080')
client.pod('test')
```

## To delete a pod :

```groovy
def client = KubernetesClient.create('http://localhost:8080')
client.pod('test').delete()
```


## To update a pod :

```groovy
def client = KubernetesClient.create('http://localhost:8080')
client.pod('test').update { pod ->
            pod.metadata.annotations.toto = 'tata'
}
```

## To update a pod in a namespace :

```groovy
def client = KubernetesClient.create('http://localhost:8080')
client.namespace('huhu').pod('test').update { pod ->
            pod.metadata.annotations.toto = 'tata'
}
```
