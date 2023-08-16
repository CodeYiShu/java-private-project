package com.codeshu.entity;

import java.io.Serializable;

/**
 * Mybatis的映射技巧(ClassroomStudent)实体类
 *
 * @author makejava
 * @since 2023-08-16 15:40:43
 */
public class ClassroomStudent implements Serializable {
    private static final long serialVersionUID = -25812578537226081L;

    private Long id;
    /**
     * 教室id
     */
    private Long classId;
    /**
     * 学生id
     */
    private Long studentId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

}

