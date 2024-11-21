package com.ochre.music.product;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @MockBean private ProductService service;
    @Autowired private MockMvc mockMvc;
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Nested
    class CreateProductResource {

        @Test
        void when_Post_Invalid_Values_then_Result_Error() throws Exception {

            // given
            final ProductRequest request = new ProductRequest();
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
//        final ProductRequest request = new ProductRequest(
//                "Title", "PHYSICAL","CD",1.0d,
//                Calendar.getInstance(), "p title", Calendar.getInstance(),
//                "tags"
//        );
            // given
            final ProductRequest request = new ProductRequest("Title");
            String json = jsonMapper.writeValueAsString(request);

            Mockito.when(service.create(Mockito.any()))
                    .thenReturn(new ProductResponse(1L));

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
            final ProductRequest invalidRequest = new ProductRequest();
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
            final ProductRequest request = new ProductRequest("Title 1");
            String json = jsonMapper.writeValueAsString(request);

            Mockito.when(service.update(Mockito.any()))
                    .thenReturn(new ProductResponse(1L));

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
