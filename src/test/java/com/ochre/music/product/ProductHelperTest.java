package com.ochre.music.product;

import java.util.Calendar;

public class ProductHelperTest {

     public static ProductEntity buildProduct(){
        return ProductEntity.builder()
                //.id(1L)
                .title("Gravity")
                .distribution(ProductEntity.Distribution.PHYSICAL)
                .mediaFormat(ProductEntity.MediaFormat.CD)
                .price(ProductEntity.Price.GBP)
                .releaseDate(Calendar.getInstance())
                .productGroupTitle("James Brown")
                .productGroupReleaseDate(Calendar.getInstance())
                .tags("tag1, tag2")
                .build();
    }
}
