package com.dadata.addresses.controlers;

import com.dadata.addresses.datatype.ResponceData;
import com.dadata.addresses.datatype.Suggestions;
import com.dadata.addresses.routers.MainRouter;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class MainControler {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping(path = "/")
    public String Painging(){
        return "StartPage";
    }

    @PostMapping(path = "/send",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ModelAndView RequestingADATA(@RequestParam(value = "query") String query){
        ModelAndView mv = new ModelAndView("StartPage");
        producerTemplate.start();
        List<Suggestions> suggestions = producerTemplate.requestBody("direct:start",
                query, ResponceData.class).getSuggestions();
        producerTemplate.stop();
        mv.addObject("suggestions", suggestions);
        return  mv;
    }

}
