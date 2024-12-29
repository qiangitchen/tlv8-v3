package com.tlv8.v3.oa.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.tlv8.v3.oa.pojo.OaEmReceiveemail;

@DS("oa")
@Mapper
public interface OaEmReceiveemailMapper extends BaseMapper<OaEmReceiveemail> {

}