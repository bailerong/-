
interface IFruit{
    void eat();

}
class Apple implements IFruit{
    @Override
    public void eat() {
        System.out.println("eat an apple");
    }
}
class Orange implements IFruit{
    @Override
    public void eat() {
        System.out.println("eat an Orange");
    }

}
/*class Factor{
    private Factor(){}
    public static IFruit getInstance(String className){
        if(className.equals("apple")){
            return new Apple();
        }else if(className.equals("orange")){
            return new Orange();
        }
        return null;

    }*/
//}
//用反射修改我们的工厂方法
class Factor{
    private Factor(){}
    public static IFruit getInstance(String className){
        IFruit fruit=null;
        try {
            //取得任意子类的反射对象
            Class<?> cls=Class.forName(className);
            //通过反射取得实例化对象
            fruit=(IFruit) cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fruit;
    }
}
public class FactorTest {
    public static void main(String[] args) {
        IFruit fruit=Factor.getInstance("Apple");
        fruit.eat();
    }
}
