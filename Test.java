

public class Test {
    public static void main(String[] args) throws Exception {
        Class<?> cls=Class.forName("java.util.Date");
        Object obj=cls.newInstance();
        System.out.println(obj);
    }
}
