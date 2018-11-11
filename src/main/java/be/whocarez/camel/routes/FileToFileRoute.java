package be.whocarez.camel.routes;

import org.apache.camel.builder.RouteBuilder;

public class FileToFileRoute extends RouteBuilder {

    public void configure() throws Exception {

        from("file:endpoints/input?noop=true")
                .to("file:endpoints/output");

    }

}
