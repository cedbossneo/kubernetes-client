package com.wescale.kubernetesClient.resources.pods

import com.wescale.kubernetesClient.resources.Metadata
import groovy.transform.Canonical

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
class Pod {
    Metadata metadata
    PodSpec spec
    PodStatus status
}
