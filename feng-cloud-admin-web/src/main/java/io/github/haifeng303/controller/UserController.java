package io.github.haifeng303.controller;

import com.fengzai.upms.dto.UserInfoDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @PACKAGE_NAME: io.github.haifeng303.controller
 * @author: rhf
 * @ProjectName: feng-cloud
 * @description:
 * @DATE: 2021/5/14
 **/
@Controller
@RequestMapping(value = "/user/")
public class UserController extends BaseController{
    @RequestMapping("info")
    public String info(Model model){
        UserInfoDto sessionUser = this.getSessionUser();
        model.addAttribute("user", sessionUser);
        return "user/info";
    }
}
