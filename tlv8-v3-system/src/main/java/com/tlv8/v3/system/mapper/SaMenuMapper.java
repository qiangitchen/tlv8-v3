package com.tlv8.v3.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tlv8.v3.system.pojo.SaMenu;

import java.util.List;
import java.util.Map;

@Mapper
public interface SaMenuMapper {
    List<SaMenu> selectList(Map<String, String> param);
}
