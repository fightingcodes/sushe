package com.chen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.entity.Qinshi;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AutoIncrementMapper extends BaseMapper<Qinshi> {


    void deleteAuto();

    void deletePrimaryKey();

    void deleteId();

    void changeAutoIncrementStart();
}
