package com.wescale.kubernetesClient.resources.pods

import com.wescale.kubernetesClient.resources.containers.ContainerCondition
import com.wescale.kubernetesClient.resources.containers.ContainerStatus
import groovy.transform.Canonical
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
class PodStatus {
    String phase
    List<ContainerCondition> conditions
    String message
    String reason
    String hostIP
    String podIP
    String startTime
    List<ContainerStatus> containerStatuses
}
