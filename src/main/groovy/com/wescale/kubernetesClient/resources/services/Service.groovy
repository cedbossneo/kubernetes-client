package com.wescale.kubernetesClient.resources.services

import com.wescale.kubernetesClient.FluentModel
import com.wescale.kubernetesClient.ResourceEndpoint
import groovy.transform.Canonical
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by cedric on 24/09/2015.
 */
@Canonical
@Builder(builderStrategy = SimpleStrategy, prefix = "assign")
@ResourceEndpoint('services')
class Service extends FluentModel<Service>{
    ServiceSpec spec = new ServiceSpec()
    ServiceStatus status = new ServiceStatus()
}
