package com.tlv8.v3.doc.clt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tlv8.v3.doc.clt.pojo.SaDocnode;

@DS("system")
@Mapper
public interface SaDocnodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sa_docnode
     *
     * @mbg.generated Wed Apr 19 15:03:33 CST 2023
     */
    int deleteByPrimaryKey(String sid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sa_docnode
     *
     * @mbg.generated Wed Apr 19 15:03:33 CST 2023
     */
    int insert(SaDocnode row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sa_docnode
     *
     * @mbg.generated Wed Apr 19 15:03:33 CST 2023
     */
    SaDocnode selectByPrimaryKey(String sid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sa_docnode
     *
     * @mbg.generated Wed Apr 19 15:03:33 CST 2023
     */
    List<SaDocnode> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sa_docnode
     *
     * @mbg.generated Wed Apr 19 15:03:33 CST 2023
     */
    int updateByPrimaryKey(SaDocnode row);
    
    List<SaDocnode> selectByDocpath(String docpath);
    
    List<SaDocnode> selectByDocDisplayPath(String docpath);

	SaDocnode selectByFileID(String fileID);
}