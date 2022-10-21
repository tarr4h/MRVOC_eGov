package com.wigo.voc.kspo.setting.service;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.kspo.common.VocUtils;
import com.wigo.voc.kspo.setting.dao.VocProcedureCodeSettingDao;
import com.wigo.voc.kspo.setting.model.VocProcedureCodeVo;
import com.wigo.voc.sys.model.CrmEmpBaseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class VocProcedureCodeSettingService {

    @Autowired
    VocProcedureCodeSettingDao dao;

    @SuppressWarnings("unchecked")
    public Object insertCode(Map<String, Object> param) {
        List<Map<String, Object>> formArr = (List<Map<String, Object>>) param.get("formArr");
        param = VocUtils.formSerializeArrayToMap(formArr);

        return dao.insertCode(param);
    }

    public List<VocProcedureCodeVo> selectProcedureCodeList(EzMap param) {
        return dao.selectProcedureCodeList(param);
    }

    public Object saveRows(Map<String, Object> param) {
        return dao.saveRows(param);
    }

    public Object deleteRows(Map<String, Object> param) {
        return dao.deleteRows(param);
    }

    @SuppressWarnings("unchecked")
    public Object chngProcedureDuty(Map<String, Object> param) {
        log.debug("param = {}", param);
        return dao.chngProcedureDuty(param);
    }
}
