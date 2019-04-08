package AutoAgency;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface ISubject{//核心业务
    public void eat(String msg,int num);
    //吃饭就是我们的核心业务
}
class RealSubject implements ISubject{
    @Override
    public void eat(String msg, int num) {
        System.out.println("我要吃"+num+"分量的"+msg);
    }
}
class ProxySubject implements InvocationHandler{
    //绑定任意接口的对象，使用object描述
    private Object target;
    /*
    * 实现真实对象的绑定处理，同时返回代理对象
    * target
    * return 返回一个代理对象
    * （这个对象是根据接口定义东陶创建生成的代理对象）*/
    public Object bind(Object target){
        //保存真实主题对象
        this.target=target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),this);
    }
    public void preHandle(){
        System.out.println("方法处理前");
    }
    public void afterHandle(){
        System.out.println("方法处理后");
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.preHandle();
        //反射调用方法
        Object ret=method.invoke(this.target,args);
        this.afterHandle();
        return ret;
    }
}
    public class Test {
        public static void main(String[] args) {
            ISubject subject=(ISubject)new ProxySubject().bind(new RealSubject());
            subject.eat("宫保鸡丁",20);
        }
}
