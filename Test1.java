import java.util.Date;

public class Test1 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //Date date=new Date();
        //1、取得类的Class对象
        Class<Date> cls=(Class<Date>) Class.forName("java.util.Date");
        //通过反射取得Date类实例化对象
        Date date=cls.newInstance();
        System.out.println(date);
    }
}
