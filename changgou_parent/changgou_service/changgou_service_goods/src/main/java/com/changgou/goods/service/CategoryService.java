package com.changgou.goods.service;

import com.changgou.goods.pojo.Category;
import com.changgou.goods.pojo.Template;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/28 17:01
 */
public interface CategoryService {
    /**
     * 查询：根据动态条件分页
     */
    PageInfo<Category> findPage(Category category, Integer page, Integer size);

    /**
     * 查询：分页查询
     */
    PageInfo<Category> findPage(Integer page, Integer size);

    /**
     * 查询：所有数据
     */
    List<Category> findAll();

    /**
     * 查询：根据ID
     */
    Category findById(Integer id);

    /**
     * 查询：动态条件
     */
    List<Category> findList(Category category);

    /**
     * 查询：根据父节点id
     */
    List<Category> findByParentId(Integer id);

    /**
     * 新增
     */
    void add(Category category);

    /**
     * 删除
     */
    void delete(Integer id);

    /**
     * 更新
     */
    void update(Category category);
}
