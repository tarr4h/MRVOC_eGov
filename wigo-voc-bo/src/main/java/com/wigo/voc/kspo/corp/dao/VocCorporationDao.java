package com.wigo.voc.kspo.corp.dao;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.sys.mapper.CrmMapper;
import com.wigo.voc.sys.model.CrmEmpBaseVo;
import com.wigo.voc.sys.model.CrmOrgBaseVo;

import java.util.List;
import java.util.Map;

@CrmMapper
public interface VocCorporationDao {
    List<CrmOrgBaseVo> selectOrgList(Map<String, Object> param);

    List<CrmEmpBaseVo> selectEmpList(EzMap param);
}
