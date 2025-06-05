package com.anatoliydrake.fibonacci;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class FibonacciControllerTest extends PostgresTestContainerInitializer {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get value with index greater than 1")
    public void testGetNumberWithIndexGreaterThan1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/fibonacci/10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value(55));

    }

    @Test
    @DisplayName("Get value with index less than 1")
    public void testGetNumberWithIndexLessThan1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/fibonacci/0"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Index should be greater or equal to 1"));

    }
}
