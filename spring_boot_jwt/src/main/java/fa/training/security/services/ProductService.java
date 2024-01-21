package fa.training.security.services;

import fa.training.models.Category;
import fa.training.models.Product;
import fa.training.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product createProduct(Product product) {

        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId).orElse(null);

        if (existingProduct != null) {
            // Update fields based on your requirements
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());

            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}



