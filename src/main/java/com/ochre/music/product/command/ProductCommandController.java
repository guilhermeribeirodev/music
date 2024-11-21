package com.ochre.music.product.command;

import com.ochre.music.product.ProductRequest;
import com.ochre.music.product.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.net.URI;

@RestController
@RequestMapping
public class ProductCommandController {

    private final ProductCommandService service;

    public ProductCommandController(@Autowired ProductCommandService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new product", description = "Create a new product",
            responses = {@ApiResponse(responseCode = "400", description = "Invalid Request data",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping("/product")
    @RolesAllowed("ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest request) {
        ProductResponse response = service.create(request);
        return ResponseEntity.created(URI.create(String.valueOf(response.getId()))).body(response);
    }

    @Operation(summary = "Updates an existing product", description = "Updates an existing product",
            responses = {@ApiResponse(responseCode = "400", description = "Invalid Request data",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @RolesAllowed("ADMIN")
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable("id") Long id, @RequestBody @Valid ProductRequest request) {
        ProductResponse response = service.update(BigInteger.valueOf(id), request);
        return ResponseEntity.ok(response);
    }
}
