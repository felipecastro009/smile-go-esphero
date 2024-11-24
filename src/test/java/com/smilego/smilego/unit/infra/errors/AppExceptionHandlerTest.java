package com.smilego.smilego.unit.infra.errors;

import com.smilego.smilego.infra.errors.AppExceptionHandler;
import com.smilego.smilego.infra.errors.NotFoundError;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AppExceptionHandlerTest {
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new TestController())
            .setControllerAdvice(new AppExceptionHandler())
            .build();

    @Test
    void shouldHandleGenericException() throws Exception {
        mockMvc.perform(get("/throw-generic"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.message").value("An error occurred"));
    }

    @Test
    void shouldHandleNotFoundException() throws Exception {
        mockMvc.perform(get("/throw-notfound"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("NOT_FOUND"));
    }

    @RestController
    static class TestController {
        @GetMapping("/throw-generic")
        public void throwGeneric() {
            throw new RuntimeException("An error occurred");
        }

        @GetMapping("/throw-notfound")
        public void throwNotFound() {
            throw new NotFoundError();
        }
    }
}
