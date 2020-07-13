package gsu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SweetStore extends GroceryStore<SweetStore> {

//    public SweetStore() {
//    }
//
//    public SweetStore(List<Product> products, int squareStore) {
//        super(products, squareStore);
//    }

    @Override
    public void sale() {
        System.out.println("Hello, here sell sweets!");
    }

}
