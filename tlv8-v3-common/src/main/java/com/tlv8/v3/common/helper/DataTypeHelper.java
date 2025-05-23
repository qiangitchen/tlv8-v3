package com.tlv8.v3.common.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.tlv8.v3.common.db.DBUtils;

public class DataTypeHelper {

    /**
     * @param conn
     * @param table
     * @param column
     * @return String
     * @since 获取表字段数据类型
     */
    public static String getColumnDataType(Connection conn, String table, String column) {
        String result = "";
        String sql = "select " + column + " from " + table + " where 1=2";
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.createStatement(1004, 1007);
            rs = stm.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            result = rsmd.getColumnTypeName(1);
        } catch (Exception e) {
        } finally {
            try {
                DBUtils.closeConn(null, stm, rs);
            } catch (SQLException e) {
            }
        }
        return result;
    }

    /**
     * @param conn
     * @param table
     * @param columns
     * @return Map<String, String>
     * @since 获取表字段数据类型
     */
    public static Map<String, String> getColumnsDataType(Connection conn, String table, String[] columns) {
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < columns.length; i++) {
            String datatype = getColumnDataType(conn, table, columns[i]);
            map.put(columns[i], datatype);
        }
        return map;
    }

    public static String dataValuePase(String dataType, String inval) {
        String resval = null;
        if (("DATE".equalsIgnoreCase(dataType) || "DATETIME".equalsIgnoreCase(dataType)) && !"".equals(inval)) {
            try {
                DateFormat dataTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date vl1 = dataTimeFormat.parse(inval);
                resval = dataTimeFormat.format(vl1);
            } catch (Exception e) {
                try {
                    DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date vl2 = dataFormat.parse(inval);
                    resval = dataFormat.format(vl2);
                } catch (Exception e2) {

                }
            }
        } else if ("INT".equalsIgnoreCase(dataType) || "INTEGER".equalsIgnoreCase(dataType)) {
            try {
                int inum = Integer.parseInt(inval);
                resval = String.valueOf(inum);
            } catch (Exception e) {
            }
        } else if ("FLOAT".equalsIgnoreCase(dataType) || "NUMBER".equalsIgnoreCase(dataType)) {
            try {
                double inum = Double.parseDouble(inval);
                resval = String.valueOf(inum);
            } catch (Exception e) {
            }
        } else {
            if (inval != null && !"".equals(inval.trim()) && !"null".equals(inval)) {
                resval = inval;
            }
        }
        return resval;
    }
}
