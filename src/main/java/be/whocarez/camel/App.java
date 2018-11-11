package be.whocarez.camel;

import be.whocarez.camel.business.FixedCyberClients;
import be.whocarez.camel.routes.PrintRouteBuilder;
import org.apache.camel.main.Main;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

public class App {

    public static void main(String[] args) throws Exception {
        new App().run();
    }

    private void run() throws Exception {

        final Main main = new Main();
        //main.getOrCreateCamelContext().addComponent("activemq", activeMQComponent("vm://localhost?broker.persistent=false"));
        main.addRouteBuilder(new PrintRouteBuilder(new FixedCyberClients()));
        main.run();

    }

}
