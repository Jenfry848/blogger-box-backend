package com.dauphine.blogger.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("hello-world")
    public String helloword(){
        return "hello World";
    }

    @GetMapping("hello")
    public String helloByName(@RequestParam String name){
        return "hello " + name;
    }

   @GetMapping("hello/{name}")
    public String hello(@PathVariable String name){
        return "Hello " + name;
   }

}
