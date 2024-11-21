package com.ochre.music.product.read;

import com.ochre.music.product.ProductEntity;
import com.ochre.music.product.ProductHelperTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ProductReadRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductReadRepository repository;

    @BeforeEach
    void setUp(){
        entityManager.clear();
    }

    @Test
    public void when_A_Product_Exists_then_Find_The_Product_With_Success(){

        // given
        ProductEntity product = ProductHelperTest.buildProduct();
        entityManager.persist(product);
        entityManager.flush();

        // when
        List<ProductEntity> listOfProducts = repository.findAll();

        // then
        assertEquals(product, listOfProducts.get(0));
    }
}
