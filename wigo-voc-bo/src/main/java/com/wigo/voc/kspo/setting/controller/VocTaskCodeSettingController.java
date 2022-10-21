package com.wigo.voc.kspo.setting.controller;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.common.model.EzPaginationInfo;
import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.kspo.common.VocUtils;
import com.wigo.voc.kspo.setting.model.VocProcedureCodeVo;
import com.wigo.voc.kspo.setting.model.VocTaskCodeVo;
import com.wigo.voc.kspo.setting.service.VocTaskCodeSettingService;
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
@RequestMapping({"vocTaskCodeSetting", "{menuCd}/vocTaskCodeSetting"})
public class VocTaskCodeSettingController {

    @Autowired
    VocTaskCodeSettingService service;

    @GetMapping(value = {"", "index"})
    public String init(@RequestParam Map<String, Object> param, Model model) {
        model.addAllAttributes(param);

        return Utilities.getProperty("tiles.crm") + "voc/setting/task_code/vocTaskCodeSetting";
    }

    @GetMapping(value = { "openModal/{pageNm}"})
    public String openModal(@PathVariable String pageNm, @RequestParam Map<String, Object> param, Model model) throws Exception {
        model.addAttribute("param", param);

        return Utilities.getProperty("tiles.crm.blank") + "voc/setting/task_code/" + pageNm;
    }

    @GetMapping(value = { "openComnModal/{pageNm}"})
    public String openComnModal(@PathVariable String pageNm, @RequestParam Map<String, Object> param, Model model) throws Exception {
        model.addAttribute("param", param);

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

    @PostMapping(value = "selectTaskCodeList")
    public @ResponseBody Object selectTaskCodeList(@RequestBody EzMap param){
        EzPaginationInfo page = param.getPaginationInfo();
        List<VocTaskCodeVo> list = service.selectTaskCodeList(param);
        List<Map<String, Object>> convertList = Utilities.beanToMap(list);
        page.setTotalRecordCount(list.size());
        return Utilities.getGridData(convertList, page);
    }

    @PostMapping(value = "insertCode")
    public @ResponseBody Object insertCode(@RequestBody Map<String, Object> param){
        return service.insertCode(param);
    }

    @PostMapping(value = "saveRows")
    public @ResponseBody Object saveRows(@RequestBody Map<String, Object> param){
        return service.saveRows(param);
    }

    @PostMapping(value = "deleteRows")
    public @ResponseBody Object deleteRos(@RequestBody Map<String, Object> param){
        return service.deleteRows(param);
    }

    @PostMapping(value = "chngTaskDuty")
    public @ResponseBody Object chngTaskDuty(@RequestBody Map<String, Object> param){
        return service.chngTaskDuty(param);
    }
}
