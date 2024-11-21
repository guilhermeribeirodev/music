package com.ochre.music.product.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ochre.music.product.ProductRequest;
import com.ochre.music.product.ProductResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigInteger;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
class ProductCommandControllerTest {

    @MockBean
    private ProductCommandService service;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Nested
    class CreateProductResource {

        @Test
        void when_Post_Invalid_Values_then_Result_Error() throws Exception {

            // given
            final ProductRequest request = ProductRequest.builder().title("").build();
            String json = jsonMapper.writeValueAsString(request);

            // when
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/product")
                            .content(json).contentType(MediaType.APPLICATION_JSON))
            // then
                    .andExpect(status().isBadRequest());
        }

        @Test
        void when_Post_Valid_Values_then_Result_Success() throws Exception {

            // given
            final ProductRequest request =  ProductRequest.builder().title("Title").productGroupTitle("Group Title").build();
            String json = jsonMapper.writeValueAsString(request);

            Mockito.when(service.create(Mockito.any()))
                    .thenReturn(ProductResponse.builder().id(BigInteger.ONE).build());

            // when
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/product")
                            .content(json).contentType(MediaType.APPLICATION_JSON))
            // then
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.id", is( 1 )));
        }
    }

    @Nested
    class UpdateProductResource {

        @Test
        void when_Put_Invalid_Values_then_Result_Error() throws Exception {

            // given
            final ProductRequest invalidRequest = ProductRequest.builder().title("").build();
            String json = jsonMapper.writeValueAsString(invalidRequest);

            // when
            mockMvc.perform(MockMvcRequestBuilders
                            .put("/product/{id}",1)
                            .content(json).contentType(MediaType.APPLICATION_JSON))
            // then
                    .andExpect(status().isBadRequest());
        }

        @Test
        void when_Put_Valid_Values_then_Result_Success() throws Exception {

            // given
            final ProductRequest request = ProductRequest.builder().title("Title 1").build();
            String json = jsonMapper.writeValueAsString(request);

            Mockito.when(service.update(Mockito.any()))
                    .thenReturn( ProductResponse.builder().id((BigInteger.ONE)).build());

            // when
            mockMvc.perform(MockMvcRequestBuilders
                            .put("/product/{id}",1)
                            .content(json).contentType(MediaType.APPLICATION_JSON))
            // then
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.id", is( 1 )));
        }

    }
}
