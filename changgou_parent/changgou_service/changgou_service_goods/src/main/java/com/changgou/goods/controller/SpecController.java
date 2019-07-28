package com.changgou.goods.controller;

import com.changgou.goods.pojo.Spec;
import com.changgou.goods.service.SpecService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/28 15:51
 */
@RestController
@RequestMapping("/spec")
@CrossOrigin
public class SpecController {
    @Autowired
    private SpecService specService;

    /**
     * 查询：动态条件分页
     */
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody Spec spec, @PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Spec> pageInfo = specService.findPage(spec, page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "ok", pageInfo);
    }

    /**
     * 查询：分页
     */
    @GetMapping("/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Spec> specPageInfo = specService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "ok", specPageInfo);
    }

    /**
     * 查询：动态条件
     */
    @PostMapping("/search")
    public Result<List<Spec>> findList(@RequestBody Spec spec) {
        List<Spec> list = specService.findList(spec);
        return new Result<List<Spec>>(true, StatusCode.OK, "ok", list);
    }

    /**
     * 查询：所有
     */
    @GetMapping
    public Result<List<Spec>> findAll() {
        List<Spec> specList = specService.findAll();
        return new Result<List<Spec>>(true, StatusCode.OK, "ok", specList);
    }

    /**
     * 查询：根据id
     */
    @GetMapping("/{id}")
    public Result<Spec> findById(@PathVariable(value = "id") Integer id) {
        Spec spec = specService.findById(id);
        return new Result<Spec>(true, StatusCode.OK, "ok", spec);
    }

    /**
     * 添加：新增
     */
    @PostMapping
    public Result add(@RequestBody Spec spec) {
        specService.add(spec);
        return new Result(true, StatusCode.OK, "ok");
    }

    /**
     * 删除：根据id
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(value = "id") Integer id) {
        specService.delete(id);
        return new Result(true, StatusCode.OK, "ok");
    }

    /**
     * 更新：修改
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Spec spec, @PathVariable(value = "id") Integer id) {
        spec.setId(id);
        specService.update(spec);
        return new Result(true, StatusCode.OK, "ok");

    }
}
