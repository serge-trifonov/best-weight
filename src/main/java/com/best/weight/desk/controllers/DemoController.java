package com.best.weight.desk.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.best.weight.desk.entities.Demo;

import representations.DemoRepresentation;

@RestController
@RequestMapping("demo")
public class DemoController {
	
	@PostMapping
    public DemoRepresentation defaultPath(@RequestBody Demo demo) {
        return new DemoRepresentation(demo);
    }
}
