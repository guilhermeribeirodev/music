package com.ochre.music.product.query;

import com.ochre.music.product.ProductRequest;
import com.ochre.music.product.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping
public class ProductQueryController {

    private final ProductQueryService service;

    public ProductQueryController(@Autowired ProductQueryService service) {
        this.service = service;
    }

    @Operation( summary = "Retrieve products by query params", description = "Retrieve products by query params" )
    @RolesAllowed( {"ADMIN","USER"} )
    @GetMapping("/product")
    public ResponseEntity<ProductResponse> findProductByParam(
            @RequestParam("title") Optional<String> title,
            @RequestParam("groupTitle") Optional<String> groupTitle){

        if(title.isPresent() && title.get().matches("^[A-Za-z].*")){
            ProductRequest request =  ProductRequest.builder().title(title.get()).build();
            ProductResponse response = service.findByTitle(request.getTitle());
            return ResponseEntity.ok(response);
        }else if(groupTitle.isPresent() && groupTitle.get().matches(".*")){
            ProductRequest request =  ProductRequest.builder().productGroupTitle(groupTitle.get()).build();
            ProductResponse response = service.findByGroupTitle(request.getProductGroupTitle());
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
