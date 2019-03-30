import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/*
* 这是一个我们通过反射设置属性的代码
*
* */
class Person1{

    public String name;
}
public class SetField {
    public static void main(String[] args) throws Exception {
        Class<?> cls=Class.forName("Person1");
        Object object=cls.newInstance();
        //实例化操作我们的类属性
        Field nameField=cls.getDeclaredField("name");
        nameField.set(object,"lele");
        System.out.println(nameField.get(object));
    }
}
