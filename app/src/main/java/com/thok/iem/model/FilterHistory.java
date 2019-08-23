package com.thok.iem.model;

import com.thok.iem.utils.DBAnno;

public class FilterHistory {
    @DBAnno(isKey = true)
    int id;
    String historyStr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHistoryStr() {
        return historyStr;
    }

    public void setHistoryStr(String historyStr) {
        this.historyStr = historyStr;
    }
}
