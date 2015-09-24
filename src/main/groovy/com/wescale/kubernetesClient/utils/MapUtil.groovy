package com.wescale.kubernetesClient.utils

import com.wescale.kubernetesClient.ToMapIgnore
import groovy.util.logging.Log;

/**
 * Created by cedric on 24/09/2015.
 */
@Log
public class MapUtil {
    public static def toMap(def obj) {
        Class clazz = obj.getClass()
        if (obj == null
                || obj == "null"
                || clazz.isAnnotationPresent(ToMapIgnore))
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
