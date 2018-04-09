package com.liyuan.demo.service.impl;

import com.liyuan.demo.dao.HeroDao;
import com.liyuan.demo.entity.Hero;
import com.liyuan.demo.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author:LiYuan
 * @description:
 * @Date:Create in 15:13 2018/2/8
 * @Modified By:
 */
@Service
public class HeroServiceImpl implements HeroService{
    @Autowired
    private HeroDao heroDao;

    @Override
    public List<Hero> queryAll() {
        return heroDao.queryAll();
    }

    @Override
    public Hero findById(Integer id) {
        return heroDao.findById(id);
    }

    @Transactional
    @Override
    public Integer saveHero(Hero hero) {
        if(hero.getName() != null && !"".equals(hero.getName().trim())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                hero.setCreatetime(sdf.parse(sdf.format(new Date())));
                Integer result = heroDao.saveHero(hero);
                if(result > 0){
                    return hero.getId();
                }else{
                    throw new RuntimeException("插入失败");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else{
            throw new RuntimeException("信息不完整");
        }
        return 0;
    }

    @Transactional
    @Override
    public Integer updateHero(Hero hero) {
        return heroDao.updateHero(hero);
    }

    @Transactional
    @Override
    public Integer deleteHero(Integer id) {
        return heroDao.deleteHero(id);
    }
}
