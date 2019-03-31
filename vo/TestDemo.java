package vo;

public class TestDemo {
    public static void main(String[] args) throws Exception {
        String value="emp.name:lele|emp.subject:java Coder";
        EmpAction empAction=new EmpAction();
        empAction.setValue(value);
        System.out.println(empAction.getEmp());
    }
}
