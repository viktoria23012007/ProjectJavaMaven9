package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.netology.domain.Product;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;

import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
public class ProductRepository {
    private Product[] products = new Product[0];

    public Product[] findAll() {
        return this.products;
    }

    public void save(Product product) throws AlreadyExistsException {
        int id = product.getId();
        if (this.findById(id) != null) {
            throw new AlreadyExistsException("Element with ID " + id + " is already exists");
        }
        int length = products.length + 1;
        Product[] tmp = Arrays.copyOf(products, length);
        tmp[length - 1] = product;
        products = tmp;
    }

    public void removeById(int id) throws NotFoundException {
        if (this.findById(id) == null) {
            throw new NotFoundException("Element with ID " + id + " not found");
        }
        int length = products.length - 1;
        Product[] tmp = new Product[length];
        int index = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[index] = product;
                index++;
            }
        }
        products = tmp;
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
