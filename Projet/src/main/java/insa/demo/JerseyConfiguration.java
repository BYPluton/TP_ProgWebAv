package insa.demo;

import insa.demo.resources.items.ItemResource;
import insa.demo.resources.users.UserResource;
import insa.demo.resources.watchlists.WatchListResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import org.glassfish.jersey.servlet.ServletProperties;
import javax.ws.rs.ApplicationPath;

/**
 * JerseyConfiguration
 * 
 * @author  Birkan Yildiz & Nicolas Martin
 * @version 1.0
 */

@Component
@ApplicationPath("rest")

public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        register(ItemResource.class);
        register(WatchListResource.class);
        register(UserResource.class);

        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }
}
