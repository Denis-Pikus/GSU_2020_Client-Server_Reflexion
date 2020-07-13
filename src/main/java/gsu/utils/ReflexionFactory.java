package gsu.utils;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReflexionFactory<T> implements Factory<T> {
    private final List<Class<T>> someClass;
    private final ScannerWrapper sc = null;

//    public ReflexionFactory(List<Class<T>> someClass) {
//        this.someClass = someClass;
//    }

    @SneakyThrows
    public T create() {
        System.out.println("Choose class");
        for (int i = 0; i < someClass.size(); i++) {
            System.out.println( (i + 1)  + " " + someClass.get(i).getSimpleName());
        }
        int choice = sc.nextInt(1, 4);
        //for (Class<T> clazz: someClass) {
            //Field[] fields = clazz.getDeclaredFields();     //получаем все поля для класса
            List<Field> fieldsList = allFields(someClass.get(choice));
            T instance = someClass.get(choice).getConstructor().newInstance();  //получаем доступ к конструктору класса
            for (Field field : fieldsList) {
                System.out.println("input value for " + field.getName());
                String input = sc.nextLine();
                Object value = convert(field, input);
                field.setAccessible(true);
                field.set(instance, value);
            }
            return instance;
        //}
    }

    private List<Field> allFields(Class<T> clazz){
        List<Field> listOfFields = null;
        Field[] declaredFields;
        while ((declaredFields = clazz.getSuperclass().getDeclaredFields()) != null){
            listOfFields = Arrays.stream(declaredFields).collect(Collectors.toList());
        }
        listOfFields = (List<Field>) Arrays.stream(clazz.getClass().getDeclaredFields());
    //    listOfFields.forEach(System.out::println);
        return listOfFields;
    }

    private Object convert(Field field, String value){
        Class<?> type = field.getType();
        if (String.class.equals(type)){
            return value;
        }
        if (int.class.equals(type) || Integer.class.equals(type)){
            return Integer.valueOf(value);
        }
        if (long.class.equals(type) || Long.class.equals(type)){
            return Long.valueOf(value);
        }
        if (double.class.equals(type) || Double.class.equals(type)){
            return Double.valueOf(value);
        }
        if (type.isEnum()){
            Object[] constants = type.getEnumConstants();
            for (Object constant: constants) {
                if (((Enum) constant).name().equalsIgnoreCase(value))
                    return constant;
            }
            throw new IllegalArgumentException("wrong enum value");
        }
        //if (type.is)
        throw new IllegalArgumentException();
    }
}
