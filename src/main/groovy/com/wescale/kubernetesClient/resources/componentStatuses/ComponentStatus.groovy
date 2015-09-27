package com.wescale.kubernetesClient.resources.componentStatuses

import groovy.transform.Canonical

/**
 * Created by cedric on 25/09/2015.
 */

@Canonical
class ComponentStatus {
    List<Condition> conditions = []

    @Canonical
    static class Condition {
            String type
            String status
            String message
            String error
    }
}
