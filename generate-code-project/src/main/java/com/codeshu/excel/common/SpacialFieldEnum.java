package com.codeshu.excel.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 特殊字段
 */
@Getter
@RequiredArgsConstructor
public enum SpacialFieldEnum {
	// 三个字以上
	gmsjzsNum("gmsyzsNum", "GMSYZS_NUM", "高锰酸盐指数"),
	wrshxyl("wrshxylNum", "WRSHXYL_NUM", "五日生化需氧量"),
	fdcjqNum("fdcjqNum", "FDCJQ_NUM", "粪大肠菌群"),
	yhhydwNum("yhhydwNum", "YHHYDW_NUM", "流氧化还原电位"),
	ylzbmhx("ylzbmhxNum", "YLZBMHX_NUM", "阴离子表面活性"),
	gmsjNum("gmsyNum", "GMSY_NUM", "高锰酸钾"),
	codNum("codNum", "COD_NUM", "化学需氧量"),
	xsydNum("xsydNum", "XSYD_NUM", "硝酸盐氮"),
	// 三个字
	ddlNum("ddlNum", "DDL_NUM", "电导率"),
	rjyNum("rjyNum", "RJY_NUM", "溶解氧"),
	tmdNum("tmdNum", "TMD_NUM", "透明度"),
	fhwNum("fhwNum", "FHW_NUM", "氟化物"),
	ljgNum("ljgNum", "LJG_NUM", "六价铬"),
	qhwNum("qhwNum", "QHW_NUM", "氰化物"),
	hffNum("hffNum", "HFF_NUM", "挥发酚"),
	sylNum("sylNum", "SYL_NUM", "石油类"),
	lhwNum("lhwNum", "LHW_NUM", "硫化物"),
	ylsaNum("ylsaNum", "YLSA_NUM", "叶绿素a"),
	zmdNum("zmdNum", "ZMD_NUM", "藻密度"),
	zydNum("zydNum", "ZYD_NUM", "总硬度"),
	lsyNum("lsyNum", "LSY_NUM", "硫酸盐"),
	lvhwNum("lvhwNum", "LVHW_NUM", "氯化物"),
	// 两个字
	swNum("swNum", "SW_NUM", "水温"),
	phNum("phNum", "PH_NUM", "PH"),
	ydNum("ydNum", "YD_NUM", "盐度"),
	adNum("adNum", "AD_NUM", "氨氮"),
	zlNum("zlNum", "ZL_NUM", "总磷"),
	zdNum("zdNum", "ZD_NUM", "总氮"),
	// 一个字
	qianNum("qianNum", "QIAN_NUM", "铅"),
	tongNum("tongNum", "TONG_NUM", "铜"),
	xinNum("xinNum", "XIN_NUM", "锌"),
	xiNum("xiNum", "XI_NUM", "硒"),
	shenNum("shenNum", "SHEN_NUM", "砷"),
	gongNum("gongNum", "GONG_NUM", "汞"),
	geNum("geNum", "GE_NUM", "镉"),
	tieNum("tieNum", "TIE_NUM", "铁"),
	mengNum("mengNum", "MENG_NUM", "锰"),
	;

	private final String fieldName;

	private final String tableName;

	private final String comment;

	public static String getFieldNameByComment(String comment){
		comment = comment.toUpperCase();
		for (SpacialFieldEnum spacialFieldEnum : SpacialFieldEnum.values()) {
			if (comment.contains(spacialFieldEnum.comment.toUpperCase())){
				return spacialFieldEnum.fieldName;
			}
		}
		return null;
	}

	public static String getTableNameByComment(String comment){
		comment = comment.toUpperCase();
		for (SpacialFieldEnum spacialFieldEnum : SpacialFieldEnum.values()) {
			if (comment.contains(spacialFieldEnum.comment.toUpperCase())){
				return spacialFieldEnum.tableName;
			}
		}
		return null;
	}
}
