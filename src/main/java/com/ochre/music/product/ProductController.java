package com.ochre.music.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService service;

    public ProductController(@Autowired ProductService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new product", description = "Create a new product",
            responses = {@ApiResponse(responseCode = "400", description = "Invalid Request data",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest request) {
        ProductResponse response = service.create(request);
        return ResponseEntity.created(URI.create(String.valueOf(response.getId()))).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable("id") Long id, @RequestBody @Valid ProductRequest request) {
        ProductResponse response = service.update(request);
        return ResponseEntity.ok(response);
    }
}
