package com.wescale.kubernetesClient.resources.services

import groovy.transform.Canonical

/**
 * Created by cedric on 24/09/2015.
 */
@Canonical
class ServiceStatus {
    LoadBalancer loadBalancer

    @Canonical
    static class LoadBalancer {
        List<Ingress> ingress = []

        @Canonical
        static class Ingress {
            String ip
            String hostname
        }
    }
}
