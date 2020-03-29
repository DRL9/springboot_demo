package com.drl.demo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class ApiControllerTest extends Specification {

    @Autowired
    ApiController webController

    def "Controller is loaded"() {
        expect: "webController is created."
        webController
    }

    @Autowired
    MockMvc mvc

    def path = "/api/hello"

    def "Hello"() {
        expect:
        "put $path is right'"
        mvc.perform(put("$path"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello world"))
    }

    def "GetHello"() {
        expect:
        "get $path is right"
        mvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Get: hello world"))
    }

    def "PostHello"() {
        expect:
        "post $path is right"
        mvc.perform(post("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Post: hello world!"))
    }
}
