package com.chen.service;

import com.chen.common.Result;
import com.chen.controller.dto.UserDto;
import com.chen.entity.Qinshi;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈志浩
 * @since 2022-04-29
 */
public interface IQinshiService extends IService<Qinshi> {
    boolean saveUser(Qinshi qinshi);

    UserDto login(UserDto userDto);

    Result register(UserDto userDto);
}
