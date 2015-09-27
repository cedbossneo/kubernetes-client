package com.wescale.kubernetesClient.resources.nodes

import groovy.transform.Canonical

/**
 * Created by cedric on 27/09/2015.
 */
@Canonical
class NodeCondition {
    String type
    ConditionStatus status
    String lastHeartbeatTime
    String lastTransitionTime
    String reason
    String message

    public static enum ConditionStatus {
        True, False, Unknown
    }
}
