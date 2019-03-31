import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Person{
    private String name;
    private int age;
    public Person(String name,int age){
        this.name=name;
        this.age=age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
public class uniqueConstructor {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<?> cls=Person.class;

        System.out.println(cls.newInstance());

    }
}
