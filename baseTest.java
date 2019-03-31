/*
* 这是我们的一个关于基础操纵的代码，getter和setter方法
* */
class Emp{
    private String ename;
    private String job;

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
public class baseTest {
    public static void main(String[] args) {
        Emp emp=new Emp();
        emp.setEname("lele");
        emp.setJob("student");
        System.out.println(emp);
    }
}
