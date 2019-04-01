package compy;

public class CTest {
    public static void main(String[] args) throws Exception {
        String value = "cemp.ename:yuisama|cemp.job:Java Coder|cemp.dept.dname:教务"+
        "部|cemp.dept.company.cname:bit"+
        "|cemp.salary:1999.12|cemp.hireDate:2017-10-"+
       "10|cemp.dept.count:100000|cemp.dept.company.cid:10|cemp.dept.company.createDate:1999-09-10";
        CEmpAction cEmpAction=new CEmpAction();
        cEmpAction.SetValue(value);
        System.out.println(cEmpAction.getCemp());
    }
}
