//package gsu;
//
//import gsu.menu.*;
//import gsu.model.*;
//import gsu.network.NetworkClientContainer;
//import gsu.utils.Container;
//import gsu.utils.Factory;
//import gsu.utils.ReflexionFactory;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ReflectionMain {
//    public static void main(String[] args) throws IOException {
//        Factory<?> factory = new ReflexionFactory<>(List.of(MeatStore.class,
//                FishStore.class, SweetStore.class, VegetableStore.class));
//        Container<GroceryStore<?>> container = new NetworkClientContainer<>("localhost", 8080);
//
//        List<MenuItem<GroceryStore<?>>> items = new ArrayList<>();
//        items.add(new AddMenuItem<>(container, factory));
//        items.add(new DeleteMenuItem<>(container));
//        items.add(new PrintAllMenuItem<>(container));
//        items.add(new UpdateMenuItem<>(container, factory));
//        items.add(new SizeMenuItem<>(container));
//        items.add(new SortMenuItem(container));
//        TopLevelMenu<GroceryStore<?>> menu1 = new TopLevelMenu<>(items, "transport menu", 0);
//        menu1.execute();
//    }
//}
