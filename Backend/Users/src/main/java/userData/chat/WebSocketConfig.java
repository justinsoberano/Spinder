package userData.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * This class is used to configure the WebSocketConfig.
 */
@Configuration
public class WebSocketConfig {

    /**
     * This method is used to create a new ServerEndpointExporter.
     * @return a new ServerEndpointExporter
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
