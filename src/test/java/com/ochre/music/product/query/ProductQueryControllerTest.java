package com.ochre.music.product.query;

import com.ochre.music.product.ProductResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigInteger;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProductQueryController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class ProductQueryControllerTest {

    @MockBean
    private ProductQueryService service;

    @Autowired
    private MockMvc mockMvc;

    @Nested
    class ReadProduct{

        @Test
        void when_Get_Product_By_Title_then_Response_Is_Successful() throws Exception {

            // given
            //final ProductQuery query = new ProductQuery("Title");
            final ProductResponse stubResponse = ProductResponse.builder().id(BigInteger.ONE).title("Title").build();
            when(service.findByTitle(Mockito.any()))
                    .thenReturn(stubResponse);

            // then
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/product?title={title}", "Title")
                            .contentType(MediaType.APPLICATION_JSON))// then
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title").value(stubResponse.getTitle()))
                    .andExpect(jsonPath("$.id").isNumber());
        }

        @Test
        void when_Get_By_Title_Invalid_then_Return_Bad_Request() throws Exception {

            // given
            String invalidTitle = "\\^*";

            // then
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/product?title={title}", invalidTitle)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void when_Get_By_Group_Title_then_Response_Is_Successful() throws Exception {

            // given
            final ProductResponse stub = ProductResponse.builder().id(BigInteger.ONE).title("Title").productGroupTitle("Group title").build();
            when(service.findByGroupTitle(Mockito.any()))
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
