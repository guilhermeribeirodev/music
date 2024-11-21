package com.ochre.music.product;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductRequestTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void when_Product_Request_Is_Valid_then_Empty_Errors(){

        ProductRequest request = createRequest();
        Set<ConstraintViolation<ProductRequest>> errors = validator.validate(request);
        assertTrue(errors.isEmpty(), "Should not have validation errors");
    }

    @Test
    public void when_Product_Request_Is_Not_Valid_then_Show_Errors(){

        ProductRequest request = ProductRequest.builder().title("").build();
        Set<ConstraintViolation<ProductRequest>> errors = validator.validate(request);
        assertEquals(5, errors.size());
    }

    private static ProductRequest createRequest(){
        return ProductRequest.builder()
                .title("Title")
                .distribution(ProductEntity.Distribution.PHYSICAL.name())
                .mediaFormat(ProductEntity.MediaFormat.CD.name())
                .build();
    }
}
