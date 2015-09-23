package com.wescale.kubernetesClient

import groovy.json.JsonSlurper

/**
 * Created by cedric on 23/09/2015.
 */
class KubernetesClient {
    private String endpoint
    private String namespace

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

    KubernetesClient pods(){
        call('/pods')
        this
    }

    KubernetesClient call(String url) {
    }
}
