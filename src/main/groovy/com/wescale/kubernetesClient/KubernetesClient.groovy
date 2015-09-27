package com.wescale.kubernetesClient

import com.wescale.kubernetesClient.resources.bindings.Binding
import com.wescale.kubernetesClient.resources.componentStatuses.ComponentStatus
import com.wescale.kubernetesClient.resources.nodes.Node
import com.wescale.kubernetesClient.resources.pods.Pod
import com.wescale.kubernetesClient.resources.replicationcontrollers.ReplicationController
import com.wescale.kubernetesClient.resources.services.Service
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

    RESTClient call() {
        new RESTClient("$endpoint/api/v1")
    }

    RESTClient callOnNamespace(namespace = this.namespace) {
        new RESTClient("$endpoint/api/v1/namespaces/$namespace")
    }

    List<Pod> pods(){
        callOnNamespace().get(path: '/pods').json.items.collect { item ->
            Pod pod = new Pod(item as Map)
            pod.client = this
            pod
        }
    }

    Pod pod(Pod pod = new Pod()){
        pod.client = this
        pod
    }

    Pod pod(String name){
        Pod pod = new Pod(callOnNamespace().get(path: "/pods/$name").json as Map)
        pod.client = this
        pod
    }

    List<ReplicationController> replicationControllers(){
        callOnNamespace().get(path: '/replicationcontrollers').json.items.collect { item ->
            ReplicationController replicationController = new ReplicationController(item as Map)
            replicationController.client = this
            replicationController
        }
    }

    ReplicationController replicationController(ReplicationController replicationController = new ReplicationController()){
        replicationController.client = this
        replicationController
    }

    ReplicationController replicationController(String name){
        ReplicationController replicationController = new ReplicationController(callOnNamespace().get(path: "/replicationcontrollers/$name").json as Map)
        replicationController.client = this
        replicationController
    }

    List<Service> services(){
        callOnNamespace().get(path: '/services').json.items.collect { item ->
            Service service = new Service(item as Map)
            service.client = this
            service
        }
    }

    Service service(Service service = new Service()){
        service.client = this
        service
    }

    Service service(String name){
        Service service = new Service(callOnNamespace().get(path: "/services/$name").json as Map)
        service.client = this
        service
    }

    Binding binding(Binding binding = new Binding()) {
        binding.client = this
        binding
    }

    List<ComponentStatus> componentStatuses(){
        callOnNamespace().get(path: '/componentstatuses').json.items.collect { item ->
            new ComponentStatus(item as Map)
        }
    }

    ReplicationController componentStatuse(String name){
        callOnNamespace().get(path: "/componentstatuses/$name").json
    }

    List<Node> nodes() {
        call().get(path: '/nodes').json.items.collect { item ->
            Node node = new Node(item as Map)
            node.client = this
            node
        }
    }

    Node node(Node node = new Node()) {
        node.client = this
        node
    }

    Node node(String name) {
        Node node = new Node(call().get(path: "/nodes/$name").json as Map)
        node.client = this
        node
    }


}
