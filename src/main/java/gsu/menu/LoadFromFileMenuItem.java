package gsu.menu;

import gsu.utils.Container;
import gsu.utils.ScannerWrapper;

public class LoadFromFileMenuItem<T> implements MenuItem<T> {
    private Container<T> container;
    private ScannerWrapper sc = new ScannerWrapper();

    public LoadFromFileMenuItem(Container container) {
        this.container = container;
    }

    @Override
    public int getOrder() {
        return 8;
    }

    @Override
    public String getTitle() {
        return "Load from file";
    }

    @Override
    public void execute() {
        System.out.println("Enter file name");
        container.loadFromFile(sc.nextLine());

    }
}