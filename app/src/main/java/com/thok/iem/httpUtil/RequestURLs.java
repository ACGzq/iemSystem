package com.thok.iem.httpUtil;


import com.thok.iem.BuildConfig;

public class RequestURLs {
    public static String hostUrl = BuildConfig.API_URL;
    public static final String URL_SEARCH_DEVICE = "/device/search";
    public static final String URL_SEARCHPAGE_DEVICE = "/device/searchpage";
    public static final String URL_FIND_DEVICE = "/device/find";
    public static final String URL_LOGIN = "/user/login";
    public static final String URL_UPDATA_PW = "/user/update/pw";
    public static final String URL_SEARCH_MAINTENANCE = "/maintenance/search";
    public static final String URL_SEARCHPAGE_MAINTENANCE = "/maintenance/searchpage";
    public static final String URL_FIND_MAINTENANCE = "/maintenance/find";
    public static final String URL_DIC_TYPE_LIST = "/api/dic/type/list";
    public static final String URL_ADD_REPAIR_DEVICE = "/api/device/repair/add";
    public static final String URL_DEVICE_REPAIR_FINISH = "/api/device/repair/finish";
    public static final String URL_SPARE_SEARCH = "/spare/search";
    public static final String URL_SPARE_SEARCHPAGE = "/spare/searchPage";
    public static final String URL_SPARE_PICK_ADD = "/spare/pick/add";
    public static final String URL_DEVICE_POLLING_ADD = "/device/polling/add ";
    public static final String URL_DEVICE_REPAIR_TASK = "/api/device/repair/task";
    public static final String URL_MAINTENANCE_HISTORY_ADD = "/maintenancerHistory/add";
    public static final String URL_SPARE_PICK_LIST = "/spare/pick/list";
    public static final String URL_SPARE_PICK_DETAIL = "/spare/pick/detail";

    public static String getHostUrl() {
        return hostUrl;
    }

    public static void setHostUrl(String hostUrl) {
        RequestURLs.hostUrl = hostUrl;
    }

    public static String getUrlSearchDevice() {
        return hostUrl + URL_SEARCH_DEVICE;
    }

    public static String getUrlSearchpageDevice() {
        return hostUrl + URL_SEARCHPAGE_DEVICE;
    }

    public static String getUrlFindDevice() {
        return hostUrl + URL_FIND_DEVICE;
    }

    public static String getUrlLogin() {
        return hostUrl + URL_LOGIN;
    }

    public static String getUrlUpdataPw() {
        return hostUrl + URL_UPDATA_PW;
    }

    public static String getUrlSearchMaintenance() {
        return hostUrl + URL_SEARCH_MAINTENANCE;
    }

    public static String getUrlSearchpageMaintenance() {
        return hostUrl + URL_SEARCHPAGE_MAINTENANCE;
    }

    public static String getUrlFindMaintenance() {
        return hostUrl + URL_FIND_MAINTENANCE;
    }

    public static String getUrlDicTypeList() {
        return hostUrl + URL_DIC_TYPE_LIST;
    }

    public static String getUrlAddRepairDevice() {
        return hostUrl + URL_ADD_REPAIR_DEVICE;
    }

    public static String getUrlDeviceRepairFinish() {
        return hostUrl + URL_DEVICE_REPAIR_FINISH;
    }

    public static String getUrlSpareSearch() {
        return hostUrl + URL_SPARE_SEARCH;
    }

    public static String getUrlSpareSearchpage() {
        return hostUrl + URL_SPARE_SEARCHPAGE;
    }

    public static String getUrlSparePickAdd() {
        return hostUrl + URL_SPARE_PICK_ADD;
    }

    public static String getUrlDevicePollingAdd() {
        return hostUrl + URL_DEVICE_POLLING_ADD;
    }

    public static String getUrlDeviceRepairTask() {
        return hostUrl + URL_DEVICE_REPAIR_TASK;
    }

    public static String getUrlMaintenanceHistoryAdd() {
        return hostUrl + URL_MAINTENANCE_HISTORY_ADD;
    }

    public static String getUrlSparePickList() {
        return hostUrl + URL_SPARE_PICK_LIST;
    }

    public static String getUrlSparePickDetail() {
        return hostUrl + URL_SPARE_PICK_DETAIL;
    }
}
