package gsu.menu;

import gsu.utils.Container;

import java.util.List;

public class SortMenuItem<T extends Comparable<T>> implements MenuItem<T> {
    Container<T> container;

    public SortMenuItem(Container<T> container) {
        this.container = container;
    }

    @Override
    public int getOrder() {
        return 6;
    }

    @Override
    public String getTitle() {
        return "Sort list";
    }

    @Override
    public void execute() {
        List<T> list = (List<T>) container.getAll();
        container.sort();
        list.stream().forEach(System.out::println);
    }
}
