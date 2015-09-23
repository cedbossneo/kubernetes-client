package com.wescale.kubernetesClient.resources.pods

import com.wescale.kubernetesClient.ToMapIgnore
import com.wescale.kubernetesClient.resources.containers.PodCondition
import com.wescale.kubernetesClient.resources.containers.ContainerStatus
import groovy.transform.Canonical

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
@ToMapIgnore
class PodStatus {
    String phase
    List<PodCondition> conditions
    String message
    String reason
    String hostIP
    String podIP
    String startTime
    List<ContainerStatus> containerStatuses

    PodCondition condition(String type){
        conditions.find {it.type == type}
    }
}
