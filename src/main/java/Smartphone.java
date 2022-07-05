package ru.netology.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Smartphone extends Product {
    private String brand;

    public Smartphone(int id, String name, int price, String brand) {
        super(id, name, price);
        this.brand = brand;
    }

    @Override
    public boolean matches(String search) {
        if (super.matches(search)) { // вызов метода matches в Product
            return true;
        }
        return brand.contains(search);
    }
}
