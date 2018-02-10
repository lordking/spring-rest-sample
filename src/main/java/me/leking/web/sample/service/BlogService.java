package me.leking.web.sample.service;

import me.leking.web.sample.common.DTOUtils;
import me.leking.web.sample.entity.Post;
import me.leking.web.sample.model.BlogForm;
import me.leking.web.sample.repository.BlogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.leking.web.sample.model.Blog;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by jinlei on 2017/4/20.
 */
@Service
@Transactional
public class BlogService {

    private static final Logger log = LoggerFactory.getLogger(BlogService.class);

    @Inject
    BlogRepository blogRepository;

    /**
     * 保存
     * @param form
     * @return
     */
    public Blog save(BlogForm form) {
        log.debug("save a blog");

        //保存数据实体对象
        Post post = DTOUtils.map(form, Post.class);
        Post saved = this.blogRepository.save(post);

        //输出业务对象
        Blog blog = DTOUtils.map(saved, Blog.class);

        return blog;
    }

    /**
     * 查询一条记录
     * @param id
     * @return
     */
    public Blog findOneById(Long id) {
        log.debug("find a blog by id");

        //查询数据实体对象
        Post post = this.blogRepository.findOne(id);

        //输出业务对象
        Blog blog = DTOUtils.map(post, Blog.class);

        return blog;
    }

    /**
     * 查询全部
     * @return
     */
    public List<Blog> findAll() {
        log.debug("find all of blogs");

        //查询数据实体对象
        List<Post> posts = this.blogRepository.findAll();

        //输出业务对象
        List<Blog> blogs = DTOUtils.mapList(posts, Blog.class);

        return blogs;
    }

    /**
     * 更新一条记录
     * @param id
     * @param form
     * @return
     */
    public Blog updateOneById(Long id, BlogForm form) {
        log.debug("update a blog by id");

        //查询数据实体对象
        Post post = this.blogRepository.findOne(id);

        //更新实体对象
        DTOUtils.mapTo(form, post);
        Post saved = this.blogRepository.save(post);

        //输出业务对象
        Blog blog = DTOUtils.map(post, Blog.class);

        return blog;
    }

    /**
     * 删除一条记录
     * @param id
     * @return
     */
    public boolean deleteOneById(Long id) {
        log.debug("delete a blog by id");

        //查询数据实体对象
        Post post = this.blogRepository.findOne(id);

        if (post == null) {

        }

        this.blogRepository.delete(id);
        log.debug("delete ==> " + post);
        return true;
    }
}
