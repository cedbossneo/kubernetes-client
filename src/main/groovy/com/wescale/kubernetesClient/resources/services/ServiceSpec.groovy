package com.wescale.kubernetesClient.resources.services

import groovy.transform.Canonical
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by cedric on 24/09/2015.
 */
@Canonical
@Builder(builderStrategy = SimpleStrategy, prefix = "assign")
class ServiceSpec {
    List<ServicePort> ports = []
    Map selector = [:]
    String clusterIP
    String type
    List<String> deprecatedPublicIPs = []
    String sessionAffinity
}
