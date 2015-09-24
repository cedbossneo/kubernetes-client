package com.wescale.kubernetesClient.resources.containers

import groovy.transform.Canonical

/**
 * Created by cedric on 23/09/2015.
 */
@Canonical
class ContainerStatus {
    String name
    State state
    State lastState
    Boolean ready
    Integer restartCount
    String image
    String imageID
    String containerID

    @Canonical
    static class State{
        Waiting waiting
        Running running
        Terminated terminated

        @Canonical
        static class Waiting {
            String reason
        }

        @Canonical
        static class Running {
            String startedAt
        }

        @Canonical
        static class Terminated {
            Integer exitCode
            Integer signal
            String reason
            String message
            String startedAt
            String finishedAt
            String containerID
        }
    }
}
