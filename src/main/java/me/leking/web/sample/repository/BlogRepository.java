package me.leking.web.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import me.leking.web.sample.entity.Post;

/**
 * Created by jinlei on 2017/4/20.
 */
public interface BlogRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

//    /**
//     * 使用SQL语句方式直接查询。很方便，不建议。
//     * 建议尽可能使用JpaSpecificationExecutor、Specification做复杂查询
//     * @param id
//     * @return
//     */
//    @Query("select t where blog t where t.id = :id")
//    Post findById(@Param("id")String id);

}
