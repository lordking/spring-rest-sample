package me.leking.web.sample.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * 数据库实体对象
 * Created by jinlei on 2017/4/20.
 */
@Entity
@Table(name = "post")
@Data
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content")
    @Size(max = 2000)
    private String content;

    @Column(updatable = false, name = "created_date")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
}
