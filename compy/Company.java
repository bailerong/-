package compy;

import javax.sql.DataSource;
import java.util.Date;

public class Company {
    private String cname;
    private String address;
    private Integer cid;
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Company{" +
                "cname='" + cname + '\'' +
                ", address='" + address + '\'' +
                ", cid=" + cid +
                ", createDate=" + createDate +
                '}';
    }
}

