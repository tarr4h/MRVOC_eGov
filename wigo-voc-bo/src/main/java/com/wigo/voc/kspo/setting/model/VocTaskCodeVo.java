package com.wigo.voc.kspo.setting.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VocTaskCodeVo {

    private String taskSeq;
    private String taskNm;
    private int deadline;
    private String dutyDept;
    private String dutyDeptNm;
    private String dutyEmp;
    private String dutyEmpNm;
    private String dutyRole;
    private String chngYn;
    private String autoApplyYn;
    private String modDt;
    private String modUsr;
}
