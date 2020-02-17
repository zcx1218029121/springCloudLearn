package com.zcx.clouldauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zcx.common.entity.system.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> findUserPermissions(String username);
}
