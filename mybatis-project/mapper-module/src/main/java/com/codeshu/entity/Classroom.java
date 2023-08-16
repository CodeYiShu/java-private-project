package com.codeshu.entity;

import java.io.Serializable;

/**
 * Mybatis的映射技巧(Classroom)实体类
 *
 * @author makejava
 * @since 2023-08-16 15:40:43
 */
public class Classroom implements Serializable {
    private static final long serialVersionUID = -82650633479945679L;

    private Long id;
    /**
     * 教室名称
     */
    private String className;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}

