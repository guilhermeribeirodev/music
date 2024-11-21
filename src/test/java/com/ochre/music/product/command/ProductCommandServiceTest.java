package com.ochre.music.product.command;

import com.ochre.music.product.ProductEntity;
import com.ochre.music.product.ProductHelperTest;
import com.ochre.music.product.ProductRequest;
import com.ochre.music.product.ProductResponse;
import com.ochre.music.product.util.ProductMapper;
import com.ochre.music.product.vo.Price;
import com.ochre.music.product.write.ProductWriteRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductCommandServiceTest {

    @InjectMocks
    private ProductCommandService service;

    @Mock
    private ProductWriteRepository repository;

    @Mock
    private ProductMapper mapper;

    @Nested
    class CreateProduct {

        @Test
        void when_A_Valid_Product_Is_Created_then_Return_A_Successful_Response(){

            // given
            ProductRequest request = ProductRequest.builder()
                    .title("Title")
                    .distribution("Physical")
                    .mediaFormat("CD")
                    .price(new Price(BigDecimal.valueOf(100), Price.Currency.GBP))
                    .productGroupTitle("Group Title")
                    .build();

            ProductEntity createdProduct = ProductHelperTest.buildProduct();
            createdProduct.setId(BigInteger.ONE);
            when(repository.save(any())).thenReturn(createdProduct);

            // when
            ProductResponse response = service.create(request);

            // then
            assertEquals(createdProduct.getId(), response.getId());
        }

        @Test
        void when_A_Not_Valid_Product_Is_Created_then_Return_Validation_Error(){

            // given
            ProductRequest request = ProductRequest.builder().title("%&*%").build();

            // then
            assertThrows(Exception.class, () -> service.create(request));
        }
    }

    @Nested
    class UpdateProduct {

        @Test
        void when_A_Valid_Product_Is_Updated_then_Return_A_Successful_Response(){

            // given
            ProductRequest request = ProductRequest.builder()
                    //.id(BigInteger.ONE)
                    .title("New Title")
                    .distribution("Physical")
                    .mediaFormat("CASSETE")
                    .price(new Price(BigDecimal.valueOf(100), Price.Currency.GBP))
                    .productGroupTitle("Group Title")
                    .build();

            ProductResponse mappedResponse = ProductResponse.builder()
                    //.id(request.getId())
                    .title(request.getTitle())
                    .distribution(request.getDistribution())
                    .mediaFormat(request.getMediaFormat())
                    .price(request.getPrice())
                    .productGroupTitle(request.getProductGroupTitle())
                    .build();

            ProductEntity updatedProduct = ProductHelperTest.buildProduct();
            updatedProduct.setId(BigInteger.ONE);
            when(repository.findById(any())).thenReturn(Optional.of(updatedProduct));
            when(mapper.toEntity(any())).thenReturn(updatedProduct);
            when(mapper.toResponse(any())).thenReturn(mappedResponse);
            when(repository.save(any())).thenReturn(updatedProduct);

            // when
            ProductResponse response = service.update(updatedProduct.getId(), request);

            // then
            assertEquals(request.getTitle(), response.getTitle());
            assertEquals(request.getDistribution(), response.getDistribution());
            assertEquals(request.getMediaFormat(), response.getMediaFormat());
            assertEquals(request.getPrice(), response.getPrice());
            assertEquals(request.getProductGroupTitle(), response.getProductGroupTitle());

        }
    }
}
