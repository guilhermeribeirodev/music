package com.ochre.music.product.command;

import com.ochre.music.product.ProductEntity;
import com.ochre.music.product.ProductRequest;
import com.ochre.music.product.ProductResponse;
import com.ochre.music.product.util.ProductMapper;
import com.ochre.music.product.write.ProductWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {

    private final ProductWriteRepository writeRepository;
    private final ProductMapper mapper;

    @Autowired
    public ProductCommandService( ProductWriteRepository writeRepository, ProductMapper mapper) {
        this.writeRepository = writeRepository;
        this.mapper = mapper;
    }

    public ProductResponse create(ProductRequest productRequest) {

        ProductEntity writeEntity = mapper.toEntity(productRequest);
        ProductEntity created = writeRepository.save(writeEntity);

        return ProductResponse.builder().id(created.getId()).build();
    }

    public ProductResponse update(ProductRequest productRequest){

        ProductEntity existingProduct = writeRepository.findById(productRequest.getId()).orElseThrow();
        ProductEntity updatedProduct = mapper.toEntity(productRequest);
        updatedProduct.setId(existingProduct.getId());

        ProductResponse response = mapper.toResponse(writeRepository.save(updatedProduct));

        return response;
    }
}
