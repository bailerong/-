
import java.lang.reflect.Method;

class SPerson{
    private String name;
    private int age;
    public SPerson(){}
    public SPerson(String name,int age){
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
public class getNewMethod {
    public static void main(String[] args) throws Exception{
Class<?> cls=Class.forName("SPerson");
//任何时候调用类中的普通方法都需要实例化对象
        Object object=cls.newInstance();
//取得setName方法的实例化对象，并且设置方法名称与类型参数
        Method setMethod=cls.getMethod("setName",String.class);
        //随后需要通过Method类实例化对象，调用指定的方法
        setMethod.invoke(object,"lele");//相当于Person类，setName（lele）
        Method getMethod=cls.getMethod("getName");//相当于Person类，getName
        Object result=getMethod.invoke(object);
        System.out.println(result);
        }
    }

