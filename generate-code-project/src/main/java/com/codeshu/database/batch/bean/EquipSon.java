package com.codeshu.database.batch.bean;

import lombok.Data;

import java.util.Date;

/**
 * 具体物资表
 *
 * @author makejava
 * @since 2023-10-30 19:55:51
 */
@Data
public class EquipSon {
	private String id;
	/**
	 * 基础表ID
	 */
	private String equipId;
	/**
	 * 物资名称
	 */
	private String suppliesName;
	/**
	 * 数量
	 */
	private String number;
	/**
	 * 用途
	 */
	private String used;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改人
	 */
	private String updateUser;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 删除标志位 0-未删除 1-已删除
	 */
	private String delFlag;
}

