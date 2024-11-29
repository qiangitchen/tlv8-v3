package com.tlv8.v3.system.service;

import java.util.List;

import com.tlv8.v3.system.pojo.SaMenu;

public interface ISaMenuService {

    List<SaMenu> selectList(String sorgid, String sorgfid);
}
