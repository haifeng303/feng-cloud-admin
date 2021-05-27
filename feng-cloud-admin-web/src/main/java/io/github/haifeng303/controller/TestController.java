package io.github.haifeng303.controller;

import com.alibaba.fastjson.JSONObject;
import com.fengzai.common.res.ResultResponse;
import com.fengzai.upms.dto.UserInfoDto;
import com.fengzai.upms.feign.UserFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @PACKAGE_NAME: io.github.haifeng303.controller
 * @author: rhf
 * @ProjectName: feng-cloud
 * @description:
 * @DATE: 2021/5/26
 **/
@RestController
@RequestMapping("/test/")
public class TestController {
    @Resource
    UserFeign userFeign;
    @GetMapping("1")
    public String test1(){
        ResultResponse<UserInfoDto> admin = userFeign.getUserByUserName("admin");
        return JSONObject.toJSONString(admin);
    }
}
