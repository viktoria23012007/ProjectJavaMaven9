package ru.netology.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductModelsTest {
    private Product product = new Product(1, "Something", 1000);
    private Product book = new Book(2, "1984", 300, "George Orwell");
    private Product phone = new Smartphone(3, "Galaxy S21", 75000, "Samsung");

    @ParameterizedTest
    @CsvSource({
            "By Name, thing, true",
            "Not Found, things, false"
    })
    public void shouldMatchesProduct(String name, String search, boolean expected) {
        boolean actual = product.matches(search);
        assertEquals(actual, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "By Name, 84, true",
            "By Author, well, true",
            "Not Found, Оруэлл, false",
    })
    public void shouldMatchesBook(String name, String search, boolean expected) {
        boolean actual = book.matches(search);
        assertEquals(actual, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "By Name, S21, true",
            "By Brand, Sam, true",
            "Not Found, Apple, false",
    })
    public void shouldMatchesSmartphone(String name, String search, boolean expected) {
        boolean actual = phone.matches(search);
        assertEquals(actual, expected);
    }
}
