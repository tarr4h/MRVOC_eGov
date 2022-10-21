package com.wigo.voc.kspo.setting.service;

import com.wigo.voc.common.model.AbstractTreeVo;
import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.kspo.common.VocUtils;
import com.wigo.voc.kspo.setting.dao.VocManagementCodeDao;
import com.wigo.voc.kspo.setting.model.VocManagementCodeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@Service
@Slf4j
public class VocManagementCodeService {

    @Autowired
    VocManagementCodeDao dao;

    public Object vocManagementCodeTree(EzMap param) {
        return AbstractTreeVo.makeHierarchy(dao.vocManagementCodeTree(param));
    }

    public List<VocManagementCodeVo> vocManagementCodeList(EzMap param) {
        return dao.vocManagementCodeList(param);
    }

    public Object vocManagementCode(EzMap param) {
        return dao.vocManagementCode(param);
    }

    public Object insertVocManagementCode(EzMap param) {
        log.debug("param = {}", param);
        Map<String, Object> returnMap = new HashMap<>();
        String msg = null;
        boolean result = false;

        if(param.get("codeNm") == null){
            msg = "코드명을 입력해주세요.";
        } else {
            if(param.get("prntsCd") != null){
                param = generateInsertCodeParam(param);
            } else {
                param = generateInsertGroupParam(param);
            }
            int insertResult = dao.insertVocManagementCode(param);

            if(insertResult > 0){
                msg = "등록되었습니다.";
                result = true;
            } else {
//                 exception Throw
            }
        }

        returnMap.put("msg", msg);
        returnMap.put("result", result);
        return returnMap;
    }

    public EzMap generateInsertGroupParam(EzMap param){
        StringBuilder managementCd = new StringBuilder();
        managementCd.append("MC");

        int groupSize = dao.vocManagementCodeList(param).size() + 1;
        if(groupSize < 10){
            managementCd.append("0").append(groupSize);
        } else {
            managementCd.append(groupSize);
        };

        for(int i = 0; i < 13; i++){
            managementCd.append("0");
        }

        log.debug("management grp cd = {}", managementCd);

        param.put("managementCd", managementCd.toString());
        param.put("topCd", managementCd.toString());
        param.put("odrg", groupSize);
        param.put("lvl", 1);
        return param;
    }

    public EzMap generateInsertCodeParam(EzMap param){
        // 17자리(코드값 2 + 분류값15(2/3/3/3/4))
        VocManagementCodeVo prntsRow = dao.vocManagementCode(param);
        log.debug("prntsRow = {}", prntsRow.getCodeNm());

        int childrenSize = dao.vocManagementCodeList(param).size();
        String maxChildCd = dao.maxChildCd(param);

        param.put("topCd", prntsRow.getTopCd());
        param.put("lvl", prntsRow.getLvl() + 1);
        param.put("regUsr", Utilities.getLoginUserCd());

        String prntsCd = prntsRow.getManagementCd();
        String prntsCdGrp = prntsCd.substring(2, 4);
        String prntsCdNum = prntsCd.substring(4); // grp인 경우엔 2

        // prefix
        StringBuilder managementCd = new StringBuilder();
        managementCd.append("MC").append(prntsCdGrp);

        // 부모코드 자리
        int prntsLvl = prntsRow.getLvl() - 1;
        if(prntsLvl != 0){
            String subStrCd = prntsCdNum.substring(0, prntsLvl * 3);
            managementCd.append(subStrCd);
        }

        log.debug("prntsLvl = {}", prntsLvl);
        log.debug("managementCd 1 = {}", managementCd);

        // 현재 코드의 실제 적재 자리
        int inputVal = 0;
        if(maxChildCd == null) {
            param.put("odrg", childrenSize + 1);
            inputVal = childrenSize + 1;
        } else {
            String siblingCd = maxChildCd.substring(4 + (prntsLvl * 3), 4 + (prntsLvl + 1) * 3);
            inputVal = VocUtils.parseIntObject(siblingCd) + 1;
            param.put("odrg", inputVal);
        }

        if(inputVal < 10){
            managementCd.append("000");
        } else if (inputVal < 100){
            managementCd.append("00");
        } else if (inputVal < 1000){
            managementCd.append("0");
        }
        if(prntsLvl + 1 < 4){
            managementCd.deleteCharAt(managementCd.length() - 1);
        }
        managementCd.append(inputVal);

        int leftSpace = 17 - 4 - ((prntsLvl + 1) * 3);
        leftSpace = prntsLvl == 3 ? leftSpace - 1 : leftSpace;
        for(int i = 0; i < leftSpace; i++){
            managementCd.append("0");
        }

        log.debug("managementCd = {}", managementCd);

        param.put("managementCd", managementCd.toString());
        return param;
    }

    public Object updateManagementCodeList(EzMap param) {
        return dao.updateManagementCodeList(param);
    }

    public Object deleteManagementCodeList(List<VocManagementCodeVo> list) {
        for(VocManagementCodeVo vo : list){

        }
        return dao.deleteManagementCodeList(list);
    }
}
