package com.codeshu.entity;

import java.io.Serializable;

/**
 * Mybatis的映射技巧(Student)实体类
 *
 * @author makejava
 * @since 2023-08-16 15:40:43
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 751172260466109106L;

    private Long id;
    /**
     * 学生名称
     */
    private String studentName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}

