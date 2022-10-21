package com.wigo.voc.kspo.setting.dao;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.common.model.ITreeVo;
import com.wigo.voc.kspo.setting.model.VocManagementCodeVo;
import com.wigo.voc.sys.mapper.CrmMapper;

import java.util.List;
import java.util.Map;

@CrmMapper
public interface VocManagementCodeDao {
    List<? extends ITreeVo> vocManagementCodeTree(EzMap param);

    List<VocManagementCodeVo> vocManagementCodeList(EzMap param);

    VocManagementCodeVo vocManagementCode(EzMap param);

    int insertVocManagementCode(EzMap param);

    int updateManagementCodeList(EzMap param);

    int deleteManagementCodeList(List<VocManagementCodeVo> list);

    String maxChildCd(EzMap param);
}
