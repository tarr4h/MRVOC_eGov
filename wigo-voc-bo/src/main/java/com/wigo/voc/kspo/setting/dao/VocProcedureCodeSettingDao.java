package com.wigo.voc.kspo.setting.dao;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.kspo.setting.model.VocProcedureCodeVo;
import com.wigo.voc.sys.mapper.CrmMapper;

import java.util.List;
import java.util.Map;

@CrmMapper
public interface VocProcedureCodeSettingDao {

    int insertCode(Map<String, Object> param);

    List<VocProcedureCodeVo> selectProcedureCodeList(EzMap param);

    int saveRows(Map<String, Object> param);

    int deleteRows(Map<String, Object> param);

    int chngProcedureDuty(Map<String, Object> param);
}
