package com.wigo.voc.kspo.setting.controller;

import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.kspo.setting.service.VocManagementCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        return Utilities.getProperty("tiles.crm") + "voc/option/vocOptionSetting";
    }

}
