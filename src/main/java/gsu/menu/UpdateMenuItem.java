package gsu.menu;

import gsu.utils.Container;
import gsu.utils.Factory;
import gsu.utils.ScannerWrapper;

public class UpdateMenuItem<T> implements MenuItem<T> {
    private Container<T> container;
    private Factory<T> factory;
    private ScannerWrapper sc = new ScannerWrapper();

    public UpdateMenuItem(Container<T> container, Factory<T> factory) {
        this.container = container;
        this.factory = factory;
    }

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public String getTitle() {
        return "Update element";
    }

    @Override
    public void execute() {
        System.out.println("Input index");
        int index = sc.nextInt();
        container.set(index, factory.create());
    }
}
