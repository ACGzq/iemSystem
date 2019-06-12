package com.thok.iem;

import com.thok.iem.model.SearchDeviceRequestBean;

import org.junit.Test;

public class SampleTest {
    @Test
    public void runTest(){
        SearchDeviceRequestBean sdrBean = new SearchDeviceRequestBean();
        sdrBean.setDeviceName("ffwr");
        sdrBean.setDeviceNum("158833");
        System.out.println(sdrBean.toString());
    }
}
