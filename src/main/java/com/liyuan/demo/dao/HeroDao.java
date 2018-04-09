package com.liyuan.demo.dao;

import com.liyuan.demo.entity.Hero;

import java.util.List;

/**
 * @Author:LiYuan
 * @description:
 * @Date:Create in 11:23 2018/2/8
 * @Modified By:
 */
public interface HeroDao {
    List<Hero> queryAll();

    Hero findById(Integer id);

    Integer saveHero(Hero hero);

    Integer updateHero(Hero hero);

    Integer deleteHero(Integer id);
}
