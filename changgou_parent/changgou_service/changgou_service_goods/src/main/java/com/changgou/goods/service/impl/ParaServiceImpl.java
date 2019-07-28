package com.changgou.goods.service.impl;

import com.changgou.goods.dao.ParaMapper;
import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.pojo.Spec;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.ParaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/28 16:19
 */
public class ParaServiceImpl implements ParaService {
    @Autowired
    private ParaMapper paraMapper;
    @Autowired
    private TemplateMapper templateMapper;

    /**
     * 查询：动态条件分页
     */
    @Override
    public PageInfo<Para> findPage(Para para, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Example example = createExample(para);
        return new PageInfo<Para>(paraMapper.selectByExample(example));
    }

    /**
     * 查询：分页
     */
    @Override
    public PageInfo<Para> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return new PageInfo<Para>(paraMapper.selectAll());
    }

    /**
     * 查询：所有
     */
    @Override
    public List<Para> findAll() {
        List<Para> paras = paraMapper.selectAll();
        return paras;
    }

    /**
     * 查询：根据id
     */
    @Override
    public Para findById(Integer id) {
        Para para = paraMapper.selectByPrimaryKey(id);

        return para;
    }

    /**
     * 查询：动态条件
     */
    @Override
    public List<Para> findList(Para para) {
        Example example = createExample(para);
        List<Para> paras = paraMapper.selectByExample(example);
        return paras;
    }

    /**
     * 添加：新增
     */
    @Override
    public void add(Para para) {
        paraMapper.insert(para);
        updateSpecNum(para, 1);
    }

    /**
     * 删除：根据id
     */
    @Override
    public void delete(Integer id) {
        Para para = paraMapper.selectByPrimaryKey(id);
        updateSpecNum(para, -1);
        paraMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新：修改
     */
    @Override
    public void update(Para para) {
        paraMapper.updateByPrimaryKeySelective(para);
    }

    /**
     * 方法：构建动态条件，用于查询
     */
    private Example createExample(Para para) {
        //动态构建条件
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        if (para != null) {
            if (!StringUtils.isEmpty(para.getId())) {
                criteria.andEqualTo("id", para.getId());
            }
            if (!StringUtils.isEmpty(para.getName())) {
                criteria.andLike("name", "%" + para.getName() + "%");
            }
            if (!StringUtils.isEmpty(para.getOptions())) {
                criteria.andEqualTo("options", para.getOptions());
            }
            if (!StringUtils.isEmpty(para.getSeq())) {
                criteria.andEqualTo("seq", para.getSeq());
            }
            if (!StringUtils.isEmpty(para.getTemplateId())) {
                criteria.andEqualTo("templateId", para.getTemplateId());
            }


        }
        return example;
    }

    /**
     * 修改模板统计数据
     */
    public void updateSpecNum(Para para, int count) {
        //修改模板数量统计
        Template template = templateMapper.selectByPrimaryKey(para.getTemplateId());
        template.setSpecNum(template.getSpecNum() + count);
        templateMapper.updateByPrimaryKeySelective(template);
    }
}
