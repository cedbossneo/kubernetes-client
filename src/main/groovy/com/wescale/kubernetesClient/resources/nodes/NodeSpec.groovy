package com.wescale.kubernetesClient.resources.nodes

import groovy.transform.Canonical

/**
 * Created by cedric on 27/09/2015.
 */
@Canonical
class NodeSpec {
    String podCIDR
    String externalID
    String providerID
    Boolean unschedulable
}
