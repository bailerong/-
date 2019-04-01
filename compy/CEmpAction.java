package compy;

public class CEmpAction {
    private Cemp cemp=new Cemp();
    public void SetValue(String value) throws Exception {
        CBean.setBeanValue(this,value);
    }
    public Cemp getCemp(){
        return cemp;
    }
}
