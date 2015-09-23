package com.wescale.kubernetesClient

import com.wescale.kubernetesClient.resources.pods.Pod
import com.wescale.kubernetesClient.resources.replicationcontrollers.ReplicationController
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

    RESTClient call() {
        new RESTClient("$endpoint/api/v1")
    }

    RESTClient callOnNamespace(namespace = this.namespace) {
        new RESTClient("$endpoint/api/v1/namespaces/$namespace")
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

    Pod pod(String name){
        Pod pod = callOnNamespace().get(path: "/pods/$name").json
        pod.client = this
        pod
    }

    List<ReplicationController> replicationControllers(){
        callOnNamespace().get(path: '/replicationcontrollers').json.items.collect { item ->
            ReplicationController replicationController = item as ReplicationController
            replicationController.client = this
            replicationController
        }
    }

    ReplicationController replicationController(ReplicationController replicationController = new ReplicationController()){
        replicationController.client = this
        replicationController
    }

    ReplicationController replicationController(String name){
        ReplicationController replicationController = callOnNamespace().get(path: "/replicationcontrollers/$name").json
        replicationController.client = this
        replicationController
    }
}
