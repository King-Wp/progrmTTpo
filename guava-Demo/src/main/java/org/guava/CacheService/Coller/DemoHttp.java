package org.guava.CacheService.Coller;

import org.guava.CacheService.mok.DemoMain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 11653
 * @createTime: 2024/03/15 11:53
 * @package: org.guava.CacheService.Coller
 * @description:
 */
@RestController
@RequestMapping("/resource/personSysDept")
public class DemoHttp {

    @GetMapping("/holle")
    public String test(){
        System.out.println("开始");
        DemoMain.test01();
        return "12312312";
    }

}
