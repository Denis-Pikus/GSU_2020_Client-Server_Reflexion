package gsu.model;

import gsu.utils.Factory;
import gsu.utils.ScannerWrapper;

import java.util.ArrayList;
import java.util.List;

public class GroceryStoreFactory implements Factory<GroceryStore<?>> {
    ScannerWrapper sc = new ScannerWrapper();
    public GroceryStore create() throws IllegalArgumentException {
        System.out.println("1 - create meat store");
        System.out.println("2 - create fish store");
        System.out.println("3 - create sweet store");
        System.out.println("4 - create vegetable store");
        int choice = sc.nextInt(1, 4);
        GroceryStore result;
        switch (choice) {
            case 1:
                result = new MeatStore();
                break;
            case 2:
                result = new FishStore();
                break;
            case 3:
                result = new SweetStore();
                break;
            case 4:
                result = new VegetableStore();
                break;
            default:
                throw new IllegalArgumentException("Wrong GroceryStore type:" + choice);
        }
        System.out.println("Enter square store:");
            try{
                result.setSquareStore(sc.nextInt());
            }catch (IllegalArgumentException e){
                System.out.println("Enter square store:");
                result.setSquareStore(sc.nextInt());
            }
            System.out.println("Enter food:");
            List<Product> products = new ArrayList<>();
            addProduct(products);
            result.setProducts(products);
        return result;
        }

//Метод формирующий список продуктов
        private void addProduct(List<Product> products){
            do {
                System.out.println("Enter product name:");
                String productName = sc.nextLine();
                System.out.println("Enter product price:");
                int price = sc.nextInt();
                products.add(new Product(productName, price));
                System.out.println("Are you input next product? y/n");
            }
            while (!sc.nextLine().equalsIgnoreCase("n"));
    }
}
