package com.tlv8.v3.common.data;

import java.net.URLDecoder;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.ibatis.session.SqlSession;

import com.tlv8.v3.common.action.ActionSupport;
import com.tlv8.v3.common.base.Data;
import com.tlv8.v3.common.db.DBUtils;

/**
 * 
 */
public class BaseDeleteAction extends ActionSupport {
	protected Data data = new Data();
	protected String dbkay = "system"; // 默认值system
	protected String table = "";
	protected String cascade = "";// 级联删除配置{表名：外键,表名：外键...}
	protected String rowid;

	public Data getData() {
		return this.data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	@SuppressWarnings("deprecation")
	public String deleteData() throws SQLException, NamingException, Exception {
		String result = "";
		if (table == null || "".equals(table)) {
			throw new Exception("操作的表名不能为空！");
		}
		String sql = "delete from " + table + " where fID = '" + getRowid() + "'";
		if ("system".equals(dbkay))
			sql = sql.replace("fID", "sID");
		SqlSession session = DBUtils.getSession(dbkay);
		try {
			DBUtils.excuteDelete(session, sql);
			String billTable = "";
			String BillID = "";
			if (!"".equals(cascade) && cascade != null) {
				String[] cas = cascade.split(",");
				for (int n = 0; n < cas.length; n++) {
					billTable = cas[n].split(":")[0];
					BillID = cas[n].split(":")[1];
					String dsql = "delete from " + billTable + " where " + BillID + " = '" + getRowid() + "'";
					DBUtils.excuteDelete(session, dsql);// 级联删除
				}
			}
			session.commit();
		} catch (Exception e) {
			session.rollback();
			session.close();
			throw new SQLException(e);
		} finally {
			session.close();
		}
		return result;
	}

	public void setRowid(String rowid) {
		try {
			this.rowid = URLDecoder.decode(rowid, "UTF-8");
		} catch (Exception e) {
			this.rowid = rowid;
		}
	}

	public String getRowid() {
		return rowid;
	}

	public void setDbkay(String dbkay) {
		try {
			if (dbkay != null && !"".equals(dbkay))
				this.dbkay = URLDecoder.decode(dbkay, "UTF-8");
		} catch (Exception e) {
			this.dbkay = dbkay;
		}
	}

	public String getDbkay() {
		return dbkay;
	}

	public void setTable(String table) {
		try {
			this.table = URLDecoder.decode(table, "UTF-8");
		} catch (Exception e) {
			this.table = table;
		}
	}

	public String getTable() {
		return table;
	}

	public void setCascade(String cascade) {
		try {
			this.cascade = URLDecoder.decode(cascade, "UTF-8");
		} catch (Exception e) {
			this.cascade = cascade;
		}
	}

	public String getCascade() {
		return cascade;
	}

}
