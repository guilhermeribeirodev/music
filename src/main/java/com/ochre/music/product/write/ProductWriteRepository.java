package com.ochre.music.product.write;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductWriteRepository extends JpaRepository<ProductWriteEntity, Long> {
}
