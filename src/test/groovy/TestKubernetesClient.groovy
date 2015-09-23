import com.wescale.kubernetesClient.KubernetesClient
import org.junit.Test

/**
 * Created by cedric on 23/09/2015.
 */
class TestKubernetesClient {

    @Test
    public void testServices(){
        def client = KubernetesClient.create('http://192.168.99.100:8080')
        assert client.pods().size() == 1
    }

}
