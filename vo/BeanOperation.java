package vo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
* 本类主要负责实现自动的VO匹配处理
*
*/
public class BeanOperation {
    private BeanOperation(){}
    /*
    * 负责设置类中的属性操作
    * actionObject表示当前发出设置请求的程序类的当前对象
    * msg属性的具体内容，格式为：“属性名称：|内容|属性名称：|内容..”
    * */
    public static void setBeanValue(Object actionObject,String msg) throws Exception {
        //要进行内容的设置，必须将字符串拆分
        //按照竖线拆分，取出所有要设置的内容
        String[] result=msg.split("\\|");
        //每次执行后只剩下“属性名称：内容”
        for(int i=0;i<result.length;i++){
            //需要针对属性以及内容做进一步的拆分
            String[] temp=result[i].split(":");
            //属性名称 :"emp.ename"
            String attribute=temp[0];
            //属性内容
            String value=temp[1];
            String[]  fields=attribute.split("\\.");
            //获取当前操作的简单java类对象
            Object currentObject=getObject(actionObject,fields[0]);
            //调用简单java类的setter方法
            setObjectValue(currentObject,fields[1],temp[1]);
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
    public static void setObjectValue(Object obj, String attribute,String value) throws Exception {
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
}

