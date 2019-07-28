package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/26 17:41
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询：所有
     *
     * @return
     */
    @Override
    public List<Brand> findAll() {
        List<Brand> brands = brandMapper.selectAll();
        return brands;
    }

    /**
     * 查询：根据id
     */
    @Override
    public Brand findById(Integer id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        return brand;
    }

    /**
     * 查询：条件搜索
     */
    @Override
    public List<Brand> findList(Brand brand) {
        Example example = createExample(brand);


        return brandMapper.selectByExample(example);
    }

    /**
     * 查询：分页搜索
     */
    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {
        //参数1：当前页   参数2：每页显示的数量
        PageHelper.startPage(page, size);

        return new PageInfo<Brand>(brandMapper.selectAll());
    }

    /**
     * 查询：根据条件分页查询
     */
    @Override
    public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Example example = createExample(brand);
        return new PageInfo<Brand>(brandMapper.selectByExample(example));
    }

    /**
     *
     * 此方法用于动态条件构建
     */
    private Example createExample(Brand brand) {
        //动态构建条件，Brand.class 操作指定对象，则写指定对象的字节码对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (brand != null) {
            //用户输入名字，根据名字模糊查询
            if (StringUtils.isEmpty(brand.getName())) {
                //参数1：搜索的javaBean的属性名   2：对应的搜索参数
                criteria.andLike("name", "%" + brand.getName() + "%");
            }

            //品牌图片地址
            if (!StringUtils.isEmpty(brand.getImage())) {
                criteria.andLike("image", "%" + brand.getImage() + "%");
            }
            // 品牌的首字母 根据首字母查询   where letter=?
            if (!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andLike("letter", "%" + brand.getLetter() + "%");
            }
            // 品牌id
            if (!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andEqualTo("id", brand.getId());
            }
            // 排序
            if (!StringUtils.isEmpty(brand.getSeq())) {
                criteria.andEqualTo("seq", brand.getSeq());
            }
        }
        return example;
    }

    /**
     * 添加：一个品牌
     *
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    /**
     *修改： 根据id修改
     */
    @Override
    public void update(Brand brand) {
        //这个效率块
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 删除：根据id删除
     */

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

}
