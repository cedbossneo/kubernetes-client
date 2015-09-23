package com.wescale.kubernetesClient.resources.replicationcontrollers

import com.wescale.kubernetesClient.FluentModel
import com.wescale.kubernetesClient.resources.pods.Pod
import com.wescale.kubernetesClient.resources.pods.PodSpec
import groovy.transform.Canonical
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
@Builder(builderStrategy = SimpleStrategy, prefix = "assign")
class ReplicationController extends FluentModel<ReplicationController>{
    ReplicationControllerSpec spec = new ReplicationControllerSpec()
    ReplicationControllerStatus status = new ReplicationControllerStatus()

    @Override
    String resourceEndpoint() {
        "/replicationcontrollers"
    }

    @Override
    String resourceKind() {
        "ReplicationController"
    }

    ReplicationController assignTemplate(Pod pod = new Pod()){
        spec.template = pod
        this
    }
}
