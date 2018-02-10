package me.leking.web.sample.api;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import me.leking.web.sample.model.PrevalentMessage;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 跨域session设置
 * Created by jinlei on 2017/7/22.
 */

@Controller
@RequestMapping("/pub/access_token")
public class CrossDomainSessionController {

    /**
     * 创建cookie
     * @param response
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseBody
    public PrevalentMessage createSession(HttpServletResponse response) {

        Cookie cookie = new Cookie("token", UUID.randomUUID().toString());
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(86400);
        cookie.setValue("test");
        response.addCookie(cookie);

        return new PrevalentMessage("ok");
    }

    /**
     * 查询创建的cookie
     * @param request
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public PrevalentMessage findSession(HttpServletRequest request) {

        Cookie cookie = WebUtils.getCookie(request, "token");

        return new PrevalentMessage(cookie.getValue());
    }

    /**
     * 跨域查询创建的cookie
     * @param request
     * @return
     */
    @RequestMapping(value ="/get", produces =MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public PrevalentMessage callback(HttpServletRequest request) {

        Cookie cookie = WebUtils.getCookie(request, "token");

        return new PrevalentMessage(cookie.getValue());
    }

}
