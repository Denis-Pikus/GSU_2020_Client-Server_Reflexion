package gsu.menu;

import gsu.utils.Container;
import gsu.utils.ScannerWrapper;

public class SaveToFileMenuItem<T> implements MenuItem<T> {
    private Container<T> container;
    private ScannerWrapper sc = new ScannerWrapper();

    public SaveToFileMenuItem(Container container) {
        this.container = container;
    }

    @Override
    public int getOrder() {
        return 7;
    }

    @Override
    public String getTitle() {
        return "Save info to file";
    }

    @Override
    public void execute() {
        System.out.println("Enter file name");
        String file = sc.nextLine();
        container.saveToFile(file);
    }
}
