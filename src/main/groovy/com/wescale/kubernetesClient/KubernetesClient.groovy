package com.wescale.kubernetesClient

import com.wescale.kubernetesClient.resources.pods.Pod
import groovy.json.JsonSlurper
import groovy.transform.stc.ClosureParams
import groovy.transform.stc.FromString
import groovy.transform.stc.SimpleType
import wslite.json.JSONObject
import wslite.rest.RESTClient

/**
 * Created by cedric on 23/09/2015.
 */
@ToMapIgnore
class KubernetesClient {
    public String endpoint
    public String namespace = 'default'

    static KubernetesClient create(String endpoint) {
        new KubernetesClient(endpoint)
    }

    KubernetesClient(String endpoint){
        this.endpoint = endpoint
    }

    KubernetesClient namespace(String name = 'default'){
        this.namespace = name
        this
    }

    List<Pod> pods(){
        callOnNamespace().get(path: '/pods').json.items.collect { item ->
            Pod pod = item as Pod
            pod.client = this
            pod
        }
    }

    Pod pod(Pod pod = new Pod()){
        pod.client = this
        pod
    }

    RESTClient call() {
        new RESTClient("$endpoint/api/v1")
    }

    RESTClient callOnNamespace(namespace = this.namespace) {
        new RESTClient("$endpoint/api/v1/namespaces/$namespace")
    }

}
