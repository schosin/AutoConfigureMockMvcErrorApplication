package de.schosins.AutoConfigureMockMvcError;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

@SpringBootTest
public class BuilderWithFilterTest {

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;

    @BeforeEach
    void initMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new ResourceUrlEncodingFilter())
                .build();
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "/", "/index" })
    void test(String path) throws Exception {
        mockMvc.perform(get(path)).andExpect(status().isOk());
    }

}
