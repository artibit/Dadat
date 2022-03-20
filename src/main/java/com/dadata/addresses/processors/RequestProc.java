package com.dadata.addresses.processors;

import com.dadata.addresses.datatype.Locations;
import com.dadata.addresses.datatype.RequestData;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RequestProc implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception{
        Gson converter =new Gson();
        exchange.getIn().setHeader(Exchange.HTTP_METHOD, "POST");
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getIn().setHeader("Authorization", "Token 8503bd93c81002f43816174a541cd9300e0afd5e" );
        exchange.getIn().setHeader(Exchange.CHARSET_NAME, "utf8");

        Locations[] locations = new Locations[]{new Locations()};
        locations[0].setCity_fias_id("7dfa745e-aa19-4688-b121-b655c11e482f");

        String query = exchange.getIn().getBody(String.class);

        RequestData request = new RequestData();
        request.setQuery(query);
        request.setCount(20);
        request.setLocations(locations);

        exchange.getIn().setBody(converter.toJson(request));
    }
}
