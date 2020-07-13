package gsu.network;

import gsu.model.GroceryStore;
import gsu.utils.ClassFactory;
import gsu.utils.ScannerWrapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerContainer {

    private static ExecutorService executorService;
    private static CopyOnWriteArrayList<GroceryStore<?>> list = new CopyOnWriteArrayList<>();
    private static ScannerWrapper sc;

    public static void main(String[] args) throws IOException {
        executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("server started");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("new connection opened");
            process(socket);
        }
    }

    private static void process(Socket socket) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    ObjectInputStream ois  = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    System.out.println("ready to communicate");
                    while (true) {
                        communicate(ois, oos);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void communicate(ObjectInputStream ois, ObjectOutputStream oos) throws Exception {

        Request request = (Request) ois.readObject();
        System.out.println(request);

        switch (request.getType()) {
            case ADD: {
                GroceryStore<?> element = (GroceryStore<?>) request.getPayload();
                list.add(element);
                break;
            }
            case GET: {
                List<GroceryStore<?>> newList = List.copyOf(list);
                Response response = new Response((Serializable) newList);
                for (GroceryStore<?> groceryStore : newList) {
                    Thread.sleep(500);
                    System.out.println(groceryStore);
                }
                oos.writeObject(response);
                oos.flush();
                break;
            }
            case SET:{
                UpdatePayload updatePayload = (UpdatePayload) request.getPayload();
                list.set(updatePayload.getIndex(), (GroceryStore<?>) updatePayload.getElement());
                break;
            }
            case DELETE:{
                int index = (Integer) request.getPayload();
                list.remove(index);
                break;
            }
            case SIZE:{
                Response response = new Response(list.size());
                oos.writeObject(response);
                oos.flush();
                break;
            }

            case SORT:{
                List<GroceryStore<?>> newList = new ArrayList<>(list);
                Collections.sort(newList, GroceryStore::compareTo);
                Response response = new Response((Serializable) List.copyOf(newList));
                System.out.println("Sorted!");
                newList.forEach(System.out::println);
                oos.writeObject(response);
                oos.flush();
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + request.getType());

            case SAVE_TO_FILE:{
                String fileName = (String) request.getPayload();
                try (FileWriter fw = new FileWriter(fileName)) {
                    for (GroceryStore element : list) {
                        fw.write(element.toString() + "\n");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            case LOAD_FROM_FILE:{
                String fileName = (String) request.getPayload();
                ClassFactory<GroceryStore> classFactory;
                List<GroceryStore> listOfClass;
                try (FileWriter fw = new FileWriter(fileName)) {
                    classFactory = new ClassFactory<>(sc.nextLine());
                    listOfClass = classFactory.loadFromFile();
                    for (GroceryStore store: listOfClass) {
                        list.add(store);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
