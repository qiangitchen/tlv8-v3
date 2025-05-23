package com.tlv8.v3.doc.clt.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tlv8.v3.common.action.ActionSupport;
import com.tlv8.v3.common.db.DBUtils;

/**
 * @P 删除图片
 * @author ChenQain
 * @C 2011-12-8
 */
@Controller
@Scope("prototype")
public class ImageDeleteAction extends ActionSupport {
	private String fileName;
	private String caption;
	private String flag;
	private String dbkey;
	private String tablename;
	private String cellname;
	private String rowid;

	public String getUploadFileName() {
		return fileName;
	}

	public void setUploadFileName(String fileName) {
		this.fileName = fileName;
	}

	@ResponseBody
	@RequestMapping("/ImageDeleteAction")
	@Override
	public Object execute() throws Exception {
		SqlSession session = DBUtils.getSqlSession();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = session.getConnection();
			String keyfield = ("system".equals(dbkey)) ? "sID" : "fID";
			SQL sql = new SQL();
			sql.UPDATE(tablename);
			sql.SET(cellname + "=null");
			sql.WHERE(keyfield + "=?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, rowid);
			pstmt.executeUpdate();
			session.commit(true);
		} catch (Exception e) {
			session.rollback(true);
			setCaption("错误:" + e.toString());
			e.printStackTrace();
			setFlag("false");
		} finally {
			DBUtils.closeConn(session, conn, pstmt, null);
		}
		setFlag("true");
		return this;
	}

	public void setDbkey(String dbkey) {
		this.dbkey = dbkey;
	}

	public String getDbkey() {
		return dbkey;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getTablename() {
		return tablename;
	}

	public void setCellname(String cellname) {
		this.cellname = cellname;
	}

	public String getCellname() {
		return cellname;
	}

	public void setRowid(String rowid) {
		this.rowid = rowid;
	}

	public String getRowid() {
		return rowid;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getCaption() {
		return caption;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}
}
