package com.changgou.goods.controller;

import com.changgou.goods.pojo.Category;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.service.CategoryService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/28 17:24
 */
@RestController
@RequestMapping("/categroy")
@CrossOrigin
public class CategroyController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询：动态条件分页
     */
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody Category category, @PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Category> pageInfo = categoryService.findPage(category, page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "ok", pageInfo);
    }

    /**
     * 查询：分页
     */
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Category> pageInfo = categoryService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "ok", pageInfo);
    }

    /**
     * 查询：动态条件
     */
    @GetMapping("/search")
    public Result<List<Category>> findList(@RequestBody Category category) {
        List<Category> list = categoryService.findList(category);
        return new Result<List<Category>>(true, StatusCode.OK, "ok", list);
    }

    /**
     * 查询：所有
     */
    @GetMapping
    public Result<List<Category>> findAll() {
        List<Category> paras = categoryService.findAll();
        return new Result<List<Category>>(true, StatusCode.OK, "OK", paras);
    }

    /**
     * 查询：根据ID
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable(value = "id") Integer id) {
        Category category = categoryService.findById(id);
        return new Result(true, StatusCode.OK, "ok", category);
    }

    /**
     * 新增：添加
     */
    @PostMapping
    public Result add(@RequestBody Category category) {
        categoryService.add(category);
        return new Result(true, StatusCode.OK, "ok");

    }

    /**
     * 删除：根据id
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(value = "id") Integer id) {
        categoryService.delete(id);
        return new Result(true, StatusCode.OK, "ok");
    }

    /**
     * 更新：根据id修改
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Category category, @PathVariable(value = "id") Integer id) {
        category.setId(id);
        categoryService.update(category);
        return new Result(true, StatusCode.OK, "ok");
    }

    /**
     * 查询：根据父节点id
     */
    @RequestMapping("/list/{id}")
    public Result<Category> findByParentId(@PathVariable(value = "id") Integer id) {
        List<Category> list = categoryService.findByParentId(id);
        return new Result<Category>(true, StatusCode.OK, "ok", list);
    }
}
