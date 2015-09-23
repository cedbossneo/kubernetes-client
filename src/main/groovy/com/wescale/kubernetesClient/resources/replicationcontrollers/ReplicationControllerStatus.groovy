package com.wescale.kubernetesClient.resources.replicationcontrollers

import com.wescale.kubernetesClient.ToMapIgnore
import groovy.transform.Canonical

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
@ToMapIgnore
class ReplicationControllerStatus {
    Integer replicas
    Integer observedGeneration
}
