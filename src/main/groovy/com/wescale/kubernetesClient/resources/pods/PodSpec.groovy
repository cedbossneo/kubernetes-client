package com.wescale.kubernetesClient.resources.pods

import com.wescale.kubernetesClient.resources.Secret
import com.wescale.kubernetesClient.resources.containers.Container
import com.wescale.kubernetesClient.resources.volumes.Volume
import groovy.transform.Canonical
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
@Builder(builderStrategy = SimpleStrategy)
class PodSpec {
    List<Volume> volumes
    List<Container> containers
    String restartPolicy
    Integer terminationGracePeriodSeconds
    Integer activeDeadlineSeconds
    String dnsPolicy
    Map nodeSelector
    String serviceAccountName
    String serviceAccount
    String nodeName
    Boolean hostNetwork
    List<Secret> imagePullSecrets

    Container container(Container container = new Container()) {
        containers = [container, *containers] as List<Container>
        container
    }

    Volume volume(Volume volume = new Volume()) {
        volumes = [volume, *volumes] as List<Volume>
        volume
    }
}
