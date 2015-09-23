package com.wescale.kubernetesClient.resources.volumes

import com.wescale.kubernetesClient.resources.Secret
import groovy.transform.Canonical

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
class RbdVolume {
    List<String> monitors
    String image
    String fsType
    String pool
    String user
    String keyring
    Secret secretRef
    Boolean readOnly
}
