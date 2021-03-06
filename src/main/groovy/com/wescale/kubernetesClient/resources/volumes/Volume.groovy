package com.wescale.kubernetesClient.resources.volumes

import com.wescale.kubernetesClient.resources.Secret
import groovy.transform.Canonical
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
@Builder(builderStrategy = SimpleStrategy, prefix = "assign")
class Volume {
    String name
    HostPathVolume hostPath
    EmptyDirVolume emptyDir
    GcePersistentDiskVolume gcePersistentDisk
    AwsElasticBlockStoreVolume awsElasticBlockStore
    GitRepoVolume gitRepo
    Secret secret
    NfsVolume nfs
    IscsiVolume iscsi
    GlusterFSVolume glusterfs
    PersistentVolumeClaimVolume persistentVolumeClaim
    RbdVolume rbd
}
