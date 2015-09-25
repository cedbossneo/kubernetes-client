package com.wescale.kubernetesClient

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created by cedric on 24/09/2015.
 */

@Retention(RetentionPolicy.RUNTIME)
@interface KubernetesResource {
    String endpoint() default ""
}