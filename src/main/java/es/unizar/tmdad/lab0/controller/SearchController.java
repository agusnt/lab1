package es.unizar.tmdad.lab0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.social.twitter.api.SearchResults;

import es.unizar.tmdad.lab0.service.TwitterLookupService;


@Controller
public class SearchController {

    @Autowired
    TwitterLookupService twitter;

    @RequestMapping("/")
    public String greeting() {
        return "index";
    }

    //@ResponseBody
    //Mapea mensaje en en un metodo
    @MessageMapping("/search")
    public void search(String q) {
        twitter.search(q);
    }
}
