package gsu.network;

import gsu.utils.Container;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NetworkClientContainer<T extends Serializable> implements Container<T> {

    private final Socket socket;
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;

    public NetworkClientContainer(String host, int port) throws IOException {
        socket = new Socket(host, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void add(T element) {
        Request request = new Request(Type.ADD, element);
        try {
            oos.writeObject(request);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void set(int index, T element) {
        Request request = new Request(Type.SET, new UpdatePayload(index, element));
        try{
            oos.writeObject(request);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int index) {
        Request request = new Request(Type.DELETE, index);
        try{
            oos.writeObject(request);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<T> getAll() {
        Request request = new Request(Type.GET, null);
        try {
            oos.writeObject(request);
            oos.flush();
            Response response = (Response) ois.readObject();
            return (Collection<T>) response.getPayload();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public int size() {
        Request request = new Request(Type.SIZE, null);
        try {
            oos.writeObject(request);
            oos.flush();
            Response response = (Response) ois.readObject();
            return (int) response.getPayload();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort() {
        Request request = new Request(Type.SORT, null);
        try {
            oos.writeObject(request);
            oos.flush();
            Response response = (Response) ois.readObject();
            List<T> list = (List<T>) response.getPayload();
            list.forEach(System.out::println);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Collection is empty!");
        }
    }

    @Override
    public void saveToFile(String fileName) {
        Request request = new Request(Type.SAVE_TO_FILE, fileName);
        try {
            oos.writeObject(request);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Collection is empty!");
        }
    }

    @Override
    public void loadFromFile(String fileName) {
        Request request = new Request(Type.LOAD_FROM_FILE, fileName);
        try {
            oos.writeObject(request);
            oos.flush();
//            Response response = (Response) ois.readObject();
//            List<T> list = (List<T>) response.getPayload();
//            list.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Collection is empty!");
        }
    }
}
