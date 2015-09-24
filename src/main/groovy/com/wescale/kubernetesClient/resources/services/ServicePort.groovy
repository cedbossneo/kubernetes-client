package com.wescale.kubernetesClient.resources.services

import groovy.transform.Canonical

/**
 * Created by cedric on 24/09/2015.
 */
@Canonical
class ServicePort {
    String name
    String protocol
    Integer port
    Integer targetPort
    Integer nodePort
}
