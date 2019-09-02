package com.thok.iem;

import com.thok.iem.model.SearchDeviceRequest;
import com.thok.iem.model.UserBean;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SampleTest {
    private static String s = "http://192.168.1.102:8088/img/save/1565161950683.jpg";
    private static final  String testStr = s+"12345";
    private ArrayList<String> list1;
    ArrayList<String> list2;
    @Test
    public void runTest(){
      /*  UserBean userBean = new UserBean();
        userBean.setId("ffwr");
        userBean.setDelFlag("158833");
        Field[] fields = UserBean.class.getDeclaredFields();
        System.out.println(fields.length);
        String url = "www.HH.JPG";
        System.out.println(url.contentEquals("jpg"));
        for(Field field :fields){
            System.out.println(field.getName()+":"+field.getType().getName());
        }*/
        System.out.println(s.toLowerCase().contains("jpg"));
        s = "233";
        System.out.println(testStr);
        ArrayList<String> list3 = new ArrayList<>();
        list3.add("a");
        list3.add("b");
        list3.add("c");
        list1 = list3;
        list2 = list1;
        list3.add("d");
        list1.forEach(str->System.out.println(str));
        list1.add("e");
        list2.forEach(str->System.out.println(str));
    }
}
