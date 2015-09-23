package com.wescale.kubernetesClient.resources.containers

import groovy.transform.Canonical
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
@Builder(builderStrategy = SimpleStrategy, prefix = "assign")
class Container {
    String name
    String image
    List<String> command
    List<String> args
    String workingDir
    List<ContainerPort> ports
    List<ContainerEnv> env
    ContainerResources resources
    List<ContainerVolumeMount> volumeMounts = []
    ContainerProbe livenessProbe
    ContainerProbe readinessProbe
    ContainerLifeCycle lifecycle
    String terminationMessagePath
    String imagePullPolicy
    ContainerSecurityContext securityContext
}
