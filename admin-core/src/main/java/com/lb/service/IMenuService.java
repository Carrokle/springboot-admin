package com.lb.service;

import com.baomidou.mybatisplus.service.IService;
import com.lb.entity.Menu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> selectMenuByRoleCode(String roleCode);
}
