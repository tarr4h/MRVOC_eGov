package com.wigo.voc.kspo.setting.service;

import com.wigo.voc.kspo.setting.dao.VocManagementCodeDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VocManagementCodeService {

    @Autowired
    VocManagementCodeDao dao;
}
