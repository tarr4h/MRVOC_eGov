package com.wigo.voc.kspo.setting.dao;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.kspo.setting.model.VocTaskCodeVo;
import com.wigo.voc.sys.mapper.CrmMapper;

import java.util.List;
import java.util.Map;

@CrmMapper
public interface VocTaskCodeSettingDao {
    List<VocTaskCodeVo> selectTaskCodeList(EzMap param);

    int insertCode(Map<String, Object> param);

    int saveRows(Map<String, Object> param);

    int deleteRows(Map<String, Object> param);

    int chngTaskDuty(Map<String, Object> param);
}
