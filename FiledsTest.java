import java.lang.reflect.Field;

/*
* 这是一个我们通过反射获取类属性的代码
* */
class Teacher{
    public int age;
    public String name;
}
class Student extends Teacher{
    private String school;
}
public class FiledsTest {
    public static void main(String[] args) throws Exception {
    Class<?> cls=Class.forName("Student");
        {
            Field[] fields=cls.getFields();
            for(Field field:fields){
                System.out.println(field);
            }
        }
        System.out.println("-----------------");
        {
            Field[] fields=cls.getDeclaredFields();
            for(Field field:fields){
                System.out.println(field);
            }
        }
    }
}
