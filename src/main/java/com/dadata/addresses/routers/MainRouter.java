package com.dadata.addresses.routers;

import com.dadata.addresses.processors.RequestProc;
import com.dadata.addresses.processors.ResponceProc;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

@Component
public class MainRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception{
        from("direct:start")
                .routeId("direct-start")
                //.log("[Start Request]")
                .process(new RequestProc())
                .to("http://suggestions.dadata.ru/suggestions/api/4_1/rs/suggest/address")
                .process(new ResponceProc())
                .end();
    }
}
