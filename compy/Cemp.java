package compy;

import java.util.Date;

public class Cemp {
    private String ename;

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    private String job;
    private Double salary;
    private Date hireDate;

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    private Dept dept=new Dept();



    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Cemp{" +
                "ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                ", dept=" + dept +
                '}';
    }
}

