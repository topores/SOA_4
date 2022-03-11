package client;

import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.model.health.ServiceHealth;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.List;

import static java.lang.String.format;

@Slf4j
@Singleton
public class ServiceDiscoveryRequestManager {

    private Consul client = null;

    @Inject
    @ConfigProperty(name = "service.name")
    private String serviceName;

    @PostConstruct
    private void init() {
        try {
            client = Consul.builder().build();
        } catch (Exception e) {
            log.info("Consul is unavailable");
        }
    }

    public String discoverServiceUrl() {
        if (client != null) {
            HealthClient healthClient = client.healthClient();
            List<ServiceHealth> response = healthClient.getHealthyServiceInstances(serviceName).getResponse();
            if (!response.isEmpty()) {
                ServiceHealth serviceHealth = response.get(0);
                String hostname = serviceHealth.getNode().getAddress();
                int port = serviceHealth.getService().getPort();

                return format("http://%s:%d", hostname, port);

            }
        } //else {
//            return "http://localhost:8101";
//        }
        throw new RuntimeException(format("Service %s couldn't be found in the Consul", serviceName));
    }
}
