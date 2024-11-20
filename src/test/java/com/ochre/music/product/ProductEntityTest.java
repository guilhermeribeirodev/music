package com.ochre.music.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository repository;

    @Test
    public void when_A_Valid_Product_Is_Created_then_Product_fields_should_match(){

        // given
        ProductEntity product = ProductEntity.builder()
                .title("Gravity")
                .distribution(ProductEntity.Distribution.PHYSICAL)
                .mediaFormat(ProductEntity.MediaFormat.CD)
                .price(ProductEntity.Price.GBP)
                .releaseDate(Calendar.getInstance())
                .productGroupTitle("James Brown")
                .productGroupReleaseDate(Calendar.getInstance())
                .tags("tag1, tag2")
                .build();

        // when
        ProductEntity created = repository.save(product);

        // then
        assertEquals(product, created);

    }

    @Test
    public void when_A_Product_Exists_then_Delete_Product_Is_Successful(){

        // given

        ProductEntity product = ProductEntity.builder()
                .title("Gravity")
                .distribution(ProductEntity.Distribution.PHYSICAL)
                .mediaFormat(ProductEntity.MediaFormat.CD)
                .price(ProductEntity.Price.GBP)
                .releaseDate(Calendar.getInstance())
                .productGroupTitle("James Brown")
                .productGroupReleaseDate(Calendar.getInstance())
                .tags("tag1, tag2")
                .build();

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

        ProductEntity product = ProductEntity.builder()
                .title("Gravity")
                .distribution(ProductEntity.Distribution.PHYSICAL)
                .mediaFormat(ProductEntity.MediaFormat.CD)
                .price(ProductEntity.Price.GBP)
                .releaseDate(Calendar.getInstance())
                .productGroupTitle("James Brown")
                .productGroupReleaseDate(Calendar.getInstance())
                .tags("tag1, tag2")
                .build();

        entityManager.persist(product);
        entityManager.flush();

        // when

        product.updatePrice(ProductEntity.Price.EUR);

        ProductEntity updated = repository.save(product);

        // then
        assertEquals(product, updated);
    }

    @Test
    public void when_A_Product_Exists_then_Find_The_Product_With_Success(){

        // given

        ProductEntity product = ProductEntity.builder()
                .title("Gravity")
                .distribution(ProductEntity.Distribution.PHYSICAL)
                .mediaFormat(ProductEntity.MediaFormat.CD)
                .price(ProductEntity.Price.GBP)
                .releaseDate(Calendar.getInstance())
                .productGroupTitle("James Brown")
                .productGroupReleaseDate(Calendar.getInstance())
                .tags("tag1, tag2")
                .build();

        entityManager.persist(product);
        entityManager.flush();

        // when

        List<ProductEntity> listOfProducts = repository.findAll();

        // then
        assertEquals(product, listOfProducts.get(0));
    }
}
