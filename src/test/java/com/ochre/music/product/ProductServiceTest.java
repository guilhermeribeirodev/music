package com.ochre.music.product;

import com.ochre.music.product.write.ProductWriteEntity;
import com.ochre.music.product.write.ProductWriteRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductWriteRepository repository;

    @Nested
    class CreateProductTests {

        @Test
        void when_Product_Is_Created_then_Return_A_Product_Response(){

            // given
            ProductRequest request = new ProductRequest("Title");
            ProductWriteEntity createdProduct = ProductHelperTest.buildProduct();
            createdProduct.setId(1L);
            when(repository.save(any())).thenReturn(createdProduct);

            // when
            ProductResponse response = service.create(request);

            // then
            assertEquals(createdProduct.getId(), response.getId());
        }
    }
}
