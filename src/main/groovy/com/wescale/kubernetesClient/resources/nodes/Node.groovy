package com.wescale.kubernetesClient.resources.nodes

import com.wescale.kubernetesClient.FluentModel
import com.wescale.kubernetesClient.KubernetesResource
import groovy.transform.Canonical
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by cedric on 27/09/2015.
 */
@Canonical
@Builder(builderStrategy = SimpleStrategy, prefix = "assign")
@KubernetesResource(endpoint = 'nodes')
class Node extends FluentModel<Node> {
    NodeSpec spec = new NodeSpec();
    NodeStatus status = new NodeStatus();

    boolean ready() {
        status.condition('Ready')?.status == NodeCondition.ConditionStatus.True
    }
}
