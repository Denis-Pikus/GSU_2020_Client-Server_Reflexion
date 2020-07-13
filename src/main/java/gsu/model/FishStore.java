package gsu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FishStore extends GroceryStore<FishStore> {

//    public FishStore() {
//    }

//    public FishStore(List<Product> products, int squareStore) {
//        super(products, squareStore);
//    }

    @Override
    public void sale() {
        System.out.println("Hello, here sell fishes!");
    }

}
