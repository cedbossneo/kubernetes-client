package com.wescale.kubernetesClient.resources.containers

import groovy.transform.Canonical

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
class ContainerEnv {
    String name
    String value
    ValueFrom valueFrom

    @Canonical
    static class ValueFrom {
        FieldRef fieldRef

        @Canonical
        static class FieldRef {
            String apiVersion
            String fieldPath
        }
    }
}
