package com.Jyoti.blog.BlogappApi.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(length = 100,nullable = false)
    private String title;

    @Column(length = 10000)
    private String content;

    private String imageLink;

    private Date   createdOn;

    @ManyToOne
    private Category  category;

    @ManyToOne
    private User user;
}
