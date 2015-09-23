package com.wescale.kubernetesClient.resources.replicationcontrollers

import com.wescale.kubernetesClient.resources.pods.Pod
import groovy.transform.Canonical
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
@Builder(builderStrategy = SimpleStrategy, prefix = "assign")
class ReplicationControllerSpec {
    Integer replicas
    Map selector = [:]
    Pod template = new Pod()

}
