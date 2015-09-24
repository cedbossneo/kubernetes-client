import com.wescale.kubernetesClient.KubernetesClient
import com.wescale.kubernetesClient.resources.Metadata
import com.wescale.kubernetesClient.resources.containers.Container
import com.wescale.kubernetesClient.resources.pods.Pod
import com.wescale.kubernetesClient.resources.pods.PodSpec
import com.wescale.kubernetesClient.resources.replicationcontrollers.ReplicationControllerSpec
import com.wescale.kubernetesClient.resources.services.ServicePort
import com.wescale.kubernetesClient.resources.services.ServiceSpec
import groovy.util.logging.Log
import org.junit.Test

/**
 * Created by cedric on 23/09/2015.
 */
@Log
class TestKubernetesClient {

    @Test
    public void testPods(){
        def client = KubernetesClient.create('http://192.168.99.100:8080')
        if (client.pods().size() > 0){
            client.pods().get(0).delete()
        }
        assert client.pods().size() == 0

        client.pod()
                .assignMetadata(new Metadata(name: 'toto2'))
                .assignSpec(new PodSpec(containers: [new Container(name: 'huhu', image: 'nginx')]))
                .create()

        assert client.pods().size() == 1
        while (!client.pod('toto2').ready()){
            log.info('Waiting for toto2 to be ready')
            Thread.sleep(1000)
        }

        client.pods().get(0).update { patch ->
            patch.metadata.annotations.toto = 'tata'
        }

        client.pods().get(0).delete()

        assert client.pods().size() == 0
    }

    @Test
    public void testServices(){
        def client = KubernetesClient.create('http://192.168.99.100:8080')
        if (client.services().size() > 1){
            client.services().get(1).delete()
        }
        assert client.services().size() == 1

        client.service()
                .assignMetadata(new Metadata(name: 'toto2'))
                .assignSpec(new ServiceSpec(ports: [new ServicePort(port: 80, targetPort: 80)], selector: [name: 'toto']))
                .create()

        assert client.services().size() == 2

        client.services().get(1).update { patch ->
            patch.metadata.annotations.toto = 'tata'
        }

        client.services().get(1).delete()

        assert client.services().size() == 1
    }

    @Test
    public void testReplicationController(){
        def client = KubernetesClient.create('http://192.168.99.100:8080')

        if (client.replicationControllers().size() > 0){
            client.replicationControllers().get(0).delete()
        }
        assert client.replicationControllers().size() == 0

        def result = client.replicationController()
                .assignMetadata(new Metadata(name: 'toto2', labels: [name: 'toto']))
                .assignSpec(new ReplicationControllerSpec(
                        replicas: 1,
                        selector: [name: 'toto']))
                .assignTemplate(new Pod(
                        metadata: new Metadata(name: 'toto2', labels: [name: 'toto']),
                        spec: new PodSpec(containers: [new Container(name: 'huhu', image: 'nginx')])
                ))
                .create()
        assert result != null
        assert client.replicationControllers().size() == 1

        while (!client.replicationController('toto2').ready()){
            log.info('Waiting for toto2 to be ready')
            Thread.sleep(1000)
        }
        client.replicationControllers().get(0).update { patch ->
            patch.spec.replicas = 2
        }
        while (!client.replicationController('toto2').ready()){
            log.info('Waiting for toto2 to be ready')
            Thread.sleep(1000)
        }
        client.replicationControllers().get(0).delete()
        assert client.replicationControllers().size() == 0
    }

}
