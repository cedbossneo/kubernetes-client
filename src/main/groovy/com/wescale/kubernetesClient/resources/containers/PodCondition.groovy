package com.wescale.kubernetesClient.resources.containers

import groovy.transform.Canonical

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
class PodCondition {
    String type
    ConditionStatus status

    public static enum ConditionStatus {
        True, False, Unknown
    }
}
