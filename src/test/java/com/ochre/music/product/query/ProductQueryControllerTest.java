package com.ochre.music.product.query;

import com.ochre.music.MusicApplication;
import com.ochre.music.product.ProductResponse;
import com.ochre.music.product.util.ProductMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigInteger;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class ProductQueryControllerTest {

    @TestConfiguration  // Config to add beans to Spring Test Context
    static class TestConfig {
        @Bean
        public ProductMapper getProductMapper() {
            return new ProductMapper(new MusicApplication().getModelMapper());
        }
    }

    @MockBean
    private ProductQueryService service;

    @Autowired
    private MockMvc mockMvc;

    @Nested
    class ReadProduct{

        @Test
        void when_Get_By_Title_then_Response_Is_Successful() throws Exception {

            // given
            final ProductQuery query = new ProductQuery("Title");
            final ProductResponse stub = ProductResponse.builder().title(query.getTitle()).build();
            Mockito.when(service.findByTitle(Mockito.any()))
                    .thenReturn(stub);

            // then
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/product?title={title}", query.getTitle())
                            .contentType(MediaType.APPLICATION_JSON))// then
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title").value(stub.getTitle()))
                    .andExpect(jsonPath("$.id").isNumber());

        }

        @Test
        void when_Get_By_Title_Invalid_then_Return_Bad_Request() throws Exception {

            // given
            String invalidTitle = "\\^*";

            // then
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/product?title={title}", invalidTitle)
                            .contentType(MediaType.APPLICATION_JSON))// then
                    .andExpect(status().isNotFound());

        }

        @Test
        void when_Get_By_Group_Title_then_Response_Is_Successful() throws Exception {

            // given
            final ProductResponse stub = ProductResponse.builder().id(BigInteger.ONE).title("Title").productGroupTitle("Group title").build();
            Mockito.when(service.findByTitle(Mockito.any()))
                    .thenReturn(stub);

            // then
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/product?groupTitle={groupTitle}", stub.getProductGroupTitle())
                            .contentType(MediaType.APPLICATION_JSON))// then
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title").value(stub.getTitle()))
                    .andExpect(jsonPath("$.id").isNumber());

        }
    }
}
