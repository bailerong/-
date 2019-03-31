package vo;

public class EmpAction {
    private Emp emp=new Emp();
    public void setValue(String value) throws Exception {
        BeanOperation.setBeanValue(this,value);
    }
    public Emp getEmp(){
        return emp;
    }
}
