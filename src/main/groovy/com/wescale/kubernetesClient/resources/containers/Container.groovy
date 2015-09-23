package com.wescale.kubernetesClient.resources.containers

import groovy.transform.Canonical

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
class Container {
    String name
    String image
    List<String> command
    List<String> args
    String workingDir
    List<ContainerPort> ports
    List<ContainerEnv> env
    ContainerResources resources
    List<ContainerVolumeMount> volumeMounts
    ContainerProbe livenessProbe
    ContainerProbe readinessProbe
    ContainerLifeCycle lifecycle
    String terminationMessagePath
    String imagePullPolicy
    ContainerSecurityContext securityContext
}
