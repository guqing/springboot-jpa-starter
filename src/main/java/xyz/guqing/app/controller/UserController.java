package xyz.guqing.app.controller;

import cn.hutool.json.JSONUtil;
import com.xkcoding.justauth.AuthRequestFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.app.model.dto.UserDTO;
import xyz.guqing.app.model.entity.User;
import xyz.guqing.app.model.params.LoginParam;
import xyz.guqing.app.security.support.MyUserDetails;
import xyz.guqing.app.security.support.MyUserDetailsServiceImpl;
import xyz.guqing.app.security.utils.IpUtil;
import xyz.guqing.app.security.utils.JwtTokenUtil;
import xyz.guqing.app.security.utils.SecurityUserHelper;
import xyz.guqing.app.service.UserService;
import xyz.guqing.app.utils.PageInfo;
import xyz.guqing.app.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

/**
 * 用户Controller
 * @author guqing
 * @date 2019/8/11
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "UserController-用户api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
     this.userService = userService;
    }

    @GetMapping("/info")
    @ApiOperation(value="获取用户信息", notes="需要先登录获取Token才可以访问")
    public Result<UserDTO> getUserInfo(HttpServletRequest request) {
        MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
        Integer userId = user.getId();
        UserDTO userInfo = userService.getUserInfo(userId);
        return Result.ok(userInfo);
    }
}
