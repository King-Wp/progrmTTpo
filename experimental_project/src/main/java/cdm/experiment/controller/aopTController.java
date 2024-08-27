package cdm.experiment.controller;

import cdm.experiment.programming.RecordFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 11653
 * @createTime: 2024/07/05 15:54
 * @package: cdm.experiment.controller
 * @description:
 */

@RestController
@RequestMapping("/api")
public class aopTController {

    @RecordFunction(functionName = "测试")
    @GetMapping("/sysFg")
    public void sysFg(){
        System.out.println("sysFg");
    }

}
