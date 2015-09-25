package com.wescale.kubernetesClient.resources.bindings

import com.wescale.kubernetesClient.FluentModel
import com.wescale.kubernetesClient.KubernetesResource
import groovy.transform.Canonical
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by cedric on 25/09/2015.
 */
@Canonical
@Builder(builderStrategy = SimpleStrategy, prefix = "assign")
@KubernetesResource(endpoint = 'bindings')
class Binding extends FluentModel<Binding>{
    BindingTarget target = new BindingTarget()
}
