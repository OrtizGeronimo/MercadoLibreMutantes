package com.example.mutantes.Integration;

import com.example.mutantes.MutantesApplication;
import com.example.mutantes.services.MutantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MutantesApplication.class)
@AutoConfigureMockMvc
public class MutantControllerTest {

    @Autowired
    MutantService ms;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testMutante() throws Exception{

            mockMvc.perform(post("/apiMutants/v1/mutant")
                    .content("{\"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

    }

}
