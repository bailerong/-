package compy;

import java.time.LocalDateTime;

public class Dept {
    private String dname;
    private String loc;
    private Long count;//我们的员工的总数

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    private Company company=new Company();

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                ", count=" + count +
                ", company=" + company +
                '}';
    }
}

