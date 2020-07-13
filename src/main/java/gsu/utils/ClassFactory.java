package gsu.utils;

import gsu.model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClassFactory<T> implements Factory<T> {
    private String fileName;
    private List<GroceryStore> list;
    private Scanner sc;
    private TextFactory textFactory = new TextFactory();
    private List<GroceryStore> groceryStoreList = new ArrayList<>();
    private String storeName;

    public ClassFactory(String fileName) {
        this.fileName = fileName;
    }

//    public List<GroceryStore> getList() {
//        return this.list;
//    }

    GroceryStore store = null;
    public List<GroceryStore> loadFromFile() {
        try (FileInputStream fis = new FileInputStream(fileName);
             Scanner sc = new Scanner(fis)) {
            //считываем файл построчно
            while (sc.hasNextLine()) {
                String[] arr = textFactory.create(sc.nextLine());
                if (arr[0].equalsIgnoreCase("store")){
                    storeName = arr[1];
                    store = (GroceryStore) create();     //Создаем объект магазин
                    groceryStoreList.add(store);
                    store.setSquareStore(Integer.parseInt(arr[2]));
                }
                else {
                    if (!arr[0].equals("")){ //пока не встретиться пустая строка заполняем список продуктов
                        store.addProduct(new Product(arr[0], Integer.parseInt(arr[1])));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return groceryStoreList;
    }

    @Override
    public T create() {
        GroceryStore result = null;
        switch (this.storeName) {
            case "MeatStore":
                result = new MeatStore();
                break;
            case "FishStore":
                result = new FishStore();
                break;
            case "SweetStore":
                result = new SweetStore();
                break;
            case "VegetableStore":
                result = new VegetableStore();
                break;
//            default:
//                throw new IllegalArgumentException();
        }
        return (T) result;
    }
}
