package com.changgou.goods.service;

import com.changgou.goods.pojo.Para;
import com.changgou.goods.pojo.Spec;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/28 16:19
 */
public interface ParaService {
    /**
     * 查询：根据动态条件分页
     */
    PageInfo<Para> findPage(Para para, Integer page, Integer size);

    /**
     * 查询：分页查询
     */
    PageInfo<Para> findPage(Integer page, Integer size);

    /**
     * 查询：所有数据
     */
    List<Para> findAll();

    /**
     * 查询：根据ID
     */
    Para findById(Integer id);

    /**
     * 查询：动态条件
     */
    List<Para> findList(Para para);

    /**
     * 新增
     */
    void add(Para para);

    /**
     * 删除
     */
    void delete(Integer id);

    /**
     * 更新
     */
    void update(Para para);
}
