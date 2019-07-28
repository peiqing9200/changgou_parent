package com.changgou.goods.service.impl;

import com.changgou.goods.dao.SpecMapper;
import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.pojo.Spec;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.SpecService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/28 15:19
 */
@Service
public class SpecServiceImpl implements SpecService {
    @Autowired
    private SpecMapper specMapper;
    @Autowired
    private TemplateMapper templateMapper;

    /**
     * 查询：根据动态条件分页
     */
    @Override
    public PageInfo<Spec> findPage(Spec spec, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Example example = createExample(spec);
        return new PageInfo<Spec>(specMapper.selectByExample(example));
    }

    /**
     * 查询：分页
     */
    @Override
    public PageInfo<Spec> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return new PageInfo<Spec>(specMapper.selectAll());
    }

    /**
     * 查询：所有
     */
    @Override
    public List<Spec> findAll() {
        List<Spec> specs = specMapper.selectAll();
        return specs;
    }

    /**
     * 查询：根据id
     */
    @Override
    public Spec findById(Integer id) {
        Spec spec = specMapper.selectByPrimaryKey(id);
        return spec;
    }

    /**
     * 查询：动态条件
     */
    @Override
    public List<Spec> findList(Spec spec) {
        Example example = createExample(spec);
        List<Spec> specs = specMapper.selectByExample(example);
        return specs;
    }

    /**
     * 新增：添加
     */
    @Override
    public void add(Spec spec) {
        specMapper.insert(spec);
        updateSpecNum(spec, 1);
    }

    /**
     * 删除：根据id
     */
    @Override
    public void delete(Integer id) {
        Spec spec = specMapper.selectByPrimaryKey(id);
        updateSpecNum(spec, -1);
        specMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新：修改
     */
    @Override
    public void update(Spec spec) {
        specMapper.updateByPrimaryKey(spec);
    }

    /**
     * 方法：构建动态条件，用于查询
     */
    private Example createExample(Spec spec) {
        //动态构建条件
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        if (spec != null) {
            if (!StringUtils.isEmpty(spec.getId())) {
                criteria.andEqualTo("id", spec.getId());
            }
            if (!StringUtils.isEmpty(spec.getName())) {
                criteria.andLike("name", "%" + spec.getName() + "%");
            }
            if (!StringUtils.isEmpty(spec.getOptions())) {
                criteria.andEqualTo("options", spec.getOptions());
            }
            if (!StringUtils.isEmpty(spec.getSeq())) {
                criteria.andEqualTo("seq", spec.getSeq());
            }
            if (!StringUtils.isEmpty(spec.getTemplateId())) {
                criteria.andEqualTo("templateId", spec.getTemplateId());
            }


        }
        return example;
    }

    /**
     * 修改模板统计数据
     */
    public void updateSpecNum(Spec spec, int count) {
        //修改模板数量统计
        Template template = templateMapper.selectByPrimaryKey(spec.getTemplateId());
        template.setSpecNum(template.getSpecNum() + count);
        templateMapper.updateByPrimaryKeySelective(template);
    }
}
