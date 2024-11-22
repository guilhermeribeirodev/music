package com.ochre.music.product;

import com.ochre.music.product.vo.Price;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
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
        assertEquals(6, errors.size());
    }

    private static ProductRequest createRequest(){
        final Calendar futureDate = Calendar.getInstance();
        futureDate.add(Calendar.MONTH, 1);
        List<String> tags = Arrays.asList("tag1","tag2");
        return ProductRequest.builder()
                .title("Title")
                .releaseDate(futureDate)
                .distribution(ProductEntity.Distribution.PHYSICAL.name())
                .mediaFormat(ProductEntity.MediaFormat.CD.name())
                .price(new Price(BigDecimal.valueOf(130.0d), Price.Currency.GBP))
                .productGroupTitle("Group Title")
                .productGroupReleaseDate(futureDate)
                .tags(tags)
                .build();
    }
}
