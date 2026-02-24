package com.ricardo.skins.controllers;


import com.ricardo.skins.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OpenCaseController {

    @Autowired
    private CaseService caseService;


}
