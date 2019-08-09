package com.thok.iem.model;

import java.util.List;

public class PickDetailResponse extends BaseResponse {

    /**
     * data : {"id":"554eb9e5655542dd8100551644f3964b","pickNo":"BJSQ2019072311581600001","repairId":"4ad4bfeb815841cd9e9c84e7ccf4de34","pickUser":"打印机","createTime":"2019-07-23 11:58:17","updateTime":"2019-07-23 11:58:17","status":"VALID","delFlag":"0",
     * "details":[{"id":"0d60536ea4574bba9476f74ea5111be5","spareId":"68806762b476423cb626b8432037321b","statuses":"VALID","delFlag":"0","pickId":"554eb9e5655542dd8100551644f3964b","createTime":"2019-07-23 11:58:17","amount":5,
     * "spare":{"id":"68806762b476423cb626b8432037321b","spareNo":"YCSPARE2019062811035400002","spareName":"无人机","unit":"台","specifications":"4桨","number":67,"supplier":"普托罗斯","remarks":"","createBy":null,"status":0,"dr":0,"createTime":"2019-07-23 11:58:17","updateTime":"2019-06-28 19:03:54"}}],
     * "repair":{"id":"4ad4bfeb815841cd9e9c84e7ccf4de34","repairCode":"WX2019061902163600001","deviceId":"e5d4bcd31d2f4f7eb653e3b7d72efdc8","createBy":null,"createTime":"2019-06-19 14:16:37","dicId":null,"dicName":null,"content":"打印机            ",
     * "imgUrl":null,"status":"REPAIRED","delFlag":"0","repairUser":"张三","repairResult":"砸了","repairProcess":"修不了","reportUser":"打印机","reportTime":"2019-06-23 08:00:00","repairTime":"2016-01-01 08:00:00","acceptTime":null,"deviceVo":null}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 554eb9e5655542dd8100551644f3964b
         * pickNo : BJSQ2019072311581600001
         * repairId : 4ad4bfeb815841cd9e9c84e7ccf4de34
         * pickUser : 打印机
         * createTime : 2019-07-23 11:58:17
         * updateTime : 2019-07-23 11:58:17
         * status : VALID
         * delFlag : 0
         * details : [
         * {"id":"0d60536ea4574bba9476f74ea5111be5","spareId":"68806762b476423cb626b8432037321b","statuses":"VALID","delFlag":"0",pickId":"554eb9e5655542dd8100551644f3964b","createTime":"2019-07-23 11:58:17","amount":5,"
         * spare":{"id":"68806762b476423cb626b8432037321b","spareNo":"YCSPARE2019062811035400002","spareName":"无人机","unit":"台","specifications":"4桨","number":67,"supplier":"普托罗斯","remarks":"","createBy":null,"status":0,"dr":0,"createTime":"2019-07-23 11:58:17","updateTime":"2019-06-28 19:03:54"}
         * }]
         * repair : {"id":"4ad4bfeb815841cd9e9c84e7ccf4de34","repairCode":"WX2019061902163600001","deviceId":"e5d4bcd31d2f4f7eb653e3b7d72efdc8","createBy":null,"createTime":"2019-06-19 14:16:37","dicId":null,"dicName":null,"content":"打印机            ",
         * "imgUrl":null,"status":"REPAIRED","delFlag":"0","repairUser":"张三","repairResult":"砸了","repairProcess":"修不了","reportUser":"打印机","reportTime":"2019-06-23 08:00:00","repairTime":"2016-01-01 08:00:00","acceptTime":null,"deviceVo":null}
         */

        private String id;
        private String pickNo;
        private String repairId;
        private String pickUser;
        private String createTime;
        private String updateTime;
        private String status;
        private String delFlag;
        private RepairBean repair;
        private List<DetailsBean> details;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPickNo() {
            return pickNo;
        }

        public void setPickNo(String pickNo) {
            this.pickNo = pickNo;
        }

        public String getRepairId() {
            return repairId;
        }

        public void setRepairId(String repairId) {
            this.repairId = repairId;
        }

        public String getPickUser() {
            return pickUser;
        }

