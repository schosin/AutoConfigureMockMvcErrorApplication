package de.schosins.AutoConfigureMockMvcError;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AutowireTest {

    @Autowired
    protected MockMvc mockMvc;

    @ParameterizedTest
    @ValueSource(strings = { "", "/", "/index" })
    void test(String path) throws Exception {
        mockMvc.perform(get(path)).andExpect(status().isOk());
    }

}
