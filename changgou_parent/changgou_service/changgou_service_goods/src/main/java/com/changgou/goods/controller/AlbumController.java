package com.changgou.goods.controller;

import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @作者:qipeiqing
 * @时间:2019/07/28 9:38
 */
@RestController
@RequestMapping("/album")
@CrossOrigin  //允许跨域
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    /**
     * 查询：动态条件分页
     */
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody Album album, @PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Album> pageInfo = albumService.findPage(album, page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "动态查询成功", pageInfo);
    }

    /**
     * 查询：分页
     */
    @GetMapping("/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Album> pageInfo = albumService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * 查询：所有
     */
    @GetMapping
    public Result<List<Album>> findAll() {
        List<Album> all = albumService.findAll();
        return new Result<List<Album>>(true, StatusCode.OK, "查询成功", all);
    }

    /**
     * 查询：动态条件
     */
    @PostMapping("/search")
    public Result<List<Album>> findList(@RequestBody Album album) {
        List<Album> list = albumService.findList(album);
        return new Result<List<Album>>(true, StatusCode.OK, "ok", list);
    }

    /**
     * 查询：根据 id
     */
    @GetMapping("/{id}")
    public Result<Album> findById(@PathVariable(value = "id") Long id) {
        Album album = albumService.findById(id);
        return new Result<Album>(true, StatusCode.OK, "ok", album);
    }

    /**
     * 添加新增
     */
    @PostMapping
    public Result add(@RequestBody Album album) {
        albumService.add(album);
        return new Result(true, StatusCode.OK, "ok", "");
    }

    /**
     * 删除：根据id删除
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(value = "id") Long id) {
        albumService.delete(id);
        return new Result(true, StatusCode.OK, "ok", "");
    }

    /**
     * 更新：修改
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Album album, @PathVariable(value = "id") Long id) {
        album.setId(id);
        albumService.update(album);
        return new Result(true, StatusCode.OK, "ok", "");
    }

}
