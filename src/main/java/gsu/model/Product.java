package gsu.model;
//Класс описывающий продукты продаваемые в магазине

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Product implements Comparable<Product>, Serializable {
    private String productName;
    private int price;

    @Override
    public String toString() {
        return productName + ":" + price;
    }

    @Override
    public int compareTo(Product o) {
        return Integer.compare(price, o.getPrice());
    }

    public void show(){
        System.out.println("Product " + productName + " price " + price);
    }

//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Product)) return false;
//        Product product = (Product) o;
//        return Double.compare(product.getPrice(), getPrice()) == 0 &&
//                Objects.equals(getProductName(), product.getProductName());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getPrice(), getProductName());
//    }
}
