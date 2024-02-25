package org.electronicsstore.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.entity.ContentType;
import org.electronicsstore.backend.dtos.CustomerCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(args = {"--KEYCLOAK_BACKEND_CLIENT_SECRET=yWzrTIs2DWRX28mYhPPWgNrVewBYmU8a"})
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldCreateCustomer() throws Exception {
        String hash = UUID.randomUUID().toString();
        CustomerCreateRequest req = new CustomerCreateRequest(
                "test@email.test",
                "test-" + hash,
                "test-" + hash,
                "test"
        );
        this.mockMvc.perform(post("/api/auth/public/users")
                        .contentType(ContentType.APPLICATION_JSON.getMimeType())
                        .content(mapper.writeValueAsString(req)))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