        public void setPickUser(String pickUser) {
            this.pickUser = pickUser;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public RepairBean getRepair() {
            return repair;
        }

        public void setRepair(RepairBean repair) {
            this.repair = repair;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public static class RepairBean {
            /**
             * id : 4ad4bfeb815841cd9e9c84e7ccf4de34
             * repairCode : WX2019061902163600001
             * deviceId : e5d4bcd31d2f4f7eb653e3b7d72efdc8
             * createBy : null
             * createTime : 2019-06-19 14:16:37
             * dicId : null
             * dicName : null
             * content : 打印机
             * imgUrl : null
             * status : REPAIRED
             * delFlag : 0
             * repairUser : 张三
             * repairResult : 砸了
             * repairProcess : 修不了
             * reportUser : 打印机
             * reportTime : 2019-06-23 08:00:00
             * repairTime : 2016-01-01 08:00:00
             * acceptTime : null
             * deviceVo : null
             */

            private String id;
            private String repairCode;
            private String deviceId;
            private Object createBy;
            private String createTime;
            private Object dicId;
            private Object dicName;
            private String content;
            private Object imgUrl;
            private String status;
            private String delFlag;
            private String repairUser;
            private String repairResult;
            private String repairProcess;
            private String reportUser;
            private String reportTime;
            private String repairTime;
            private Object acceptTime;
            private Object deviceVo;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRepairCode() {
                return repairCode;
            }

            public void setRepairCode(String repairCode) {
                this.repairCode = repairCode;
            }

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getDicId() {
                return dicId;
            }

            public void setDicId(Object dicId) {
                this.dicId = dicId;
            }

            public Object getDicName() {
                return dicName;
            }

            public void setDicName(Object dicName) {
                this.dicName = dicName;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(Object imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public String getRepairUser() {
                return repairUser;
            }

            public void setRepairUser(String repairUser) {
                this.repairUser = repairUser;
            }

            public String getRepairResult() {
                return repairResult;
            }

            public void setRepairResult(String repairResult) {
                this.repairResult = repairResult;
            }

            public String getRepairProcess() {
                return repairProcess;
            }

            public void setRepairProcess(String repairProcess) {
                this.repairProcess = repairProcess;
            }

            public String getReportUser() {
                return reportUser;
            }

            public void setReportUser(String reportUser) {
                this.reportUser = reportUser;
            }

            public String getReportTime() {
                return reportTime;
            }

            public void setReportTime(String reportTime) {
                this.reportTime = reportTime;
            }

            public String getRepairTime() {
                return repairTime;
            }

            public void setRepairTime(String repairTime) {
                this.repairTime = repairTime;
            }

            public Object getAcceptTime() {
                return acceptTime;
            }

            public void setAcceptTime(Object acceptTime) {
                this.acceptTime = acceptTime;
            }

            public Object getDeviceVo() {
                return deviceVo;
            }

            public void setDeviceVo(Object deviceVo) {
                this.deviceVo = deviceVo;
            }
        }

        public static class DetailsBean {
            /**
             * id : 0d60536ea4574bba9476f74ea5111be5
             * spareId : 68806762b476423cb626b8432037321b
             * statuses : VALID
             * delFlag : 0
             * pickId : 554eb9e5655542dd8100551644f3964b
             * createTime : 2019-07-23 11:58:17
             * amount : 5
             * spare : {"id":"68806762b476423cb626b8432037321b","spareNo":"YCSPARE2019062811035400002","spareName":"无人机","unit":"台","specifications":"4桨","number":67,"supplier":"普托罗斯","remarks":"","createBy":null,"status":0,"dr":0,"createTime":"2019-07-23 11:58:17","updateTime":"2019-06-28 19:03:54"}
             */

            private String id;
            private String spareId;
            private String statuses;
            private String delFlag;
            private String pickId;
            private String createTime;
            private int amount;
            private SpareBean spare;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSpareId() {
                return spareId;
            }

            public void setSpareId(String spareId) {
                this.spareId = spareId;
            }

            public String getStatuses() {
                return statuses;
            }

            public void setStatuses(String statuses) {
                this.statuses = statuses;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public String getPickId() {
                return pickId;
            }

            public void setPickId(String pickId) {
                this.pickId = pickId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public SpareBean getSpare() {
                return spare;
            }

            public void setSpare(SpareBean spare) {
                this.spare = spare;
            }

            public static class SpareBean {
                /**
                 * id : 68806762b476423cb626b8432037321b
                 * spareNo : YCSPARE2019062811035400002
                 * spareName : 无人机
                 * unit : 台
                 * specifications : 4桨
                 * number : 67
                 * supplier : 普托罗斯
                 * remarks :
                 * createBy : null
                 * status : 0
                 * dr : 0
                 * createTime : 2019-07-23 11:58:17
                 * updateTime : 2019-06-28 19:03:54
                 */

                private String id;
                private String spareNo;
                private String spareName;
                private String unit;
                private String specifications;
                private int number;
                private String supplier;
                private String remarks;
                private Object createBy;
                private int status;
                private int dr;
                private String createTime;
                private String updateTime;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getSpareNo() {
                    return spareNo;
                }

                public void setSpareNo(String spareNo) {
                    this.spareNo = spareNo;
                }

                public String getSpareName() {
                    return spareName;
                }

                public void setSpareName(String spareName) {
                    this.spareName = spareName;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public String getSpecifications() {
                    return specifications;
                }

                public void setSpecifications(String specifications) {
                    this.specifications = specifications;
                }

                public int getNumber() {
                    return number;
                }

                public void setNumber(int number) {
                    this.number = number;
                }

                public String getSupplier() {
                    return supplier;
                }

                public void setSupplier(String supplier) {
                    this.supplier = supplier;
                }

                public String getRemarks() {
                    return remarks;
                }

                public void setRemarks(String remarks) {
                    this.remarks = remarks;
                }

                public Object getCreateBy() {
                    return createBy;
                }

                public void setCreateBy(Object createBy) {
                    this.createBy = createBy;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getDr() {
                    return dr;
                }

                public void setDr(int dr) {
                    this.dr = dr;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public String getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(String updateTime) {
                    this.updateTime = updateTime;
                }
            }
        }
    }
}
