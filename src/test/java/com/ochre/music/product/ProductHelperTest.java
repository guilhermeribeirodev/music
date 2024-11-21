package com.ochre.music.product;

import com.ochre.music.product.vo.Price;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProductHelperTest {

     public static ProductEntity buildProduct(){
        List<ProductTag> tags = Arrays.asList(
                ProductTag.builder().name("tag1").build(),
                ProductTag.builder().name("tag2").build()
        );
        return ProductEntity.builder()
                //.id(1L)
                .title("Gravity")
                .distribution(ProductEntity.Distribution.PHYSICAL)
                .mediaFormat(ProductEntity.MediaFormat.CD)
                .price(new Price(BigDecimal.valueOf(100), Price.Currency.GBP))
                .releaseDate(new Date(Calendar.getInstance().getTimeInMillis()))
                .productGroupTitle("James Brown")
                .productGroupReleaseDate(Calendar.getInstance())
                .tags(tags)
                .build();
    }
}
