package com.ochre.music.product;

import lombok.Getter;

@Getter
public class ProductResponse {

    long id;

    public ProductResponse(long id) {
        this.id = id;
    }
}
