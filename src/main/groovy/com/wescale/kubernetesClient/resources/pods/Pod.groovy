package com.wescale.kubernetesClient.resources.pods

import com.wescale.kubernetesClient.FluentModel
import com.wescale.kubernetesClient.resources.Metadata
import groovy.transform.Canonical
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
@Builder(builderStrategy = SimpleStrategy)
class Pod extends FluentModel<Pod>{
    PodSpec spec
    PodStatus status

    @Override
    String resourceEndpoint() {
        'pods'
    }

    @Override
    String resourceKind() {
        'Pod'
    }

    PodSpec spec(PodSpec existing = new PodSpec()){
        this.spec = existing
    }

}
