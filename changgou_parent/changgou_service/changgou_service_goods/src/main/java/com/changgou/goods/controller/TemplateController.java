package com.changgou.goods.controller;

import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.TemplateService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/28 14:43
 */
@RestController
@RequestMapping("/template")
@CrossOrigin
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    /**
     * 查询：动态条件分页
     */
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody Template template, @PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Template> pageInfo = templateService.findPage(template, page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "ok", pageInfo);

    }

    /**
     * 查询：分页
     */
    @GetMapping("/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Template> pageInfo = templateService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "ok", pageInfo);
    }

    /**
     * 查询：动态条件
     */
    @PostMapping("/search")
    public Result<List<Template>> findList(@RequestBody Template template) {
        List<Template> templates = templateService.findList(template);
        return new Result<List<Template>>(true, StatusCode.OK, "ok", templates);
    }

    /**
     * 查询：所有
     */
    @GetMapping
    public Result<List<Template>> findAll() {
        List<Template> templates = templateService.findAll();
        return new Result<List<Template>>(true, StatusCode.OK, "ok", templates);
    }

    /**
     * 查询：根据id
     */
    @GetMapping("/{id}")
    public Result<Template> findById(@PathVariable(value = "id") Integer id) {
        Template template = templateService.findById(id);
        return new Result<Template>(true, StatusCode.OK, "ok", template);
    }

    /**
     * 添加：新增
     */
    @PostMapping
    public Result add(@RequestBody Template template) {
        templateService.add(template);
        return new Result(true, StatusCode.OK, "ok");
    }
    /**
     * 删除:根据ID
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(value = "id") Integer id){
        templateService.delete(id);
        return new Result(true,StatusCode.OK,"ok");
    }
    /**
     * 更新：修改
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Template template,@PathVariable(value = "id") Integer id){
        template.setId(id);
        templateService.update(template);
        return new Result(true,StatusCode.OK,"ok");
    }

}

