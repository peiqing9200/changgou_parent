package com.changgou.goods.service.impl;

import com.changgou.goods.dao.AlbumMapper;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/27 20:26
 */
@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumMapper albumMapper;

    /**
     * 查询：动态条件分页
     */
    @Override
    public PageInfo<Album> findPage(Album album, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Example example = createExample(album);
        return new PageInfo<Album>(albumMapper.selectByExample(example));
    }

    /**
     * 查询：分页
     */
    @Override
    public PageInfo<Album> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return new PageInfo<Album>(albumMapper.selectAll());
    }

    /**
     * 查询：所有数据
     */
    @Override
    public List<Album> findAll() {
        List<Album> albums = albumMapper.selectAll();
        return albums;
    }

    /**
     * 查询：根据ID
     */
    @Override
    public Album findById(Long id) {
        Album album = albumMapper.selectByPrimaryKey(id);
        return album;
    }

    /**
     * 查询：动态条件
     */
    @Override
    public List<Album> findList(Album album) {
        Example example = createExample(album);
        List<Album> albums = albumMapper.selectByExample(example);
        return albums;
    }

    /**
     * 新增
     */
    @Override
    public void add(Album album) {
        albumMapper.insert(album);
    }

    /**
     * 删除:根据id
     */
    @Override
    public void delete(Long id) {
        albumMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新
     */
    @Override
    public void update(Album album) {
        albumMapper.updateByPrimaryKeySelective(album);
    }

    /**
     * 方法：构建动态条件，用于查询
     */
    private Example createExample(Album album) {
        //动态构建条件
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        if (album != null) {
            if (!StringUtils.isEmpty(album.getId())) {
                criteria.andEqualTo("id", album.getId());
            }
            if (!StringUtils.isEmpty(album.getTitle())) {
                criteria.andLike("title", "%" + album.getTitle() + "%");
            }
            if (!StringUtils.isEmpty(album.getImage())) {
                criteria.andEqualTo("image", album.getImage());
            }
            if (!StringUtils.isEmpty(album.getImageItems())) {
                criteria.andEqualTo("imageItems", album.getImageItems());
            }
        }
        return example;
    }

}
