package insa.demo;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.glassfish.jersey.servlet.ServletProperties;
import javax.ws.rs.ApplicationPath;


@Component
@ApplicationPath("rest")
@Configuration

public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }
}
