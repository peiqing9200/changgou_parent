package com.changgou.goods.service.impl;

import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.TemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/28 11:36
 */
@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    private TemplateMapper templateMapper;

    /**
     * 查询：动态条件分页
     */
    @Override
    public PageInfo<Template> findPage(Template template, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Example example = createExample(template);
        return new PageInfo<Template>(templateMapper.selectByExample(example));
    }

    /**
     * 查询：分页
     */
    @Override
    public PageInfo<Template> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);

        return new PageInfo<Template>(templateMapper.selectAll());
    }

    /**
     * 查询：所有
     */
    @Override
    public List<Template> findAll() {
        List<Template> templates = templateMapper.selectAll();
        return templates;
    }

    /**
     * 查询：根据id
     */
    @Override
    public Template findById(Integer id) {
        Template template = templateMapper.selectByPrimaryKey(id);
        return template;
    }

    /**
     * 查询：动态条件
     */
    @Override
    public List<Template> findList(Template template) {
        Example example = createExample(template);
        List<Template> templates = templateMapper.selectByExample(example);
        return templates;
    }

    /**
     * 添加新增
     */
    @Override
    public void add(Template template) {
        templateMapper.insert(template);
    }

    /**
     * 删除：根据id
     */
    @Override
    public void delete(Integer id) {
        templateMapper.deleteByPrimaryKey(id);

    }

    /**
     * 更新：修改
     */
    @Override
    public void update(Template template) {
        templateMapper.updateByPrimaryKey(template);
    }

    /**
     * 方法：构建动态条件，用于查询
     */
    private Example createExample(Template template) {
        //动态构建条件
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        if (template != null) {
            if (!StringUtils.isEmpty(template.getId())) {
                criteria.andEqualTo("id", template.getId());
            }
            if (!StringUtils.isEmpty(template.getName())) {
                criteria.andLike("name", "%" + template.getName() + "%");
            }
            if (!StringUtils.isEmpty(template.getParaNum())) {
                criteria.andEqualTo("paraNum", template.getParaNum());
            }
            if (!StringUtils.isEmpty(template.getSpecNum())) {
                criteria.andEqualTo("specNum", template.getSpecNum());
            }


        }
        return example;
    }
}
