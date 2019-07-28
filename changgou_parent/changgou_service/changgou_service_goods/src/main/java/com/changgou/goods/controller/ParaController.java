package com.changgou.goods.controller;

import com.changgou.goods.pojo.Para;
import com.changgou.goods.service.ParaService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/28 16:37
 */
@RestController
@RequestMapping("/para")
@CrossOrigin
public class ParaController {
    @Autowired
    private ParaService paraService;

    /**
     * 查询：动态条件分页
     */
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody Para para, @PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Para> pageInfo = paraService.findPage(para, page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "ok", pageInfo);
    }

    /**
     * 查询：分页
     */
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Para> pageInfo = paraService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "ok", pageInfo);
    }

    /**
     * 查询：动态条件
     */
    @GetMapping("/search")
    public Result<List<Para>> findList(@RequestBody Para para) {
        List<Para> list = paraService.findList(para);
        return new Result<List<Para>>(true, StatusCode.OK, "ok", list);
    }

    /**
     * 查询：所有
     */
    @GetMapping
    public Result<List<Para>> findAll() {
        List<Para> paras = paraService.findAll();
        return new Result<List<Para>>(true, StatusCode.OK, "OK", paras);
    }

    /**
     * 查询：根据ID
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable(value = "id") Integer id) {
        Para para = paraService.findById(id);
        return new Result(true, StatusCode.OK, "ok", para);
    }

    /**
     * 新增：添加
     */
    @PostMapping
    public Result add(@RequestBody Para para) {
        paraService.add(para);
        return new Result(true, StatusCode.OK, "ok");

    }

    /**
     * 删除：根据id
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(value = "id") Integer id) {
        paraService.delete(id);
        return new Result(true, StatusCode.OK, "ok");
    }
    /**
     * 更新：根据id修改
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Para para,@PathVariable(value = "id")Integer id){
        para.setId(id);
        paraService.update(para);
        return new Result(true,StatusCode.OK,"ok");
    }
}
