package gsu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MeatStore extends GroceryStore<MeatStore> {

//    public MeatStore() {}
//
//    public MeatStore(List<Product> products, int squareStore) {
//        super(products, squareStore);
//    }

    @Override
    public void sale() {
        System.out.println("Hello, here sell meats!");
    }

}
