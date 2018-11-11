package be.whocarez.camel.routes;

import be.whocarez.camel.App;
import be.whocarez.camel.business.CyberClients;
import org.apache.camel.builder.RouteBuilder;

import java.util.Map;

public class PrintRouteBuilder extends RouteBuilder {

    //private final String queueTemplate = "activemq:%s";
    private final String queueTemplate = "file:endpoints/output?fileName=%s.txt&fileExist=Append";
    private final CyberClients cyberClients;

    public PrintRouteBuilder(CyberClients cyberClients) {
        this.cyberClients = cyberClients;
    }

    @Override
    public void configure() throws Exception {

//        from("activemq:printQueue")
        from("stream:in")
                .dynamicRouter()
                .body(this::determineQueue)
                .end();

    }

    private String determineQueue(Object message, Map<String, Object> properties) {

        final Object alreadyProcessed = properties.putIfAbsent("processed", true);

        if (alreadyProcessed == null) {
            final String queueName = cyberClients.queueNameByGuid(destination(message));
            System.out.printf("Message [%s] will be put on queue [%s]%n", message, queueName);
            return String.format(queueTemplate, queueName);

        }

        return null;

    }

    private String destination(Object input) {
        return ((String) input).substring(0, 1);
    }

}
