package com.feng.framework.ycnweapp;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Course
 * @Author 小风谷
 * @Date 2021/3/6 21:34
 * @Version 1.0
 * @Description 课程表
 */
@Data
@ToString
@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //课程名称
    @Column(name = "CourseName")
    private String CourseName;

    //课程描述
    @Column(name = "CourseDescription")
    private String CourseDescription;

}
