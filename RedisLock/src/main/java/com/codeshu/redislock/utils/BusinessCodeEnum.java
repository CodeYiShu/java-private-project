package com.codeshu.redislock.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 业务名称和编码的对应规则
 * @author: YouCong
 * @date: 2022/11/22 15:29
 * @description:
 */
@Getter
@RequiredArgsConstructor
public enum BusinessCodeEnum {
    XJRW("XJRW", "巡检任务"),
    DS("DS", "电商");

    //业务名称对应的编码
    private final String code;
    //业务名称
    private final String detail;

    public static BusinessCodeEnum fromCode(String code) {
        for (BusinessCodeEnum orderStatusEnum : BusinessCodeEnum.values()) {
            if (orderStatusEnum.getCode().equals(code)) {
                return orderStatusEnum;
            }
        }
        return null;
    }

    public static BusinessCodeEnum fromDetail(String detail) {
        for (BusinessCodeEnum orderStatusEnum : BusinessCodeEnum.values()) {
            if (orderStatusEnum.getDetail().equals(detail)) {
                return orderStatusEnum;
            }
        }
        return null;
    }
}
