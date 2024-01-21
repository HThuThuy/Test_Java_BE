package fa.training.controllers;

import fa.training.models.Category;
import fa.training.models.Product;
import fa.training.security.services.CategoryService;
import fa.training.security.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(name = "category", required = false) Long categoryId) {
        List<Product> products;
        if (categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            products = productService.getProductsByCategory(category);
        } else {
            products = productService.getAllProducts();
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_PRODUCT')")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasAuthority('EDIT_PRODUCT')")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        Product updated = productService.updateProduct(productId, updatedProduct);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasAuthority('DELETE_PRODUCT')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


