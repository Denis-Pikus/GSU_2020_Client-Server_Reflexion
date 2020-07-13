package gsu.menu;

import gsu.utils.Container;

public class SizeMenuItem<T> implements MenuItem<T> {
    Container<T> container;

    public SizeMenuItem(Container<T> container) {
        this.container = container;
    }

    @Override
    public int getOrder() {
        return 5;
    }

    @Override
    public String getTitle() {
        return "Size";
    }

    @Override
    public void execute() {
        System.out.println("Size:" + container.size());
    }
}
