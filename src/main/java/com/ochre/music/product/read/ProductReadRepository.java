package com.ochre.music.product.read;

import com.ochre.music.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReadRepository extends JpaRepository<ProductEntity, Long> {
}
