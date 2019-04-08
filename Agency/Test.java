package Agency;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/*
* 这是我们的一个非常基础的，
* 我们的代理模式*/
interface ISubject{//核心操作接口
    public void eat();//吃饭是核心业务
}
//这是我们的一个真实的业务类
class RealSubject implements ISubject{
    @Override
    public void eat() {
        System.out.println("我饿了，我要吃饭");
    }
}
//我们的代理辅助业务
//我们的业务有很多我们的准备和善后工作，但是我们吃饭的人只在乎吃，并不在乎这些辅助的业务
//所以我们要做的就是，我们要根据我们的真实业务，
// 我们只需要找到我们的真实的业务，我们剩下的会由我们的代理辅助业务帮我们自动完成
class ProxySubject implements ISubject{
    private ISubject subject;
    public ProxySubject(ISubject subject){
        this.subject=subject;
    }
    public void prepare(){
        System.out.println("饭前先收拾食材");
    }
    public void afterEat(){
        System.out.println("洗碗");
    }
    @Override
    public void eat() {
        this.prepare();
        this.subject.eat();//核心是吃
        this.afterEat();
    }
}
//我们的工厂类
class Factory{
    private Factory(){}
    public static <T> T getInstance(String className){
        T t=null;
        try {
            t=(T)Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return t;
    }
    public static <T> T getInstance(String className,Object obj){
        T t=null;
        try {
            Constructor<?> cons=Class.forName(className).
                    getConstructor(obj.getClass().getInterfaces()[0]);
            t=(T)cons.newInstance(obj);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return t;

    }
}
public class Test {
    public static void main(String[] args) {
        ISubject subject= Factory.getInstance
                ("Agency.ProxySubject",Factory.getInstance("Agency.RealSubject"));
        subject.eat();
    }
}
