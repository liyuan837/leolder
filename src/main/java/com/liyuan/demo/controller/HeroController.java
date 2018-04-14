package com.liyuan.demo.controller;

import com.liyuan.demo.entity.Hero;
import com.liyuan.demo.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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

    /**
     * 实现文件上传
     * */
    @RequestMapping(value="/fileUpload",method = RequestMethod.POST)
    @ResponseBody
    public String fileUpload(MultipartFile file){

        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();

        String path = System.getProperty("user.dir") + "/uploadFile" ;
        System.out.println(path);
        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }

    //文件下载相关代码
    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "1.png";// 设置文件名，根据业务需要替换成要下载的文件名
        System.out.println(fileName);
        if (fileName != null) {
            //设置文件路径
            String realPath = "D:/workspace_idea/leolder/leolder/uploadFile/";
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

}
