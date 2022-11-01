package com.wigo.voc.kspo.corp.service;

import com.wigo.voc.common.model.AbstractTreeVo;
import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.kspo.corp.dao.VocCorporationDao;
import com.wigo.voc.sys.model.CrmEmpBaseVo;
import com.wigo.voc.sys.model.CrmOrgBaseVo;
import lombok.extern.slf4j.Slf4j;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("corpService")
@Slf4j
public class VocCorporationService extends EgovAbstractServiceImpl {

    @Autowired
    VocCorporationDao dao;

    public List<CrmOrgBaseVo> selectOrgList(Map<String, Object> param){
        return dao.selectOrgList(param);
    }

    public List<CrmEmpBaseVo> selectEmpList(EzMap param) {
        return dao.selectEmpList(param);
    }



}
