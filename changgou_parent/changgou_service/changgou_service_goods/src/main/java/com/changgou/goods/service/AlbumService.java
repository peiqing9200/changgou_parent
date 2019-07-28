package com.changgou.goods.service;

import com.changgou.goods.pojo.Album;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/27 20:26
 */
public interface AlbumService {
    /**
     * 查询：根据动态条件分页
     */
    PageInfo<Album> findPage(Album album, Integer page, Integer size);

    /**
     * 查询：分页查询
     */
    PageInfo<Album> findPage(Integer page, Integer size);

    /**
     * 查询：所有数据
     */
    List<Album> findAll();

    /**
     * 查询：根据ID
     */
    Album findById(Long id);

    /**
     * 查询：动态条件
     */
    List<Album> findList(Album album);

    /**
     * 新增
     */
    void add(Album album);
    /**
     * 删除
     */
    void delete(Long id);
    /**
     * 更新
     */
    void update(Album album);

}
