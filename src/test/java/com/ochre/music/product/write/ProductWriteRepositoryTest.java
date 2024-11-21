package com.ochre.music.product.write;

import com.ochre.music.product.ProductEntity;
import com.ochre.music.product.ProductHelperTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ProductWriteRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductWriteRepository repository;

    @BeforeEach void setUp(){
        entityManager.clear();
    }

    @Test
    public void when_A_Valid_Product_Is_Created_then_Product_fields_should_match(){

        // given
        ProductEntity product = ProductHelperTest.buildProduct();

        // when
        ProductEntity created = repository.save(product);

        // then
        assertEquals(product, created);

    }

    @Test
    public void when_A_Product_Exists_then_Delete_Product_Is_Successful(){

        // given
        ProductEntity product = ProductHelperTest.buildProduct();
        entityManager.persist(product);
        entityManager.flush();

        // when
        repository.delete(product);

        // then
        assertEquals(0, repository.count());
    }

    @Test
    public void when_A_Product_Exists_then_Update_Product_Is_Successful(){

        // given
        ProductEntity product = ProductHelperTest.buildProduct();
        entityManager.persist(product);
        entityManager.flush();

        // when
        product.updatePrice(ProductEntity.Price.EUR);
        ProductEntity updated = repository.save(product);

        // then
        assertEquals(product, updated);
    }
}
