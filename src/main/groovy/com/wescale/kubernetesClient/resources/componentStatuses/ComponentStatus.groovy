package com.wescale.kubernetesClient.resources.componentStatuses

import com.wescale.kubernetesClient.KubernetesResource
import groovy.transform.Canonical
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

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
