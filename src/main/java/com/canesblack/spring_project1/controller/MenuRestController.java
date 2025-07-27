package com.canesblack.spring_project1.controller;

import com.canesblack.spring_project1.service.MenuRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

//
@RestController
public class MenuRestController {

    @Autowired
    private MenuRestService menuRestService;



}
