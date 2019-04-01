package compy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;




        import java.lang.reflect.Field;
        import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/*
 * 本类主要负责实现自动的VO匹配处理
 *
 */
public class CBean {
    private CBean(){}
    /*
     * 负责设置类中的属性操作
     * actionObject表示当前发出设置请求的程序类的当前对象
     * msg属性的具体内容，格式为：“属性名称：|内容|属性名称：|内容..”
     * */

    public static void setBeanValue(Object actionObject,String msg) throws Exception {
       //要想进行内容设置，必须将字符串拆分
        //按照竖线拆分，取出所有要设置的内容
        String[] result=msg.split("\\|");
        //每次执行后只剩下“属性名称：内容”
        for(int i=0;i<result.length;i++){
            //需要针对属性名称及内容做进一步的拆分
            String[] temp=result[i].split(":");
            //属性名称“emp.ename”
            String attribute=temp[0];
            //属性内容
            //String value=temp[1];
            //通过属性的拆分就可以区分出来是单级还是多级
            String[] fields=attribute.split("\\.");
            if(fields.length>2){
                //表明是多级VO操作
                //如果要想通过多级确定出属性的操作对象，那么就应该一层层找出每一个getter方法返回的内容
                Object currentObject=actionObject;
                for(int x=0;x<fields.length-1;x++){
                    //对应getter的返回对象
                    //循环结束一定能拿到最后一层的当前对象
                    currentObject=getObject(currentObject,fields[x]);
                }
                //属性内容
                Object value=getValue(currentObject,fields[fields.length-1],temp[1]);
                setObjectValue(currentObject,fields[fields.length-1],value);
            }else{
                //单级VO
                //获取当前操作的简单java类对象
                Object value =getValue(actionObject,attribute,temp[1]);
                Object currentObject=getObject(actionObject,fields[0]);
                //调用简单java类的setter方法
                setObjectValue(currentObject,fields[1],value);
            }
        }
    }
    /*
    将给定的字符串的首字母大写
    str 给定的字符串
    return 返回首字母大写的字符串
    * */
    public static String initCap(String str){
        return str.substring(0,1).toUpperCase()+str.substring(1);
    }
    /*
     *负责调用XXXAction类中的getter方法取得简单的java类对象
     * obj 表示调用方法所在类对象
     * attribute表示属性名称
     * return 调用对象结果
     */
    public static Object getObject(Object obj,String attribute) throws Exception {
        String methodName="get"+initCap(attribute);
        //调用指定属性的Field对象，目的是取得对象类型，
        // 如果没有此属性意味着操作无法继续进行
        Field field=obj.getClass().getDeclaredField(attribute);
        if(field==null){
            //第二次尝试从父类中取得该属性
            field=obj.getClass().getField(attribute);
        }
        if(field==null){
            //如果两次都没有取得指定的属性，表示该对象一定不存在
            return null;
        }
        Method method=obj.getClass().getMethod(methodName);
        return method.invoke(obj);
    }
    /*
    根据指定的类对象设置指定类中的书香，调用setter方法
    obj 属性所在类的实例化对象
    attribute 属性名称
    value  属性内容
    */
    public static void setObjectValue(Object obj, String attribute,Object value) throws Exception {
        Field field=obj.getClass().getDeclaredField(attribute);
        if(field==null){
            obj.getClass().getField(attribute);
        }
        if(field==null){
            return ;
        }
        String methodName="set"+initCap(attribute);
        Method setMethod=obj.getClass().getMethod(methodName,field.getType());
        setMethod.invoke(obj,value);
    }

    /*
    * 将字符串的内容根据书香类型变为各种数据类型
    * 支持的类型：int double、long、String、Date
    * wrapObject包装类
    * attribute 属性
    * value 属性值
    * 根据属性类型进行转型处理
    *
    * */
    public static Object getValue(Object wrapObject,String attribute,String value) throws Exception {
       Field field=wrapObject.getClass().getDeclaredField(attribute);
       if(field==null){
           field=wrapObject.getClass().getField(attribute);
       }
       if(field==null){
           return null;
       }
        return stringToType(field.getType().getSimpleName(),value);
    }
    /*
    根据指定字符串做转型处理
    type 数据类型
    value 数据内容
    return 具体准换内容
    */
    private static Object stringToType(String type,String value) throws ParseException {
        if("String".equals(type)){
            if(isNotNull(value)){
                return value;
            }else{
                return null;
            }
        }else if("int".equals(type)||"Integer".equals(type)) {
            if (isInt(value)) {
                return Integer.parseInt(value);
            }
        }else if("double".equals(type)||"Double".equals(type)){
                if(isDouble(value)){
                    return Double.parseDouble(value);
                }
            }else if("long".equals(type)||"Long".equals(type)){
            if(isDouble(value)){
                return Long.parseLong(value);
            }
        }else if("Date".equals(type)){
            //数据类型为Date
            String parrent=null;
            if(isDate(value)){

            parrent="yyyy-MM-dd";
            }
            if(parrent!=null){
                return new SimpleDateFormat(parrent).parse(value);
            }
        }
        return null;
    }
    /*
    * 判断字符串是否为空
    str 要判断的字符串
    return 如果字符串危机哦那个，返回false；否则 返回true
    */
    private static boolean isNotNull(String str){
        return !(str==null||str.isEmpty()||"".equals(str));
    }
    private static boolean isInt(String str){
        if(isNotNull(str)){
            return str.matches("\\d+");
        }
        return false;
    }
    private static boolean isDouble(String str){
    if(isNotNull(str)){
        return str.matches("\\d+(\\.\\d+)?");
    }
    return false;
    }
    private static boolean isDate(String str){
        if(isNotNull(str)){
            return str.matches("\\d{4}-\\{2}-\\d{2};");
        }
        return false;
    }
}

