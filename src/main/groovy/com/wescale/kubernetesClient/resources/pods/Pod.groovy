package com.wescale.kubernetesClient.resources.pods

import com.wescale.kubernetesClient.FluentModel
import com.wescale.kubernetesClient.ResourceEndpoint
import com.wescale.kubernetesClient.resources.Metadata
import com.wescale.kubernetesClient.resources.containers.PodCondition
import groovy.transform.Canonical
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
@Builder(builderStrategy = SimpleStrategy, prefix = "assign")
@ResourceEndpoint('pods')
class Pod extends FluentModel<Pod>{
    PodSpec spec = new PodSpec()
    PodStatus status = new PodStatus()

    boolean ready() {
        status.condition('Ready')?.status == PodCondition.ConditionStatus.True
    }
}
