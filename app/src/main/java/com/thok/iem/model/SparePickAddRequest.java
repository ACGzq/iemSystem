package com.thok.iem.model;

import java.util.List;

public class SparePickAddRequest extends BaseRequest {

    /**
     * createBy : 周武
     * repairId : 1231adfa1ad
     * spareList : [{"id":"1231adf","number":"10"}]
     */

    private String createBy;
    private String repairId;
    private List<SparePickBean> spareList;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public List<SparePickBean> getSpareList() {
        return spareList;
    }

    public void setSpareList(List<SparePickBean> spareList) {
        this.spareList = spareList;
    }
}
