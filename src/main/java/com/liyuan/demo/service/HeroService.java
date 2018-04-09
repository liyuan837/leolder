package com.liyuan.demo.service;

import com.liyuan.demo.entity.Hero;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:LiYuan
 * @description:
 * @Date:Create in 11:24 2018/2/8
 * @Modified By:
 */

public interface HeroService {
    List<Hero> queryAll();

    Hero findById(Integer id);

    Integer saveHero(Hero hero);

    Integer updateHero(Hero hero);

    Integer deleteHero(Integer id);
}
