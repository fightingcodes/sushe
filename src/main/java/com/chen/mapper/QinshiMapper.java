package com.chen.mapper;

import com.chen.entity.Qinshi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 陈志浩
 * @since 2022-04-29
 */
public interface QinshiMapper extends BaseMapper<Qinshi> {
    void deleteAuto();
    void deletePrimaryKey();
    void deleteId();
    void changeAutoIncrementStart();
}
