package com.ustc.login.controller;

import com.ustc.entity.SessionUserBean;
import com.ustc.entity.UserDO;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.login.service.UserService;
import com.ustc.utils.CommonResult;
import com.ustc.utils.CommonResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 用户名和密码验证
 * URL = /user/login
 * @author 田宝宁
 * @date 2022/03/07
 */
@Api(tags = "登录及退出")
@RestController
@RequestMapping("/security")
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录", notes = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query", required = true),
    })
    @PostMapping("/login")
    public CommonResult<String> login(String username, String password,
                                      @ApiIgnore HttpSession httpSession) {

        boolean hasUser = userService.findUserByUsername(username);

        if(hasUser){
            // 正确的密码
            String correctPasswd = userService.getPasswordByUsername(username);
            if (!StringUtils.isEmpty(username) && correctPasswd.equals(password)) {
                // 登录成功
                SessionUserBean sessionUserBean = new SessionUserBean(username);
                httpSession.setAttribute("loginUser", sessionUserBean);

                return CommonResultUtils.success("登录成功");
            } else {
                // 登录失败
                throw new ServiceException(ServiceExceptionEnum.PASSWORD_WRONG);
            }
        }else{
            UserDO newUser = new UserDO();
            newUser.setCreateTime(new Date());
            UserDO.Profile newProfile = new UserDO.Profile();
            newProfile.setNickname(username);
            newProfile.setGender("Unknown");
            newUser.setProfile(newProfile);
            newUser.setUsername(username);
            newUser.setPassword(password);
            userService.saveUser(newUser);

            SessionUserBean sessionUserBean = new SessionUserBean(username);
            httpSession.setAttribute("loginUser", sessionUserBean);

            return CommonResultUtils.success("登录成功");
        }
    }


    @ApiOperation(value = "登出", notes = "登出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/logout")
    public String logout(String token, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/index.html";
    }

}