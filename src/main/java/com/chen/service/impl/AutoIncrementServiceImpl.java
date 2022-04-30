package com.chen.service.impl;

import com.chen.mapper.AutoIncrementMapper;
import com.chen.service.AutoIncrementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoIncrementServiceImpl implements AutoIncrementService {
    @Autowired
    private AutoIncrementMapper autoIncrementMapper;

    public void changeAuto() {
        autoIncrementMapper.deleteAuto();
        autoIncrementMapper.deletePrimaryKey();
        autoIncrementMapper.deleteId();
        autoIncrementMapper.changeAutoIncrementStart();
    }

}
