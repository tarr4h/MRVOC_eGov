package com.wigo.voc.kspo.setting.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VocProcedureCodeVo {

    private String prcdSeq;
    private String prcdNm;
    private int deadline;
    private String dutyDept;
    private String dutyDeptNm;
    private String dutyEmp;
    private String dutyEmpNm;
    private String dutyRole;
    private String chngYn;
    private String regUseYn;
    private String recUseYn;
    private String regCompulsoryYn;
    private String recCompulsoryYn;
    private String taskYn;
    private String modDt;
    private String modUsr;
}
