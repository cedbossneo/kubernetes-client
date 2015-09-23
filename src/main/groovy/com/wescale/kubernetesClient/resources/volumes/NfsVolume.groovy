package com.wescale.kubernetesClient.resources.volumes

import groovy.transform.Canonical

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
class NfsVolume {
    String server
    String path
    Boolean readOnly
}
