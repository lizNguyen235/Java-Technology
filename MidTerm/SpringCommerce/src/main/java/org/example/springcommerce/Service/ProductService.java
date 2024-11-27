package org.example.springcommerce.Service;


import org.example.springcommerce.Model.Product;
import org.example.springcommerce.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService  {
    @Autowired
    ProductRepo productRepo;

    public boolean addProduct(Product product) {
        productRepo.save(product);
        return true;
    }

    public Page<Product> getProducts(int page, int size) {
        return productRepo.findAll(PageRequest.of(page, size));
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    public void updateProduct(Product newProduct) {
        productRepo.save(newProduct);
    }
}
