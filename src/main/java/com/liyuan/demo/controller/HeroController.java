package com.liyuan.demo.controller;

import com.liyuan.demo.entity.Hero;
import com.liyuan.demo.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:LiYuan
 * @description:
 * @Date:Create in 15:28 2018/2/8
 * @Modified By:
 */
@RestController
@RequestMapping("/hero")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @GetMapping("/get")
    public Map<String,Object> list(){
        Map<String,Object> modelMap = new HashMap<>();
        List<Hero> list = heroService.queryAll();
        modelMap.put("list",list);
        return modelMap;
    }

    @GetMapping("/get/{id}")
    public Map<String,Object> find(@PathVariable("id") Integer id){
        Map<String,Object> modelMap = new HashMap<>();
        Hero hero = heroService.findById(id);
        modelMap.put("hero",hero);
        return modelMap;

    }

    @PostMapping("/post")
    public Map<String,Object> post(@RequestBody Hero hero){
        Map<String,Object> modelMap = new HashMap<>();
        Integer id = heroService.saveHero(hero);
        hero.setId(id);
        modelMap.put("hero",hero);
        return modelMap;
    }

    @PostMapping("/put")
    public Map<String,Object> put(@RequestBody Hero hero){
        Map<String,Object> modelMap = new HashMap<>();
        Integer result = heroService.updateHero(hero);
        modelMap.put("hero",hero);
        return modelMap;
    }
    @GetMapping("/delete/{id}")
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        Map<String,Object> modelMap = new HashMap<>();
        Integer result = heroService.deleteHero(id);
        modelMap.put("result","deleteSuccess");
        return modelMap;

    }
}
