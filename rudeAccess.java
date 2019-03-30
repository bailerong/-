import java.lang.reflect.Field;

/*
* 这是我们的一个破坏类中属性封装的代码
*/
class Person2{
    private String name;
}
public class rudeAccess {
    public static void main(String[] args) throws Exception {
        Class<?> cls=Class.forName("Person2");
        Object object=cls.newInstance();
        Field field=cls.getDeclaredField("name");
        field.setAccessible(true);
        field.set(object,"lele");
        System.out.println(field.get(object));
    }
}
