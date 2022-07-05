package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;
import ru.netology.domain.Book;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NotFoundException;
import ru.netology.exception.AlreadyExistsException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {
    private Book book = new Book(1, "1984", 300, "George Orwell");
    private Smartphone phone = new Smartphone(2, "Galaxy S21", 75000, "Samsung");
    private Smartphone newPhone = new Smartphone(3, "iPhone 12", 60000, "Apple");
    private Product[] products = new Product[]{book, phone};
    private ProductRepository repository = new ProductRepository(products);

    @Test
    public void shouldFindAll() {
        Product[] actual = repository.findAll();
        Product[] expected = products;
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSave() throws AlreadyExistsException {
        repository.save(newPhone);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{book, phone, newPhone};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldTrowAlreadyExistsException() {
        Assertions.assertThrows(AlreadyExistsException.class, () -> repository.save(phone));
    }

    @Test
    public void shouldRemoveById() {
        repository.removeById(2);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{book};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldTrowNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> repository.removeById(3));
    }
}
