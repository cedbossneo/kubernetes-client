package com.wescale.kubernetesClient.resources.containers

import groovy.transform.Canonical

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
class ContainerExecution {
    Exec exec
    HttpGet httpGet
    TcpSocket tcpSocket

    @Canonical
    class Exec {
        List<String> command
    }

    @Canonical
    class HttpGet {
        String path
        String port
        String host
        String scheme
    }

    @Canonical
    class TcpSocket {
        String port
    }
}
