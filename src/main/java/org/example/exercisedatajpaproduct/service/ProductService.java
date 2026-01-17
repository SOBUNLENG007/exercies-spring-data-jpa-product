package org.example.exercisedatajpaproduct.service;

import org.example.exercisedatajpaproduct.dto.dtoRequest.productRequest.DtoProductRequest;
import org.example.exercisedatajpaproduct.dto.dtoResponse.productRespones.DtoProductResponse;
import org.example.exercisedatajpaproduct.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    DtoProductResponse saveProduct(DtoProductRequest dtoProductRequest);

    Page<Product> getAllProduct(Pageable pageable);

    DtoProductResponse getProductById(Long id);

    DtoProductResponse updateProductById(DtoProductRequest dtoProductRequest, Long id);

    Product deleteProductById(Long id);
}
