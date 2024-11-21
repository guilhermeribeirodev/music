package com.ochre.music.product.command;

import com.ochre.music.product.ProductRequest;
import com.ochre.music.product.ProductResponse;
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
@RequestMapping( "/product")
public class ProductCommandController {

    private final ProductCommandService service;

    public ProductCommandController(@Autowired ProductCommandService service) {
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

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable("id") Long id, @RequestBody @Valid ProductRequest request) {
        ProductResponse response = service.update(request);
        return ResponseEntity.ok(response);
    }
}
