package com.wescale.kubernetesClient.resources.volumes

import groovy.transform.Canonical

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
class AwsElasticBlockStoreVolume {
    String volumeID
    String fsType
    Integer partition
    Boolean readOnly
}
