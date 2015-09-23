package com.wescale.kubernetesClient.resources.volumes

import groovy.transform.Canonical

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
class IscsiVolume {
    String targetPortal
    String iqn
    Integer lun
    String fsType
    Boolean readOnly
}
