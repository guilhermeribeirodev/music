package com.ochre.music.product;

import com.ochre.music.product.write.ProductWriteEntity;

import java.util.Calendar;

public class ProductHelperTest {

     public static ProductWriteEntity buildProduct(){
        return ProductWriteEntity.builder()
                //.id(1L)
                .title("Gravity")
                .distribution(ProductWriteEntity.Distribution.PHYSICAL)
                .mediaFormat(ProductWriteEntity.MediaFormat.CD)
                .price(ProductWriteEntity.Price.GBP)
                .releaseDate(Calendar.getInstance())
                .productGroupTitle("James Brown")
                .productGroupReleaseDate(Calendar.getInstance())
                .tags("tag1, tag2")
                .build();
    }
}
