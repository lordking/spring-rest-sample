package me.leking.web.sample.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import me.leking.web.sample.config.ErrorCodeConfig;
import me.leking.web.sample.exception.RestException;
import me.leking.web.sample.model.PrevalentMessage;



/**
 * 默认根目录显示
 * Created by jinlei on 2017/4/18.
 */
@RestController
@RequestMapping("/api")
public class RootController {

    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    /**
     * 访问任意未配置的路由，都会以JSON返回错误。
     * @return
     */
    @RequestMapping(value = "/*/**")
    @ResponseBody
    public PrevalentMessage index() {

        throw new RestException(ErrorCodeConfig.NOTFOUND);
    }

}
