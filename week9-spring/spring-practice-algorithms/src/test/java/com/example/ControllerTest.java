package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(com.example.Application.class)
public class ControllerTest {
    @Autowired
    private MockMvc mvc;

    //GET /camelize?original=this_is_a_thing => thisIsAThing
    @Test
    public void testCamelize() throws Exception{
        RequestBuilder req = MockMvcRequestBuilders.get("/camelize?original=this_is_a_thing");
        this.mvc.perform(req).andExpect(status().isOk()).andExpect(content().string("thisIsAThing"));
    }

    //GET /camelize?original=this_is_a_thing&initialCap=true => ThisIsAThing
    @Test public void testCamelizeWithInitialCap() throws Exception{
        RequestBuilder req = MockMvcRequestBuilders.get("/camelize?original=this_is_a_thing&initialCap=true");
        this.mvc.perform(req).andExpect(status().isOk()).andExpect(content().string("ThisIsAThing"));
    }

    //GET /redact?original=A little of this and a little of that&badWord=little
    @Test public void testRedact() throws Exception{
        RequestBuilder req = MockMvcRequestBuilders.get("/redact?original=A little of this and a little of that&badWord=little");
        this.mvc.perform(req).andExpect(status().isOk()).andExpect(content().string("A ****** of this and a ****** of that"));
    }

    //GET /redact?original=A little of this and a little of that&badWord=little&badWord=this
    @Test public void testRedactWithMultipleBadWords() throws Exception{
        RequestBuilder req = MockMvcRequestBuilders.get("/redact?original=A little of this and a little of that&badWord=little&badWord=this");
        this.mvc.perform(req).andExpect(status().isOk()).andExpect(content().string("A ****** of **** and a ****** of that"));
    }

    //POST /encode
    //message=a little of this and a little of that&key=mcxrstunopqabyzdefghijklvw
    @Test public void testEncode() throws Exception{
        RequestBuilder req = MockMvcRequestBuilders.post("/encode")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("message", "a little of this and a little of that")
                .param("key", "mcxrstunopqabyzdefghijklvw");
        this.mvc.perform(req).andExpect(status().isOk()).andExpect(content().string("m aohhas zt hnog myr m aohhas zt hnmh"));
    }

    //POST /s/little/lot
    //
    //a little of this and a little of that
    @Test public void testSed() throws Exception{
        RequestBuilder req = MockMvcRequestBuilders.post("/s/little/lot")
                .contentType(MediaType.TEXT_PLAIN)
                .content("a little of this and a little of that");
        this.mvc.perform(req).andExpect(status().isOk()).andExpect(content().string("a lot of this and a lot of that"));
    }

}
