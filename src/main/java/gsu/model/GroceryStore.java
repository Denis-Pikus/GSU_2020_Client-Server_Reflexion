package gsu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@RequiredArgsConstructor
public abstract class GroceryStore<E extends GroceryStore<E>> extends GroceryStoreFactory implements Comparable<E> {
    private List<Product> products = new ArrayList<>();
    private int squareStore;
    private Product product;
    @JsonIgnore
    private Integer id;

//    public GroceryStore() { }
//
//    public GroceryStore(List<Product> products, int squareStore) {
//        this.products = products;
//        this.squareStore = squareStore;
//    }

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }
//
//    public int getSquareStore() {
//        return squareStore;
//    }
//
//    public void setSquareStore(int squareStore) {
//        if(squareStore > 0)
//               this.squareStore = squareStore;
//        else {
//            System.out.println("The store area cannot be less than or equal to zero!");
//            throw new IllegalArgumentException();
//        }
//    }

    public abstract void sale();

    @Override
    public int compareTo(GroceryStore o) {
        int result = Integer.compare(squareStore, o.getSquareStore());
        return result == 0 ? subCompare(o) : result;
    }

    private int subCompare(GroceryStore o){
        return Integer.compare(this.products.size(), o.products.size());
    }

    //Метод который формирует "правильный" формат вывода на экран списка товаров
    private String showList(){
        StringBuilder sb = new StringBuilder();
        for (Product product: products) {
            sb.append(product + "\n");
        }
        return sb.toString();
    }

    public void show(){
        System.out.println(this.getClass().getSimpleName() + "\n" +
                "square shop:" + squareStore + "\n" +
                "Products:\n" + showList());
    }

    public void addProduct(Product product) {
        products.add(this.product);
    }

    @Override
    public String toString() {
        return  "Store:" + this.getClass().getSimpleName() + ":" + squareStore + "\n" +
                showList();
    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof GroceryStore)) return false;
//        GroceryStore<?> that = (GroceryStore<?>) o;
//        return getSquareStore() == that.getSquareStore() &&
//                Objects.equals(getProducts(), that.getProducts());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getProducts(), getSquareStore());
//    }


}
