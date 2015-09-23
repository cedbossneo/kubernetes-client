import com.wescale.kubernetesClient.KubernetesClient
import com.wescale.kubernetesClient.resources.Metadata
import com.wescale.kubernetesClient.resources.pods.Pod
import org.junit.Test

/**
 * Created by cedric on 23/09/2015.
 */
class TestKubernetesClient {

    @Test
    public void testPods(){
        def client = KubernetesClient.create('http://192.168.99.100:8080')
        assert client.pods().size() == 0
    }

    @Test
    public void testUpdate(){
        def client = KubernetesClient.create('http://192.168.99.100:8080')
        client.pods().get(0).patch { patch ->
            patch.spec.containers[0].name = 'toto2'
        }
    }

    @Test
    public void testCreate(){
        def client = KubernetesClient.create('http://192.168.99.100:8080')

        def newPod = client.pod()
        newPod.metadata().setName('toto2')
        newPod.spec().container().setName('huhu').setImage('nginx')
        newPod.create()
    }

}
