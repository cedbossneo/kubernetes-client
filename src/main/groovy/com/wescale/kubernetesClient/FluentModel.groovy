package com.wescale.kubernetesClient

import com.wescale.kubernetesClient.resources.Metadata
import com.wescale.kubernetesClient.utils.MapUtil
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.transform.stc.ClosureParams
import groovy.transform.stc.FromString
import groovy.util.logging.Log
import wslite.rest.ContentType
import wslite.rest.RESTClientException

/**
 * Created by cedric on 23/09/2015.
 */
@Log
abstract class FluentModel<T> {
    String apiVersion
    String kind
    Metadata metadata

    KubernetesClient client

    FluentModel() {
        apiVersion = 'v1'
        kind = resourceKind()
    }

    T assignMetadata(Metadata existing = new Metadata()) {
        this.metadata = existing
        this as T
    }

    KubernetesClient delete() {
        try {
            client.callOnNamespace(metadata.namespace).delete(path: "/${resourceEndpoint()}/${metadata.name}")
        } catch (RESTClientException e) {
            def jsonResponse = new JsonSlurper().parse(e.response.data)
            log.warning("Could not execute delete request for ${resourceKind()} ${metadata.name} : ${jsonResponse.message}")
        }
        client
    }

    def resourceEndpoint() {
        getClass().getAnnotation(ResourceEndpoint).value()
    }

    T patch(@ClosureParams(value = FromString.class, options = "T") Closure patch) {
        def newObject = toMap()
        patch.call(newObject)
        def patchObject = newObject - toMap()
        try {
            client.callOnNamespace(metadata.namespace).patch(path: "/${resourceEndpoint()}/${metadata.name}") {
                type "application/merge-json-patch+json"
                charset "US-ASCII"
                json patchObject
            }
        } catch (RESTClientException e) {
            def jsonResponse = new JsonSlurper().parse(e.response.data)
            log.warning("Could not execute patch request for ${resourceKind()} ${metadata.name} : ${jsonResponse.message}")
            log.fine(new JsonBuilder(patchObject).toPrettyString())
        }
        this as T
    }

    T update(@ClosureParams(value = FromString.class, options = "T") Closure patch) {
        def newObject = toMap()
        patch.call(newObject)
        try {
            client.callOnNamespace(metadata.namespace).put(path: "/${resourceEndpoint()}/${metadata.name}") {
                type ContentType.JSON
                charset "US-ASCII"
                json newObject
            }
        } catch (RESTClientException e) {
            def jsonResponse = new JsonSlurper().parse(e.response.data)
            log.warning("Could not execute update request for ${resourceKind()} ${metadata.name} : ${jsonResponse.message}")
            log.fine(new JsonBuilder(newObject).toPrettyString())
        }
        this as T
    }

    def resourceKind() {
        getClass().simpleName
    }

    T create() {
        def newObject = toMap()
        try {
            client.callOnNamespace(metadata?.namespace ?: client.namespace).post(path: "/${resourceEndpoint()}") {
                type ContentType.JSON
                charset 'US-ASCII'
                json newObject
            }.json
        } catch (RESTClientException e) {
            def jsonResponse = new JsonSlurper().parse(e.response.data)
            log.warning("Could not execute create request for ${resourceKind()} ${metadata.name} : ${jsonResponse.message}")
            log.fine(new JsonBuilder(newObject).toPrettyString())
        }
    }

    def toMap() {
        MapUtil.toMap(this)
    }


}
