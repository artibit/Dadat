package com.dadata.addresses.processors;

import com.dadata.addresses.datatype.ResponceData;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ResponceProc implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception{
        Gson converter =new Gson();

        String responce =exchange.getIn().getBody(String.class);
        ResponceData data = converter.fromJson(responce, ResponceData.class);
        exchange.getOut().setBody(data, ResponceData.class);

    }
}
