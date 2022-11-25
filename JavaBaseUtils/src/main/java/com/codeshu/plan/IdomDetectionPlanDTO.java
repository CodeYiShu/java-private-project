package com.codeshu.plan;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* 巡检计划
*
* @author Mark sunlightcs@gmail.com
* @since 3.0 2022-11-16
*/
@Data
public class IdomDetectionPlanDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer detectionType;
    private String planName;
    private Date planStartDate;
    private Date planEndDate;
    private Integer aheadMinute;
    private Integer repeatPeriod;
    private String dayRepeat;
    private String monthRepeat;
    private String weekRepeat;
    private Integer planStatus;
    private String remark;
    private Date createDate;
    private Date updateDate;
    private Long creator;
    private Long updater;

    //下发时间点
    private List<String> issueTimes;

}
