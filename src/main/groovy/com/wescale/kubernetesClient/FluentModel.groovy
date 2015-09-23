package com.wescale.kubernetesClient

import com.wescale.kubernetesClient.resources.Metadata
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy
import groovy.transform.stc.ClosureParams
import groovy.transform.stc.FromString
import wslite.rest.ContentType

/**
 * Created by cedric on 23/09/2015.
 */
abstract class FluentModel<T> {
    String apiVersion
    String kind
    Metadata metadata

    KubernetesClient client

    abstract String resourceEndpoint()
    abstract String resourceKind()

    FluentModel() {
        apiVersion = 'v1'
        kind = resourceKind()
    }

    T assignMetadata(Metadata existing = new Metadata()){
        this.metadata = existing
        this as T
    }

    KubernetesClient delete(){
        client.callOnNamespace(metadata.namespace).delete(path: "/${resourceEndpoint()}/${metadata.name}")
        client
    }

    T patch(@ClosureParams(value=FromString.class, options="T") Closure patch){
        def newObject = toMap()
        patch.call(newObject)
        def patchObject = newObject - toMap()
        client.callOnNamespace(metadata.namespace).patch(path: "/${resourceEndpoint()}/${metadata.name}"){
            type "application/merge-json-patch+json"
            charset "US-ASCII"
            json patchObject
        }
        this as T
    }

    T update(@ClosureParams(value=FromString.class, options="T") Closure patch){
        def newObject = toMap()
        patch.call(newObject)
        client.callOnNamespace(metadata.namespace).put(path: "/${resourceEndpoint()}/${metadata.name}"){
            type ContentType.JSON
            charset "US-ASCII"
            json newObject
        }
        this as T
    }

    T create() {
        client.callOnNamespace(metadata?.namespace ?: client.namespace).post(path: "/${resourceEndpoint()}"){
            type ContentType.JSON
            charset 'US-ASCII'
            json toMap()
        }.json
    }

    def toMap(){
        toMap(this)
    }

    def toMap(def obj) {
        Class clazz = obj.getClass()
        if (obj == null || clazz.isAnnotationPresent(ToMapIgnore))
            return null
        if (clazz.primitive || isWrappedType(clazz))
            return obj
        if (obj instanceof Map) {
            return (obj as Map).collectEntries { skey, sval ->
                [(skey), toMap(sval)]
            }.findAll {it.value != null}
        } else if (obj instanceof Collection) {
            //noinspection GroovyAssignabilityCheck
            return (obj as Collection).collect { sit ->
                toMap(sit)
            }.findAll {it != null}
        }
        obj.properties.findAll{it.key != 'class'}.collectEntries {it ->
            def value = it.value
            def key = it.key
            [(key): toMap(value)]
        }.findAll {it.value != null}
    }

    public static boolean isWrappedType(Class klass) {
        [String.class, Boolean.class,
         Character.class,
         Byte.class,
         Short.class,
         Integer.class,
         Long.class,
         Float.class,
         Double.class,
         Void.class].contains(klass)
    }

}
