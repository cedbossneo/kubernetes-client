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
    class State{
        Waiting waiting
        Running running
        Terminated terminated

        @Canonical
        class Waiting {
            String reason
        }

        @Canonical
        class Running {
            String startedAt
        }

        @Canonical
        class Terminated {
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
