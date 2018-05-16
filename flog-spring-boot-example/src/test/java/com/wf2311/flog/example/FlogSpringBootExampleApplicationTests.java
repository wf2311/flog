package com.wf2311.flog.example;

import com.wf2311.flog.spring.boot.starter.annotation.EnableFlog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@EnableFlog
public class FlogSpringBootExampleApplicationTests {


    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setupMockMvc() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void test() throws Exception {

        mvc.perform(MockMvcRequestBuilders.put("/user/"+1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"name\":\"test\",\"mobile\":\"15012341234\"}"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"test\",\"mobile\":\"15012341234\"}"));
    }


}
