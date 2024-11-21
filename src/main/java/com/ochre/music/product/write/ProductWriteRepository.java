package com.ochre.music.product.write;

import com.ochre.music.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProductWriteRepository extends JpaRepository<ProductEntity, BigInteger> {
}
