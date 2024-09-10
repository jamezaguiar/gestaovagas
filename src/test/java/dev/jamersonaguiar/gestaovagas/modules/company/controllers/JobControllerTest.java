package dev.jamersonaguiar.gestaovagas.modules.company.controllers;

import dev.jamersonaguiar.gestaovagas.modules.company.dto.CreateJobDTO;
import dev.jamersonaguiar.gestaovagas.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void shouldBeAbleToCreateANewJob() throws Exception {
        var createJobDTO = CreateJobDTO
                .builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/company/job")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.toJSON(createJobDTO))
                        .header("Authorization", TestUtils.generateToken(UUID.randomUUID()))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
