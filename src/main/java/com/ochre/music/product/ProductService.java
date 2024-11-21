package com.ochre.music.product;

import com.ochre.music.product.write.ProductWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductWriteRepository writeRepository;

    public ProductService(@Autowired ProductWriteRepository writeRepository) {
        this.writeRepository = writeRepository;
    }

    public ProductResponse create(ProductRequest productRequest) {

        ProductEntity writeEntity = ProductEntity.builder()
                .title(productRequest.getTitle())
//                .distribution(ProductWriteEntity.Distribution.PHYSICAL)
//                .mediaFormat(ProductWriteEntity.MediaFormat.CD)
//                .price(ProductWriteEntity.Price.GBP)
//                .releaseDate(Calendar.getInstance())
//                .productGroupTitle(productRequest.getProductGroupTitle())
//                .productGroupReleaseDate(Calendar.getInstance())
//                .tags("tag1, tag2")
                .build();

        ProductEntity created = writeRepository.save(writeEntity);

        return new ProductResponse(created.getId());
    }

    public ProductResponse update(ProductRequest productRequest){
        return null;
    }
}
