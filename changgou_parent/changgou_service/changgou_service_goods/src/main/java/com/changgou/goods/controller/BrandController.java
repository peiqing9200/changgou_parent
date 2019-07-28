package com.changgou.goods.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/26 17:48
 */
@RestController
@RequestMapping("/brand")
@CrossOrigin //允许跨域
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 查询所有品牌
     */
    @GetMapping
    public Result<Brand> findAll() {
        List<Brand> brandList = brandService.findAll();

        return new Result<Brand>(true, StatusCode.OK, "查询成功", brandList);
    }

    /**
     * 根据id查询
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable(value = "id") Integer id) {
        Brand brand = brandService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", brand);
    }

    /**
     * 根据条件查询
     */
    @PostMapping("/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand) {
        List<Brand> brands = brandService.findList(brand);

        return new Result<List<Brand>>(true, StatusCode.OK, "查询成功", brands);
    }

    /**
     * 分页查询
     */
    @GetMapping("/{page}/{size}")
    public Result<List<Brand>> findPage(@PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Brand> brands = brandService.findPage(page, size);
        return new Result<List<Brand>>(true, StatusCode.OK, "查询成功", brands);
    }

    /**
     * 根据条件分页查询
     */
    @PostMapping("/search/{page}/{size}")
    public Result<List<Brand>> findPage(@RequestBody Brand brand, @PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Brand> brands = brandService.findPage(brand, page, size);
        return new Result<List<Brand>>(true, StatusCode.OK, "查询成功", brands);
    }

    /**
     * 添加一个品牌
     */
    @PostMapping
    public Result add(@RequestBody Brand brand) {
        brandService.add(brand);
        return new Result(true, StatusCode.OK, "添加成功", "");
    }

    /**
     * 根据id修改
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Brand brand, @PathVariable(value = "id") Integer id) {
        brand.setId(id);
        brandService.update(brand);
        return new Result(true, StatusCode.OK, "修改成功", "");
    }

    /**
     * 根据id删除数据
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(value = "id") Integer id) {
        brandService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功", "");
    }


}
