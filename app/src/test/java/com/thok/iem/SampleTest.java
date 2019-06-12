package com.thok.iem;

import com.thok.iem.model.SearchDeviceRequest;

import org.junit.Test;

public class SampleTest {
    @Test
    public void runTest(){
        SearchDeviceRequest sdrBean = new SearchDeviceRequest();
        sdrBean.setDeviceName("ffwr");
        sdrBean.setDeviceNum("158833");
        System.out.println(sdrBean.toString());
    }
}
