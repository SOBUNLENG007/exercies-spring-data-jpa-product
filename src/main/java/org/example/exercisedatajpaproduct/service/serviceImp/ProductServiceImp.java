package org.example.exercisedatajpaproduct.service.serviceImp;

import lombok.AllArgsConstructor;
import org.example.exercisedatajpaproduct.dto.dtoRequest.productRequest.DtoProductRequest;
import org.example.exercisedatajpaproduct.dto.dtoResponse.productRespones.DtoProductResponse;
import org.example.exercisedatajpaproduct.entity.Product;
import org.example.exercisedatajpaproduct.repository.ProductRepository;
import org.example.exercisedatajpaproduct.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {
    private ProductRepository productRepository;

    @Override
    public DtoProductResponse saveProduct(DtoProductRequest dtoProductRequest) {
        Product product = new Product();
        dtoProductRequest.productRequest(product);
        Product productSaved = productRepository.save(product);
        DtoProductResponse dtoProductResponse = new DtoProductResponse();
        dtoProductResponse.productResponse(productSaved);
        return dtoProductResponse;
    }

    @Override
    public Page<Product> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public DtoProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        DtoProductResponse dtoProductResponse = new DtoProductResponse();
        dtoProductResponse.productResponse(product);
        return dtoProductResponse;
    }

    @Override
    public DtoProductResponse updateProductById(DtoProductRequest dtoProductRequest, Long id) {
        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            dtoProductRequest.productRequest(product);
            Product updatedProduct = productRepository.save(product);
            DtoProductResponse dtoProductResponse = new DtoProductResponse();
            dtoProductResponse.productResponse(updatedProduct);

            return dtoProductResponse;
        }
        return null;
    }

    @Override
    public Product deleteProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            Product productDeleted = productOptional.get();
            productRepository.deleteById(id);
            return productDeleted;
        }else {
            return null;
        }
    }
}
