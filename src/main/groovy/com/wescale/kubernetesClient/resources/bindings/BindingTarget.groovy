package com.wescale.kubernetesClient.resources.bindings

import groovy.transform.Canonical
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by cedric on 25/09/2015.
 */
@Canonical
@Builder(builderStrategy = SimpleStrategy, prefix = "assign")
class BindingTarget {
    String kind
    String namespace
    String name
    String uid
    String apiVersion
    String resourceVersion
    String fieldPath
}
