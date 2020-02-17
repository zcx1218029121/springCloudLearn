package com.zcx.clouldauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zcx.common.entity.system.SystemUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zcx
 *  user的通用的mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<SystemUser> {
    /**
     *  根据用户名查询用户
     */
    SystemUser findByUserName(String userName);
}
