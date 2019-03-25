package com.lb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lb.entity.Dictionary;

import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author null123
 * @since 2019-03-06
 */
public interface IDictionaryService extends IService<Dictionary> {

    /**
     * 根据类型获取一条数据
     * @param type
     * @return
     */
    Dictionary getOneByType(String type);

    /**
     * 根据类型获取所有数据
     * @param type
     * @return
     */
    List<Dictionary> getByType(String type);

    /**
     * 获取默认注册用户的角色编号
     * @return
     */
    String getDefaultRoleCode();

}
