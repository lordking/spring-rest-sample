package me.leking.web.sample.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


/**
 * 整体系统启动器。
 * <p>
 * 整体系统设计原则：
 * 1、整体系统框架采用习惯大于配置的设计原则。系统中如非必须，尽可能减少xml配置。比如Spring、Web.xml都不推荐使用XML配置方式。
 * 2、面向Rest API设计，如非必须，禁止使用J2EE做页面渲染技术。
 * 3、前后端分离技术，前端技术框架与服务无关
 * 4、JPA持久化技术，不只是数据持久化，更是对象持久化
 * <p>
 * Created by jinlei on 2017/4/18.
 */

@Order(0)
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 基础配置
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
            AppConfig.class, //App配置
            Jackson2ObjectMapperConfig.class, //Json序列化配置
            DataSourceConfig.class, //数据库连接池配置
            JpaConfig.class, //JPA的配置, 使用Hibernate做对象持久化
            DataJpaConfig.class, //JPA数据仓库配置，指定仓库位置
            SecurityConfig.class, //安全管理框架配置
//            HttpSessionConfig.class, //session配置, 不推荐
//            WebSocketConfig.class,
        };
    }

    /**
     * Servlet配置
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
            WebConfig.class,    //web.xml配置
            SwaggerConfig.class  //RestAPI文档自动输出
        };
    }

    /**
     * 路由配置
     *
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Web服务过滤器
     *
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {

        //所有内容按UTF-8编码
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);

        //跨域访问支持
        Filter corsfilter = new CORSFilter();

        return new Filter[]{encodingFilter, corsfilter};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        //增加上传文件的配置
        registration.setMultipartConfig(getMultipartConfigElement());
    }

    /**
     * 原本使用common-fileupload处理文件上传，通过在WebConfig.java中设置CommonsMultipartResolver。
     * 但是一直没有上传成功。对比样例代码，没有发现错误。检查common-fileupload.jar文件，没有发现遗漏。
     *
     * 样例代码是可以上传成功的，但这个项目就是不能成功。这个奇怪的问题，只能在此记录下来。等待有志之士解开谜团。
     *
     * @return
     */
    private MultipartConfigElement getMultipartConfigElement() {

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
            ""
        );
        return multipartConfigElement;
    }
}
