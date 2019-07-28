package com.changgou.goods.service;

import com.changgou.goods.pojo.Spec;
import com.changgou.goods.pojo.Template;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/28 15:18
 */
public interface SpecService {
    /**
     * 查询：根据动态条件分页
     */
    PageInfo<Spec> findPage(Spec spec, Integer page, Integer size);

    /**
     * 查询：分页查询
     */
    PageInfo<Spec> findPage(Integer page, Integer size);

    /**
     * 查询：所有数据
     */
    List<Spec> findAll();

    /**
     * 查询：根据ID
     */
    Spec findById(Integer id);

    /**
     * 查询：动态条件
     */
    List<Spec> findList(Spec spec);

    /**
     * 新增
     */
    void add(Spec spec);

    /**
     * 删除
     */
    void delete(Integer id);

    /**
     * 更新
     */
    void update(Spec spec);
}
