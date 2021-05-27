package io.github.haifeng303.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.system.RuntimeInfo;
import cn.hutool.system.SystemUtil;
import io.github.haifeng303.pojo.dto.SystemCoreDto;
import io.github.haifeng303.util.SystemCoreUtil;
import com.fengzai.common.res.ResultResponse;
import com.fengzai.common.util.Builder;
import com.fengzai.upms.dto.MenuDto;
import com.fengzai.upms.dto.UserInfoDto;
import com.fengzai.common.res.SystemCodeEnum;
import com.fengzai.upms.feign.MenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @PACKAGE_NAME: io.github.haifeng303.controller
 * @author: rhf
 * @ProjectName: feng-cloud
 * @description:
 * @DATE: 2021/5/14
 **/
@Controller
public class IndexController extends BaseController{
    @Autowired
    private MenuFeign menuFeign;

    @RequestMapping("/index")
    public String index(Model model){
        UserInfoDto sessionUser = this.getSessionUser();
        model.addAttribute("user", sessionUser);
        return "index";
    }

    @RequestMapping("main")
    public String main(Model m) {
        SystemCoreDto systemCoreDto = SystemCoreUtil.obtainSystemCoreParams();
        m.addAttribute("core", systemCoreDto);
        m.addAttribute("user", getSessionUser());

        return "main";
    }


    @RequestMapping("/menu/leftList")
    @ResponseBody
    public List<MenuDto> leftList() {
        UserInfoDto sessionUser = this.getSessionUser();
        ResultResponse<List<MenuDto>> listResultResponse = menuFeign.listMenuByUserId(SystemCodeEnum.UPMS, sessionUser.getUserId());
        return  listResultResponse.getBody();
    }
}
