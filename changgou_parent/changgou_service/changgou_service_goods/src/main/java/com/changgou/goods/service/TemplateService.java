package com.changgou.goods.service;

import com.changgou.goods.pojo.Album;
import com.changgou.goods.pojo.Template;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/28 11:35
 */
public interface TemplateService {
    /**
     * 查询：根据动态条件分页
     */
    PageInfo<Template> findPage(Template template, Integer page, Integer size);

    /**
     * 查询：分页查询
     */
    PageInfo<Template> findPage(Integer page, Integer size);

    /**
     * 查询：所有数据
     */
    List<Template> findAll();

    /**
     * 查询：根据ID
     */
    Template findById(Integer id);

    /**
     * 查询：动态条件
     */
    List<Template> findList(Template template);

    /**
     * 新增
     */
    void add(Template template);

    /**
     * 删除
     */
    void delete(Integer id);

    /**
     * 更新
     */
    void update(Template template);
}
