package com.lb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.entity.Dictionary;
import com.lb.mapper.DictionaryMapper;
import com.lb.service.IDictionaryService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author null123
 * @since 2019-03-06
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

    @Override
    public Dictionary getOneByType(String type) {
        return getOne(new QueryWrapper<Dictionary>()
                .lambda()
                .eq(Dictionary::getType,type));
    }

    @Override
    public List<Dictionary> getByType(String type) {
        return list(new QueryWrapper<Dictionary>()
        .lambda()
        .eq(Dictionary::getType,type));
    }
}
