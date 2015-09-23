package com.wescale.kubernetesClient.resources.containers

/**
 * Created by cedric on 23/09/2015.
 */
class ContainerEnv {
    String name
    String value
    ValueFrom valueFrom

    class ValueFrom {
        FieldRef fieldRef
        class FieldRef {
            String apiVersion
            String fieldPath
        }
    }
}
