package com.ochre.music.product.util;

import com.ochre.music.product.ProductEntity;
import com.ochre.music.product.ProductRequest;
import com.ochre.music.product.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final ModelMapper mapper;

    public ProductMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ProductEntity toEntity(ProductRequest request){
        return mapper.map(request, ProductEntity.class);
    }

    public ProductResponse toResponse(ProductEntity entity){
        return mapper.map(entity, ProductResponse.class);
    }
}
