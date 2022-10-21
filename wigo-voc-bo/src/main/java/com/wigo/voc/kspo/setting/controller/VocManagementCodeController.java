package com.wigo.voc.kspo.setting.controller;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.common.model.EzPaginationInfo;
import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.kspo.setting.model.VocManagementCodeVo;
import com.wigo.voc.kspo.setting.service.VocManagementCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping({"vocManagementCode", "{menuCd}/vocManagementCode"})
public class VocManagementCodeController {

    @Autowired
    VocManagementCodeService service;

    @GetMapping(value = {"", "index"})
    public String init(@RequestParam Map<String, Object> param, Model model) {
        model.addAllAttributes(param);

        return Utilities.getProperty("tiles.crm") + "voc/setting/management_code/vocManagementCodeSetting";
    }

    @PostMapping(value = {"vocManagementCodeTree"})
    public @ResponseBody Object vocManagementCodeTree(@RequestBody EzMap param) throws Exception{
        return service.vocManagementCodeTree(param);
    }

    @PostMapping(value = {"vocManagementCodeGrid"})
    public @ResponseBody Object vocMcMappingRows(@RequestBody EzMap param) throws Exception {
        EzPaginationInfo page = param.getPaginationInfo();
        List<VocManagementCodeVo> list = service.vocManagementCodeList(param);
        List<Map<String, Object>> convertList = Utilities.beanToMap(list);
        page.setTotalRecordCount(list.size());
        return Utilities.getGridData(convertList, page);
    }

    @GetMapping(value = "vocManagementCode")
    public @ResponseBody Object vocManagementCode(@RequestParam Map<String, Object> param) {
        EzMap map = new EzMap(param);
        return service.vocManagementCode(map);
    }

    @GetMapping(value = { "openModal/{pageNm}"})
    public String openModal(@PathVariable String pageNm, @RequestParam Map<String, Object> param, Model model) throws Exception {
        log.debug("pageNm = {}", pageNm);
        log.debug("param = {}", param);
        model.addAttribute("param", param);

        return Utilities.getProperty("tiles.crm.blank") + "voc/setting/management_code/" + pageNm;
    }

    @PostMapping(value = "insertVocManagementCode")
    public @ResponseBody Object insertVocManagementCode(@RequestBody EzMap param){
        return service.insertVocManagementCode(param);
    }

    @PostMapping(value = "updateManagementCodeList")
    public @ResponseBody Object updateManagementCodeList(@RequestBody EzMap param){
        return service.updateManagementCodeList(param);
    }

    @PostMapping(value = "deleteManagementCodeList")
    public @ResponseBody Object deleteManagementCodeList(@RequestBody List<VocManagementCodeVo> list){
        return service.deleteManagementCodeList(list);
    }


}
