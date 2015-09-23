package com.wescale.kubernetesClient.resources.containers

import groovy.transform.Canonical

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
class ContainerSecurityContext {
    Capabilities capabilities
    Boolean privileged
    SeLinuxOptions seLinuxOptions
    Integer runAsUser

    @Canonical
    class Capabilities {
        List<Object> add
        List<Object> drop
    }

    @Canonical
    class SeLinuxOptions {
        String user
        String role
        String type
        String level
    }
}
