package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/26 17:40
 */
public interface BrandService {
    /**
     * 查询所有品牌
     */
    List<Brand> findAll();

    /**
     * 根据id查询
     */
    Brand findById(Integer id);

    /**
     * 添加
     */
    void add(Brand brand);

    /**
     * 根据id修改
     */
    void update(Brand brand);

    /**
     * 根据id删除
     */
    void delete(Integer id);

    /**
     * 条件搜索
     */
    List<Brand> findList(Brand brand);

    /**
     * 分页搜索
     */
    PageInfo<Brand> findPage(Integer page, Integer size);

    /**
     * 根据条件分页查询
     */
    PageInfo<Brand> findPage(Brand brand, Integer page, Integer size);


}
