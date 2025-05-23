package com.tlv8.v3.system.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tlv8.v3.system.pojo.SaOprolemanagement;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@DS("system")
@Mapper
public interface SaOprolemanagementMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sa_oprolemanagement
     *
     * @mbg.generated Sat Nov 23 20:12:46 CST 2024
     */
    int deleteByPrimaryKey(String sid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sa_oprolemanagement
     *
     * @mbg.generated Sat Nov 23 20:12:46 CST 2024
     */
    int insert(SaOprolemanagement row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sa_oprolemanagement
     *
     * @mbg.generated Sat Nov 23 20:12:46 CST 2024
     */
    SaOprolemanagement selectByPrimaryKey(String sid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sa_oprolemanagement
     *
     * @mbg.generated Sat Nov 23 20:12:46 CST 2024
     */
    List<SaOprolemanagement> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sa_oprolemanagement
     *
     * @mbg.generated Sat Nov 23 20:12:46 CST 2024
     */
    int updateByPrimaryKey(SaOprolemanagement row);
    
    /**
	 * @param param{orgid:"",roleid:""}
	 * @return
	 */
	List<SaOprolemanagement> selectByOrgIdRoleId(Map<String, String> param);
}