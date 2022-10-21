package com.wigo.voc.kspo.setting.controller;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.common.model.EzPaginationInfo;
import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.kspo.common.VocUtils;
import com.wigo.voc.kspo.setting.model.VocProcedureCodeVo;
import com.wigo.voc.kspo.setting.service.VocProcedureCodeSettingService;
import com.wigo.voc.kspo.setting.service.VocProcedureSettingService;
import com.wigo.voc.sys.model.CrmEmpBaseVo;
import com.wigo.voc.sys.model.CrmOrgBaseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping({"vocProcedureCodeSetting", "{menuCd}/vocProcedureCodeSetting"})
public class VocProcedureCodeSettingController {

    @Autowired
    VocProcedureCodeSettingService service;

    @GetMapping(value = {"", "index"})
    public String init(@RequestParam Map<String, Object> param, Model model) {
        model.addAllAttributes(param);

        return Utilities.getProperty("tiles.crm") + "voc/setting/procedure_code/vocProcedureCodeSetting";
    }

    @GetMapping(value = { "openModal/{pageNm}"})
    public String openModal(@PathVariable String pageNm, @RequestParam Map<String, Object> param, Model model) throws Exception {
        log.debug("pageNm = {}", pageNm);
        log.debug("param = {}", param);
        model.addAttribute("param", param);

        return Utilities.getProperty("tiles.crm.blank") + "voc/setting/procedure_code/" + pageNm;
    }

    @GetMapping(value = { "openComnModal/{pageNm}"})
    public String openComnModal(@PathVariable String pageNm, @RequestParam Map<String, Object> param, Model model) throws Exception {
        log.debug("pageNm = {}", pageNm);
        log.debug("param = {}", param);
        model.addAttribute("param", param);

        log.debug("model = {}", model);

        return Utilities.getProperty("tiles.crm.blank") + "voc/common/" + pageNm;
    }

    @PostMapping(value = "getOrgTree")
    public @ResponseBody Object getOrgTree(@RequestBody Map<String, Object> param){
        return VocUtils.getOrgTree(param);
    }

    @GetMapping(value = "selectOrgList")
    public @ResponseBody Object selectOrgList(@RequestParam Map<String, Object> param){
        return VocUtils.selectOrgList(param);
    }

    @PostMapping(value = "selectEmpList")
    public @ResponseBody Object selectEmpList(@RequestBody EzMap param){
        EzPaginationInfo page = param.getPaginationInfo();
        List<CrmEmpBaseVo> list = VocUtils.selectEmpList(param);
        page.setTotalRecordCount(list.size());
        return Utilities.getGridData(list, page);
    }

    @PostMapping(value = "selectOrgList")
    public @ResponseBody Object selectOrgList(@RequestBody EzMap param){
        EzPaginationInfo page = param.getPaginationInfo();
        List<CrmOrgBaseVo> list = VocUtils.selectOrgList(param);
        page.setTotalRecordCount(list.size());
        return Utilities.getGridData(list, page);
    }

    @PostMapping(value = "insertCode")
    public @ResponseBody Object insertCode(@RequestBody Map<String, Object> param){
        return service.insertCode(param);
    }

    @PostMapping(value = "selectProcedureCodeList")
    public @ResponseBody Object selectProcedureCodeList(@RequestBody EzMap param){
        EzPaginationInfo page = param.getPaginationInfo();
        List<VocProcedureCodeVo> list = service.selectProcedureCodeList(param);
        List<Map<String, Object>> convertList = Utilities.beanToMap(list);
        page.setTotalRecordCount(list.size());
        return Utilities.getGridData(convertList, page);
    }

    @PostMapping(value = "saveRows")
    public @ResponseBody Object saveRows(@RequestBody Map<String, Object> param){
        return service.saveRows(param);
    }

    @PostMapping(value = "deleteRows")
    public @ResponseBody Object deleteRos(@RequestBody Map<String, Object> param){
        return service.deleteRows(param);
    }

    @PostMapping(value = "chngProcedureDuty")
    public @ResponseBody Object chngProcedureDuty(@RequestBody Map<String, Object> param){
        return service.chngProcedureDuty(param);
    }
}
