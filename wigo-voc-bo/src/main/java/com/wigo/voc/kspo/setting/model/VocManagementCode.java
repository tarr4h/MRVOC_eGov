package com.wigo.voc.kspo.setting.model;

import lombok.Data;

import java.util.Date;

@Data
public class VocManagementCode {

    private String managementCd;
    private String topCd;
    private String prntsCd;
    private int odrg;
    private int lvl;
    private String codeNm;
    private String useYn;
    private String regUsr;
    private Date regDt;
}
