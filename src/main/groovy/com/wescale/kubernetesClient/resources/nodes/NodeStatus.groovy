package com.wescale.kubernetesClient.resources.nodes

import groovy.transform.Canonical

/**
 * Created by cedric on 27/09/2015.
 */
@Canonical
class NodeStatus {

    @Canonical
    static class NodeAddress {
        String type
        String address
    }

    @Canonical
    static class NodeInfo {
        String machineID
        String systemUUID
        String bootID
        String kernelVersion
        String osImage
        String containerRuntimeVersion
        String kubeletVersion
        String kubeProxyVersion
    }

    String phase
    List<NodeCondition> conditions = []
    NodeInfo nodeInfo = new NodeInfo()
    List<NodeAddress> addresses = []

    NodeCondition condition(String type) {
        conditions.find { it.type == type }
    }
    Map capacity = [:]
}
