package org.example.exercisedatajpaproduct.controller;

import lombok.AllArgsConstructor;
import org.example.exercisedatajpaproduct.apiResponse.ApiResponse;
import org.example.exercisedatajpaproduct.dto.dtoRequest.productRequest.DtoProductRequest;
import org.example.exercisedatajpaproduct.dto.dtoResponse.productRespones.DtoProductResponse;
import org.example.exercisedatajpaproduct.entity.Product;
import org.example.exercisedatajpaproduct.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.DoubleStream.builder;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;



    @PostMapping("/saveProduct")
    public ResponseEntity<ApiResponse<DtoProductResponse>> saveProduct(@RequestBody DtoProductRequest  dtoProductRequest){
        DtoProductResponse dtoProductResponse= productService.saveProduct(dtoProductRequest);
        ApiResponse<DtoProductResponse> apiResponse = ApiResponse.<DtoProductResponse>builder()
                .message("A new product is inserted successfully!")
                .payload(dtoProductResponse)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/getALlProduct")
    public ResponseEntity<ApiResponse<Object>> getAllProduct(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "3") int size,
            @RequestParam (defaultValue = "id") String sortBy,
            @RequestParam (defaultValue = "ASC") String direction
    ){
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.DESC.name())
                ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productService.getAllProduct(pageable);
        List<Product> productList = productPage.getContent();
        List<DtoProductResponse> dtoProductResponse = new ArrayList<>();
        for (Product product: productList) {
            DtoProductResponse dtoProductResponse1 = new DtoProductResponse();
            dtoProductResponse1.productResponse(product);
            dtoProductResponse.add(dtoProductResponse1);
        }
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("Get all products successfully.")
                .payload(dtoProductResponse)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<ApiResponse<Object>> getProductById(@PathVariable Long id){
        DtoProductResponse product = productService.getProductById(id);
        if (Objects.isNull(product)){
            ApiResponse<Object> apiResponse = ApiResponse.builder()
                    .message("Not found with id")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.ok(apiResponse);
        }else {
            ApiResponse<Object> apiResponse = ApiResponse.builder()
                    .message("Get product with id "+id+" successfully.")
                    .payload(product)
                    .status(HttpStatus.OK)
                    .build();
            return ResponseEntity.ok(apiResponse);
        }
    }

    @PutMapping("/updateProductById/{id}")
    public ResponseEntity<ApiResponse<DtoProductResponse>> updateProductById(
            @RequestBody DtoProductRequest dtoProductRequest,
            @PathVariable Long id
    ) {
        DtoProductResponse updatedProductDto = productService.updateProductById(dtoProductRequest, id);
        if (updatedProductDto == null) {
            ApiResponse<DtoProductResponse> apiResponse = ApiResponse.<DtoProductResponse>builder()
                    .message("Product with ID " + id + " not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        ApiResponse<DtoProductResponse> apiResponse = ApiResponse.<DtoProductResponse>builder()
                .message("Update successful.")
                .payload(updatedProductDto)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("Product with id "+ id +" is deleted successfully.")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }





 }
