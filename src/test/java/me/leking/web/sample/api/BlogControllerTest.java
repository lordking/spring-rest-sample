package me.leking.web.sample.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.leking.web.sample.Constants;
import me.leking.web.sample.config.*;
import me.leking.web.sample.entity.Post;
import me.leking.web.sample.model.BlogForm;
import me.leking.web.sample.repository.BlogRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 *
 * 对controller的测试样例。
 * ContextConfiguration，根据AppInitializer导入的类来确定。
 *
 * Created by jinlei on 2017/6/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
    AppConfig.class,
    Jackson2ObjectMapperConfig.class,
    DataSourceConfig.class,
    JpaConfig.class,
    DataJpaConfig.class,
    WebConfig.class,
})
@WebAppConfiguration
public class BlogControllerTest {

    private static final Logger log = LoggerFactory.getLogger(BlogControllerTest.class);

    @Inject
    WebApplicationContext wac;

    @Inject
    ObjectMapper objectMapper;

    @Inject
    private BlogRepository blogRepository;

    private MockMvc mvc;

    private Post post;

    @BeforeClass
    public static void beforeClass() {
        log.debug("==================before class=========================");
    }

    @AfterClass
    public static void afterClass() {
        log.debug("==================after class=========================");
    }

    @Before
    public void setup() {
        log.debug("==================before test case=========================");
        mvc = webAppContextSetup(this.wac).build();

        //清除之前所有的数据
        this.blogRepository.deleteAll();

        //创建一个新的日志，作为mock
        Post input = new Post();
        input.setSubject("xx");
        input.setContent("contentxxx");
        post = this.blogRepository.save(input);

    }

    @After
    public void tearDown() {
        log.debug("==================after test case=========================");
    }

    @Test
    public void create() throws Exception {
        log.debug("================== create a blog =========================");

        BlogForm form = new BlogForm();
        form.setSubject("测试1");
        form.setContent("内容内容内容内容1");

        MvcResult response = mvc.perform(
            post(Constants.URI_API + "/blogs").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(form))
        )
            .andExpect(status().isOk())
            .andReturn();

        log.debug("");
        log.debug("result to create() ==>" + response.getResponse().getContentAsString());
        log.debug("");
    }

    @Test
    public void findAll() throws Exception {
        log.debug("================== find all of blogs =========================");

        MvcResult response = mvc.perform(
            get(Constants.URI_API + "/blogs")
        )
            .andExpect(status().isOk())
            .andReturn();

        log.debug("");
        log.debug("result to findAll() ==>" + response.getResponse().getContentAsString());
        log.debug("");
    }

    @Test
    public void findOneById() throws Exception {
        log.debug("================== find a blog =========================");

        MvcResult response = mvc.perform(
            get(Constants.URI_API + "/blogs/" + this.post.getId())
        )
            .andExpect(status().isOk())
            .andReturn();

        log.debug("");
        log.debug("result ==>" + response.getResponse().getContentAsString());
        log.debug("");
    }

    @Test
    public void updateBlog() throws Exception {
        log.debug("================== update a blog =========================");

        BlogForm form = new BlogForm();
        form.setSubject("测试xxx");
        form.setContent("内容内容内容内容xxx");

        MvcResult response = mvc.perform(
            put(Constants.URI_API + "/blogs/" + this.post.getId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(form))
        )
            .andExpect(status().isOk())
            .andReturn();

        log.debug("");
        log.debug("result ==>" + response.getResponse().getContentAsString());
        log.debug("");
    }

    @Test
    public void deleteBlog() throws Exception {
        log.debug("================== delete a blog =========================");

        MvcResult response = mvc.perform(
            delete(Constants.URI_API + "/blogs/" + this.post.getId())
        )
            .andExpect(status().isOk())
            .andReturn();

        log.debug("");
        log.debug("result ==>" + response.getResponse().getContentAsString());
        log.debug("");
    }
}
