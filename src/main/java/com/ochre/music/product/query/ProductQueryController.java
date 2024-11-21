package com.ochre.music.product.query;

import com.ochre.music.product.ProductRequest;
import com.ochre.music.product.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping( "/productQuery")
public class ProductQueryController {

    private final ProductQueryService service;

    public ProductQueryController(@Autowired ProductQueryService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<ProductResponse> findProductByParam(
            @RequestParam("title") Optional<String> title,
            @RequestParam("groupTitle") Optional<String> groupTitle){

        ProductRequest request =  ProductRequest.builder().title(title.get()).build();
        ProductResponse response = service.findByTitle(request.getTitle());
        return ResponseEntity.ok(response);
    }
}
