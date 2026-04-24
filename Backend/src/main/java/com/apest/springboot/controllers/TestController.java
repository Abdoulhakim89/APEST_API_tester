package com.apest.springboot.controllers;

import engine.TestEngine;
import org.springframework.web.bind.annotation.*;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import requestFiles.API_test;
import requestFiles.RequestConfig;
import responseFiles.API_Testresult;

import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping("/")
public class TestController  {
    @PostMapping("/run")
    public API_Testresult runTests(@RequestBody API_test requestBody)   {
        RequestConfig.config = requestBody;

        TestEngine.testResults.clear();

        TestNG testNG = new TestNG();
        testNG.setTestClasses(new Class[]{TestEngine.class});
        testNG.run();


        return new API_Testresult(TestEngine.suiteName,TestEngine.baseUrl, TestEngine.resultCollector());
    }


}
