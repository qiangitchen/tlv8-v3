package com.tlv8.v3.common.db.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataParam {
    private String tableName;
    private String keyField;
    private String keyValue;
    private int version = 0;
    private Map<String, String> data = new HashMap<>();

    private List<SubData> subDataList = new ArrayList<>();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getKeyField() {
        return keyField;
    }

    public void setKeyField(String keyField) {
        this.keyField = keyField;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public List<SubData> getSubDataList() {
        return subDataList;
    }

    public void setSubDataList(List<SubData> subDataList) {
        this.subDataList = subDataList;
    }
}
