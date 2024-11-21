package com.ochre.music.product.query;

import com.ochre.music.product.ProductEntity;
import com.ochre.music.product.ProductResponse;
import com.ochre.music.product.read.ProductReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductQueryService {

    private final ProductReadRepository readRepository;

    public ProductQueryService(@Autowired ProductReadRepository readRepository) {
        this.readRepository = readRepository;
    }

    public ProductResponse findByTitle(String title) {

        ProductEntity found = readRepository.findProductEntitiesByTitle(title);
        return ProductResponse.builder().id(found.getId()).title(found.getTitle()).build();
    }

    public ProductResponse findByGroupTitle(String productGroupTitle) {
        return null;
    }
}
