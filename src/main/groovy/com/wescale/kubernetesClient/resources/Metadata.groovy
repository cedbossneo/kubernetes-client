package com.wescale.kubernetesClient.resources

import groovy.transform.Canonical
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
@Builder(builderStrategy = SimpleStrategy)
class Metadata {
    String name
    String generateName
    String namespace
    String selfLink
    String uid
    String resourceVersion
    Integer generation
    String creationTimestamp
    String deletionTimestamp
    Map labels
    Map annotations
}
