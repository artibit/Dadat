package com.dadata.addresses.controlers;

import com.dadata.addresses.datatype.ResponceData;
import com.dadata.addresses.datatype.Suggestions;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainControler {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping(path = "/")
    public String Painging(){
        return "StartPage";
    }

    @PostMapping(path = "/send",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ModelAndView requestingAdata(@RequestParam(value = "query") String query){
        ModelAndView mv = new ModelAndView("StartPage");
        producerTemplate.start();
        List<Suggestions> suggestions = producerTemplate.requestBody("direct:start", query, ResponceData.class).getSuggestions();
        int size=suggestions.size();
        producerTemplate.stop();
        mv.addObject("suggestions", suggestions);
        mv.addObject("Size",size);
        return  mv;
    }

}
