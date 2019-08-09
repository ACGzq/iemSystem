package com.thok.iem.model;

public class RepairFinishRequest extends BaseRequest {

    /**
     * repairProcess : 线路出错，有漏电现象，导致主控主板过载烧坏
     * repairResult : 修复完成，1小时候可以启动
     * repairTime : 2019-06-19 14:25:31
     * repairUser : 老李
     */

    private String repairProcess;
    private String repairResult;
    private String repairTime;
    private String repairUser;

    public String getRepairProcess() {
        return repairProcess;
    }

    public void setRepairProcess(String repairProcess) {
        this.repairProcess = repairProcess;
    }

    public String getRepairResult() {
        return repairResult;
    }

    public void setRepairResult(String repairResult) {
        this.repairResult = repairResult;
    }

    public String getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(String repairTime) {
        this.repairTime = repairTime;
    }

    public String getRepairUser() {
        return repairUser;
    }

    public void setRepairUser(String repairUser) {
        this.repairUser = repairUser;
    }
}
