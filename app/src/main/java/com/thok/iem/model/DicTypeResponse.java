package com.thok.iem.model;

import java.util.List;

public class DicTypeResponse extends BaseResponse {

    /**
     * data : {"typeDic":{"id":"9ceeb54924874c30967a19542af677ad","name":"设备状态","dicType":"SBZT0001","createTime":"2019-06-14T02:46:54.000+0000","parentId":"","status":"VALID","delFlag":"0","createBy":null,"dataLevel":1},"list":[{"id":"a93f42334670488fa647a854a0b9d8b5","name":"保养","dicType":"ZT0004","createTime":"2019-06-14T02:48:24.000+0000","parentId":"9ceeb54924874c30967a19542af677ad","status":"VALID","delFlag":"0","createBy":null,"dataLevel":2},{"id":"fd529e2cecd64f4497c27646176f95ad","name":"维修","dicType":"ZT0003","createTime":"2019-06-14T02:47:52.000+0000","parentId":"9ceeb54924874c30967a19542af677ad","status":"VALID","delFlag":"0","createBy":null,"dataLevel":2},{"id":"2f97caf8fae541d3ba1e35627495b3b5","name":"停机","dicType":"ZT0002","createTime":"2019-06-14T02:47:39.000+0000","parentId":"9ceeb54924874c30967a19542af677ad","status":"VALID","delFlag":"0","createBy":null,"dataLevel":2},{"id":"f48a18e15bf64802a723722fa2147275","name":"运行","dicType":"ZT0001","createTime":"2019-06-14T02:47:21.000+0000","parentId":"9ceeb54924874c30967a19542af677ad","status":"VALID","delFlag":"0","createBy":null,"dataLevel":2}]}
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
         * typeDic : {"id":"9ceeb54924874c30967a19542af677ad","name":"设备状态","dicType":"SBZT0001","createTime":"2019-06-14T02:46:54.000+0000","parentId":"","status":"VALID","delFlag":"0","createBy":null,"dataLevel":1}
         * list : [{"id":"a93f42334670488fa647a854a0b9d8b5","name":"保养","dicType":"ZT0004","createTime":"2019-06-14T02:48:24.000+0000","parentId":"9ceeb54924874c30967a19542af677ad","status":"VALID","delFlag":"0","createBy":null,"dataLevel":2},{"id":"fd529e2cecd64f4497c27646176f95ad","name":"维修","dicType":"ZT0003","createTime":"2019-06-14T02:47:52.000+0000","parentId":"9ceeb54924874c30967a19542af677ad","status":"VALID","delFlag":"0","createBy":null,"dataLevel":2},{"id":"2f97caf8fae541d3ba1e35627495b3b5","name":"停机","dicType":"ZT0002","createTime":"2019-06-14T02:47:39.000+0000","parentId":"9ceeb54924874c30967a19542af677ad","status":"VALID","delFlag":"0","createBy":null,"dataLevel":2},{"id":"f48a18e15bf64802a723722fa2147275","name":"运行","dicType":"ZT0001","createTime":"2019-06-14T02:47:21.000+0000","parentId":"9ceeb54924874c30967a19542af677ad","status":"VALID","delFlag":"0","createBy":null,"dataLevel":2}]
         */

        private TypeDicBean typeDic;
        private List<ListBean> list;

        public TypeDicBean getTypeDic() {
            return typeDic;
        }

        public void setTypeDic(TypeDicBean typeDic) {
            this.typeDic = typeDic;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class TypeDicBean {
            /**
             * id : 9ceeb54924874c30967a19542af677ad
             * name : 设备状态
             * dicType : SBZT0001
             * createTime : 2019-06-14T02:46:54.000+0000
             * parentId :
             * status : VALID
             * delFlag : 0
             * createBy : null
             * dataLevel : 1
             */

            private String id;
            private String name;
            private String dicType;
            private String createTime;
            private String parentId;
            private String status;
            private String delFlag;
            private Object createBy;
            private int dataLevel;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDicType() {
                return dicType;
            }

            public void setDicType(String dicType) {
                this.dicType = dicType;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
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

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public int getDataLevel() {
                return dataLevel;
            }

            public void setDataLevel(int dataLevel) {
                this.dataLevel = dataLevel;
            }
        }

        public static class ListBean {
            /**
             * id : a93f42334670488fa647a854a0b9d8b5
             * name : 保养
             * dicType : ZT0004
             * createTime : 2019-06-14T02:48:24.000+0000
             * parentId : 9ceeb54924874c30967a19542af677ad
             * status : VALID
             * delFlag : 0
             * createBy : null
             * dataLevel : 2
             */

            private String id;
            private String name;
            private String dicType;
            private String createTime;
            private String parentId;
            private String status;
            private String delFlag;
            private Object createBy;
            private int dataLevel;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDicType() {
                return dicType;
            }

            public void setDicType(String dicType) {
                this.dicType = dicType;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
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

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public int getDataLevel() {
                return dataLevel;
            }

            public void setDataLevel(int dataLevel) {
                this.dataLevel = dataLevel;
            }
        }
    }
}
