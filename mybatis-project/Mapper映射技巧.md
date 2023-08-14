# 一、一对多（对象集合）

代码查看：yunshuo-train 的 TrainingJobDao 的 getTemplateList()

## 1、对象

查询出来的对象：
```JAVA
@Data  
@ApiModel(value = "模型训练任务模板列表的响应参数")  
public class TrainingJobTemplateResponse implements Serializable {  
   private static final long serialVersionUID = 1L;  
  
   @ApiModelProperty("项目ID")  
   @JsonProperty("id")  
   private Long projectId;  
  
   @ApiModelProperty("项目名称")  
   @JsonProperty("name")  
   private String projectName;  
  
   @ApiModelProperty("训练类型")  
   @JsonProperty("children")  
   private List<TrainingJobType> typeList;  
  
   @Data  
   public static class TrainingJobType implements Serializable {  
      private static final long serialVersionUID = 1L;  
  
      @ApiModelProperty("训练类型：1-图像分类 2-物体检测 3-图像分割")  
      @JsonIgnore  
      private Integer type;  
  
      @ApiModelProperty("训练类型名称")  
      @JsonProperty("name")  
      private String typeName;  
  
      @ApiModelProperty("模板")  
      @JsonProperty("children")  
      private List<TrainingJobTemplate> templateList;  
   }  
  
   @Data  
   public static class TrainingJobTemplate implements Serializable {  
      private static final long serialVersionUID = 1L;  
  
      @ApiModelProperty("训练任务ID（模板ID）")  
      @JsonProperty("id")  
      private Long templateId;  
  
      @ApiModelProperty("模板名称")  
      @JsonProperty("name")  
      private String templateName;  
   }  
}
```

## 2、映射 Map

```xml
<!--模板列表Map-->  
<resultMap id="getTemplateListMap" type="io.yunshuo.response.TrainingJobTemplateResponse">  
    <!--结果集中projectId和projectName相同的分为一组-->  
    <result property="projectId" column="projectId"/>  
    <result property="projectName" column="projectName"/>  
    <!--项目下的所有模板类型-->  
    <association property="typeList" resultMap="typeListMap"/>  
</resultMap>  

<!--模板类型Map-->  
<resultMap id="typeListMap" type="io.yunshuo.response.TrainingJobTemplateResponse$TrainingJobType">  
    <!--结果集中type相同的分为一组-->  
    <result property="type" column="type"/>  
    <!--模板类型下的所有模板-->  
    <association property="templateList" resultMap="templateListMap"/>  
</resultMap>  

<!--模板Map-->  
<resultMap id="templateListMap" type="io.yunshuo.response.TrainingJobTemplateResponse$TrainingJobTemplate">  
    <!--结果集中templateName和templateId相同的分为一组-->  
    <result property="templateName" column="templateName"/>  
    <result property="templateId" column="templateId"/>  
</resultMap>
```

## 3、一条查询 SQL

SQL 只需要一条：
```SQL
<select id="getTemplateList" resultMap="getTemplateListMap">
	SELECT DISTINCT T1.project_id AS projectId,
					T2.name AS projectName,
					T1.type,
					T1.id   AS templateId,
					T1.name AS templateName
	FROM training_job AS T1
			 JOIN project AS T2 ON T1.project_id = T2.id
			 JOIN project_member AS T3 ON T2.id = T3.project_id
	WHERE T1.del_flag = 0
	  AND T1.is_template = 1
	  <if test="request.userId != null">
		  AND T3.user_id = #{request.userId}
	  </if>
</select>
```

# 二、一对多（简单类型）

查看 power-grid-kg 的 DwWorkTicketDao 的 riskPageList()

## 1、对象

对象如下：
```JAVA
@Data  
@EqualsAndHashCode(callSuper = false)  
@ApiModel(value = "工作票评估列表的出参")  
public class DwWorkTicketPageListResponse implements Serializable {  
   private static final long serialVersionUID = 1L;  
  
   @ApiModelProperty(value = "主键")  
   private Long id;  
  
   @ApiModelProperty(value = " 工作票ID")  
   private String ticketId;  
  
   @ApiModelProperty(value = "工作票名称")  
   private String name;  
  
   @ApiModelProperty(value = "工作票类型 1:配电第一种工作票，2:配电第二种工作票，3:变电第一种工作票，4:变电第二种工作票。")  
   private Integer type;  
  
   @ApiModelProperty(value = "工作票描述")  
   private String description;  
  
   @ApiModelProperty(value = "工作票实际完成时间")  
   @JsonFormat(pattern = DateUtils.DATE_NOT_SECOND_PATTERN)  
   private Date finishTime;  
  
   @ApiModelProperty(value = "工作票作业人id")  
   private List<Long> workPersonIds;  
  
   @ApiModelProperty(value = "工作票作业人名字")  
   private List<String> workPersonNames;  
}
```

## 2、映射 Map

使用 `collection`：
```XML
<resultMap type="io.yunshuo.response.DwWorkTicketPageListResponse" id="riskPageListMap">  
    <result property="id" column="id"/>  
    <result property="ticketId" column="ticket_id"/>  
    <result property="name" column="name"/>  
    <result property="type" column="type"/>  
    <result property="finishTime" column="finish_time"/>  
    <result property="description" column="description"/>  
    <collection property="workPersonIds" ofType="java.lang.Long">  
        <result column="workPersonId"/>  
    </collection>  
    <collection property="workPersonNames" ofType="java.lang.String">  
        <result column="workPersonName"/>  
    </collection>  
</resultMap>
```

## 3、一条查询 SQL

如下：
```SQL
<select id="riskPageList" resultMap="riskPageListMap">  
    SELECT T1.id,T1.ticket_id,T1.name,T1.type,  
    T1.finish_time,T1.description,T3.id workPersonId,T3.name workPersonName  
    FROM dw_work_ticket T1  
    LEFT JOIN dw_work_ticket_person T2 ON T1.id = T2.work_ticket_id  
    LEFT JOIN dw_person T3 ON T2.person_id = T3.id  
    <where>  
        <if test="request.name != null and request.name != ''">  
            AND T1.name LIKE CONCAT("%",#{request.name},"%")  
        </if>  
        <if test="request.startFinishTime != null and request.endFinishTime">  
            AND DATE_FORMAT(T1.finish_time, '%Y-%m-%d') BETWEEN DATE_FORMAT(#{request.startFinishTime}, '%Y-%m-%d') AND  
            DATE_FORMAT(#{request.endFinishTime}, '%Y-%m-%d')  
        </if>  
    </where>  
</select>
```
