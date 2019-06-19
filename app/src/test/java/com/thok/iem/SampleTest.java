package com.thok.iem;

import com.thok.iem.model.SearchDeviceRequest;
import com.thok.iem.model.UserBean;

import org.junit.Test;

import java.lang.reflect.Field;

public class SampleTest {
    @Test
    public void runTest(){
        UserBean userBean = new UserBean();
        userBean.setId("ffwr");
        userBean.setDelFlag("158833");
        Field[] fields = UserBean.class.getDeclaredFields();
        System.out.println(fields.length);
        for(Field field :fields){
            System.out.println(field.getName()+":"+field.getType().getName());
        }
    }
}
