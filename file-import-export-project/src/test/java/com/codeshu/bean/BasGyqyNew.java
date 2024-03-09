package com.codeshu.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 工业企业污染排放及处理利用情况(G1)-新
 *
 * @author CodeShu 13828965090@136.com
 * @since 1.0.0 2024-01-19
 */

@Data
public class BasGyqyNew implements Serializable {

	/**
	 * 序号
	 */
	private String xh;


	/**
	 * 组织机构代码
	 */
	private String qyfrdm;

	/**
	 * 统一社会信用代码
	 */
	private String tyshxydm;

	/**
	 * 填报单位详细名称
	 */
	private String tbdwxxmc;

	/**
	 * 企业规模代码
	 */
	private String qygmdm;

	/**
	 * 企业规模名称
	 */
	private String qygmmc;

	/**
	 * 统计年份
	 */
	private String nf;

	/**
	 * 行政区代码
	 */
	private String xzqdm;

	/**
	 * 行政区名称
	 */
	private String xzqmc;

	/**
	 * 行业类别代码
	 */
	private String xylbdm;

	/**
	 * 行业类别名称
	 */
	private String xylbmc;

	/**
	 * 曾用名
	 */
	private String cym;

	/**
	 * 法人代表姓名
	 */
	private String frXm;

	/**
	 * 详细地址省(自治区、直辖市)
	 */
	private String xxdzs;

	/**
	 * 详细地址地区(市、州、盟)
	 */
	private String xxdzdq;

	/**
	 * 详细地址县(区、市、旗)
	 */
	private String xxdzx;

	/**
	 * 详细地址乡(镇)
	 */
	private String xxdzXig;

	/**
	 * 详细地址街(村)、门牌号
	 */
	private String xxdzjcmph;

	/**
	 * 中心经度（度）
	 */
	private String zxjdd;

	/**
	 * 中心经度（分）
	 */
	private String zxjdf;

	/**
	 * 中心经度（秒）
	 */
	private String zxjdm;

	/**
	 * 中心纬度（度）
	 */
	private String zxwdd;

	/**
	 * 中心纬度（分）
	 */
	private String zxwdf;

	/**
	 * 中心纬度（秒）
	 */
	private String zxwdm;

	/**
	 * 联系方式联系人
	 */
	private String lxfsLxr;

	/**
	 * 联系方式电话
	 */
	private String lxfsdh;

	/**
	 * 联系方式手机
	 */
	private String lxfssj;

	/**
	 * 联系方式邮政编码
	 */
	private String lxfsyzbm;

	/**
	 * 登记注册类型代码
	 */
	private String djzclxdm;

	/**
	 * 登记注册类型名称
	 */
	private String djzclxmc;

	/**
	 * 开业时间（年）
	 */
	private String kysjn;

	/**
	 * 开业时间（月）
	 */
	private String kysjy;

	/**
	 * 排水去向类型名称
	 */
	private String psqxlxmc;

	/**
	 * 排水去向类型代码
	 */
	private String psqxlxdm;

	/**
	 * 排入的污水处理厂代码
	 */
	private String prdwsclcdm;

	/**
	 * 排入的污水处理厂名称
	 */
	private String prdwsclcmc;

	/**
	 * 受纳水体代码
	 */
	private String snstdm;

	/**
	 * 受纳水体名称
	 */
	private String snstmc;

	/**
	 * 排污许可证编号
	 */
	private String pwxkzbh;

	/**
	 * 排污登记编号
	 */
	private String pwdjbh;

	/**
	 * 工业总产值（当年价格）（万元）
	 */
	private String gyzczdnjg;

	/**
	 * 年正常生产时间（小时）
	 */
	private String nzcscsj;

	/**
	 * 工业用水量
	 */
	private String gyysl;

	/**
	 * 取水量（吨）
	 */
	private String qsl;

	/**
	 * 重复用水量
	 */
	private String zfysl;

	/**
	 * 煤炭消费量（吨）
	 */
	private String mtxfl;

	/**
	 * 其中：燃料煤消费量（吨）
	 */
	private String rlmxfl;

	/**
	 * 其中：燃料煤平均含硫量（%）
	 */
	private String rlmpjhll;

	/**
	 * 其中：燃料煤平均灰份（%）
	 */
	private String rlmpjhf;

	/**
	 * 其中：燃料煤平均干燥无灰基挥发分（%）
	 */
	private String rlmpjgzwhjhff;

	/**
	 * 燃料油消费量(不含车船用)（吨）
	 */
	private String rlyxflbhccy;

	/**
	 * 燃料油平均含硫量（%）
	 */
	private String rlypjhll;

	/**
	 * 焦炭消费量（吨）
	 */
	private String jtxfl;

	/**
	 * 焦炭平均含硫量（%）
	 */
	private String jtpjhll;

	/**
	 * 焦炭平均灰分（%）
	 */
	private String jtpjhf;

	/**
	 * 天然气消费量（万立方米）
	 */
	private String trqxfl;

	/**
	 * 其他燃料消费量（吨标准煤）
	 */
	private String qtrlxfl;

	/**
	 * 用电量（万千瓦时）
	 */
	private String ydl;

	/**
	 * 工业锅炉数（台）
	 */
	private String gygls;

	/**
	 * 其中：20蒸吨以上的锅炉数（台）
	 */
	private String eszdysdgls;

	/**
	 * 其中：安装脱硫设施的锅炉数（台）
	 */
	private String aztlssdgls;

	/**
	 * 其中：10-20(含)蒸吨之间的锅炉数（台）
	 */
	private String zdzjdgls;

	/**
	 * 其中：10(含)蒸吨以下的锅炉数（台）
	 */
	private String zdyxdgls;

	/**
	 * 工业锅炉蒸吨数（蒸吨）
	 */
	private String gyglzds;

	/**
	 * 其中：20蒸吨以上的蒸吨数（蒸吨）
	 */
	private String eszdysdzds;

	/**
	 * 其中：安装脱硫设施的蒸吨数（蒸吨）
	 */
	private String qaztlssdzds;

	/**
	 * 其中：10-20(含)蒸吨之间的蒸吨数（蒸吨）
	 */
	private String zdzjdzds;

	/**
	 * 其中：10(含)蒸吨以下的蒸吨数（蒸吨）
	 */
	private String zdyxdzds;

	/**
	 * 工业窑炉数（座）
	 */
	private String gyyls;

	/**
	 * 主要原辅材料代码1
	 */
	private String zyyfcldm1;

	/**
	 * 主要原辅材料代码2
	 */
	private String zyyfcldm2;

	/**
	 * 主要原辅材料代码3
	 */
	private String zyyfcldm3;

	/**
	 * 主要原辅材料代码4
	 */
	private String zyyfcldm4;

	/**
	 * 主要原辅材料代码5
	 */
	private String zyyfcldm5;

	/**
	 * 主要原辅材料1
	 */
	private String zyyfcl1;

	/**
	 * 主要原辅材料2
	 */
	private String zyyfcl2;

	/**
	 * 主要原辅材料3
	 */
	private String zyyfcl3;

	/**
	 * 主要原辅材料4
	 */
	private String zyyfcl4;

	/**
	 * 主要原辅材料5
	 */
	private String zyyfcl5;

	/**
	 * 主要原辅材料_计量单位1
	 */
	private String zyyfclJldw1;

	/**
	 * 主要原辅材料_本年实际1
	 */
	private String zyyfclBnsj1;

	/**
	 * 主要原辅材料_计量单位2
	 */
	private String zyyfclJldw2;

	/**
	 * 主要原辅材料_本年实际2
	 */
	private String zyyfclBnsj2;

	/**
	 * 主要原辅材料_计量单位3
	 */
	private String zyyfclJldw3;

	/**
	 * 主要原辅材料_本年实际3
	 */
	private String zyyfclBnsj3;

	/**
	 * 主要原辅材料_计量单位4
	 */
	private String zyyfclJldw4;

	/**
	 * 主要原辅材料_本年实际4
	 */
	private String zyyfclBnsj4;

	/**
	 * 主要原辅材料_计量单位5
	 */
	private String zyyfclJldw5;

	/**
	 * 主要原辅材料_本年实际5
	 */
	private String zyyfclBnsj5;

	/**
	 * 主要产品代码1
	 */
	private String zycpdm1;

	/**
	 * 主要产品代码2
	 */
	private String zycpdm2;

	/**
	 * 主要产品代码3
	 */
	private String zycpdm3;

	/**
	 * 主要产品代码4
	 */
	private String zycpdm4;

	/**
	 * 主要产品代码5
	 */
	private String zycpdm5;

	/**
	 * 主要产品1
	 */
	private String zycp1;

	/**
	 * 主要产品2
	 */
	private String zycp2;

	/**
	 * 主要产品3
	 */
	private String zycp3;

	/**
	 * 主要产品4
	 */
	private String zycp4;

	/**
	 * 主要产品5
	 */
	private String zycp5;

	/**
	 * 主要产品_计量单位1
	 */
	private String zycpJldw1;

	/**
	 * 主要产品_本年实际1
	 */
	private String zycpBnsj1;

	/**
	 * 主要产品_计量单位2
	 */
	private String zycpJldw2;

	/**
	 * 主要产品_本年实际2
	 */
	private String zycpBnsj2;

	/**
	 * 主要产品_计量单位3
	 */
	private String zycpJldw3;

	/**
	 * 主要产品_本年实际3
	 */
	private String zycpBnsj3;

	/**
	 * 主要产品_计量单位4
	 */
	private String zycpJldw4;

	/**
	 * 主要产品_本年实际4
	 */
	private String zycpBnsj4;

	/**
	 * 主要产品_计量单位5
	 */
	private String zycpJldw5;

	/**
	 * 主要产品_本年实际5
	 */
	private String zycpBnsj5;

	/**
	 * 炼钢企业主要炉型代码
	 */
	private String lgqyzylxdm;

	/**
	 * 炼钢企业主要炉型
	 */
	private String lgqyzylx;

	/**
	 * 再生铜、铝、铅、锌企业主要炉型代码
	 */
	private String zstlqxqyzylxdm;

	/**
	 * 再生铜、铝、铅、锌企业主要炉型
	 */
	private String zstlqxqyzylx;

	/**
	 * 原生浆造纸企业主要漂白工艺代码
	 */
	private String ysjzzqyzypbgydm;

	/**
	 * 原生浆造纸企业主要漂白工艺
	 */
	private String ysjzzqyzypbgy;

	/**
	 * 镁生产企业主要生产工艺代码
	 */
	private String mscqyzyscgydm;

	/**
	 * 镁生产企业主要生产工艺
	 */
	private String mscqyzyscgy;

	/**
	 * 防腐涂料使用量（吨）
	 */
	private String fftlsyl;

	/**
	 * 其中：水性防腐涂料使用量（吨）
	 */
	private String sxfftlsyl;

	/**
	 * 其中：溶剂型防腐涂料使用量（吨）
	 */
	private String rjxfftlsyl;

	/**
	 * 工业废水排放量（吨）
	 */
	private String gyfspfl;

	/**
	 * 其中：工业废水直排入环境的（吨）
	 */
	private String gyfszprhjd;

	/**
	 * 其中：排入污水处理厂的（吨）
	 */
	private String prwsclcd;

	/**
	 * 工业废水处理量（吨）
	 */
	private String gyfscll;

	/**
	 * 化学需氧量产生量（吨）
	 */
	private String hxxylcsl;

	/**
	 * 化学需氧量排放量（吨）
	 */
	private String hxxylpfl;

	/**
	 * 氨氮产生量（吨）
	 */
	private String adcsl;

	/**
	 * 氨氮排放量（吨）
	 */
	private String adpfl;

	/**
	 * 总氮产生量（吨）
	 */
	private String zdcsl;

	/**
	 * 总氮排放量（吨）
	 */
	private String zdpfl;

	/**
	 * 总磷产生量（吨）
	 */
	private String zlcsl;

	/**
	 * 总磷排放量（吨）
	 */
	private String zlpfl;

	/**
	 * 石油类产生量（吨）
	 */
	private String sylcsl;

	/**
	 * 石油类排放量（吨）
	 */
	private String sylpfl;

	/**
	 * 工业废水中污染物产生量_挥发酚（千克）
	 */
	private String gyfszwrwcslHff;

	/**
	 * 工业废水中污染物排放量_挥发酚（千克）
	 */
	private String gyfszwrwpflHff;

	/**
	 * 工业废水中污染物产生量_氰化物（千克）
	 */
	private String gyfszwrwcslQhw;

	/**
	 * 工业废水中污染物排放量_氰化物（千克）
	 */
	private String gyfszwrwpflQhw;

	/**
	 * 工业废水中污染物产生量_总砷（千克）
	 */
	private String gyfszwrwcslZs;

	/**
	 * 工业废水中污染物排放量_总砷（千克）
	 */
	private String gyfszwrwpflZs;

	/**
	 * 工业废水中污染物产生量_总铅（千克）
	 */
	private String gyfszwrwcslZq;

	/**
	 * 工业废水中污染物排放量_总铅（千克）
	 */
	private String gyfszwrwpflZq;

	/**
	 * 工业废水中污染物产生量_总镉（千克）
	 */
	private String gyfszwrwcslZg1;

	/**
	 * 工业废水中污染物排放量_总镉（千克）
	 */
	private String gyfszwrwpflZg1;

	/**
	 * 工业废水中污染物产生量_总汞 (千克)
	 */
	private String gyfszwrwcslZg;

	/**
	 * 工业废水中污染物排放量_总汞（千克）
	 */
	private String gyfszwrwpflZg2;

	/**
	 * 工业废水中污染物产生量_总铬（千克）
	 */
	private String gyfszwrwcslZg2;

	/**
	 * 工业废水中污染物排放量_总铬（千克）
	 */
	private String gyfszwrwpflZg3;

	/**
	 * 工业废水中污染物产生量_六价铬（千克）
	 */
	private String gyfszwrwcslLjg;

	/**
	 * 工业废水中污染物排放量_六价铬（千克）
	 */
	private String gyfszwrwpflLjg;

	/**
	 * 工业废气排放量（万立方米）
	 */
	private String gyfqpfl;

	/**
	 * 二氧化硫产生量（吨）
	 */
	private String eyhlcsl;

	/**
	 * 二氧化硫排放量（吨）
	 */
	private String eyhlpfl;

	/**
	 * 氮氧化物产生量（吨）
	 */
	private String dyhwcsl;

	/**
	 * 氮氧化物排放量（吨）
	 */
	private String dyhwpfl;

	/**
	 * 颗粒物产生量（吨）
	 */
	private String klwcsl;

	/**
	 * 颗粒物排放量（吨）
	 */
	private String klwpfl;

	/**
	 * 挥发性有机物（VOCs）产生量（千克）
	 */
	private String hfxyjwvocscsl;

	/**
	 * 挥发性有机物（VOCs）排放量（千克）
	 */
	private String hfxyjwvocspfl;

	/**
	 * 砷及其化合物产生量（千克）
	 */
	private String sjqhhwcsl;

	/**
	 * 砷及其化合物排放量（千克）
	 */
	private String sjqhhwpfl;

	/**
	 * 铅及其化合物产生量（千克）
	 */
	private String qjqhhwcsl;

	/**
	 * 铅及其化合物排放量（千克）
	 */
	private String qjqhhwpfl;

	/**
	 * 镉及其化合物产生量（千克）
	 */
	private String gjqhhwcsl1;

	/**
	 * 镉及其化合物排放量（千克）
	 */
	private String gjqhhwpfl1;

	/**
	 * 汞及其化合物产生量（千克）
	 */
	private String gjqhhwcsl2;

	/**
	 * 汞及其化合物排放量（千克）
	 */
	private String gjqhhwpfl2;

	/**
	 * 铬及其化合物产生量（千克）
	 */
	private String gjqhhwcsl3;

	/**
	 * 铬及其化合物排放量（千克）
	 */
	private String gjqhhwpfl3;

	/**
	 * 废水治理设施数（套）
	 */
	private String fszlsss;

	/**
	 * 废水治理设施处理能力（吨/日）
	 */
	private String fszlssclnl;

	/**
	 * 废水治理设施运行费用（万元）
	 */
	private String fszlssyxfy;

	/**
	 * 废气治理设施数（套）
	 */
	private String fqzlsss;

	/**
	 * 废气治理设施处理能力（立方米/时）
	 */
	private String fqzlssclnl;

	/**
	 * 废气治理设施运行费用（万元）
	 */
	private String fqzlssyxfy;

	/**
	 * 二氧化碳排放量（吨）
	 */
	private String eyhtpfl;

	/**
	 * 备注
	 */
	private String bz;

	/**
	 * 一般工业固体废物产生量（吨）
	 */
	private String ybgygtfwcsl;

	/**
	 * 一般工业固体废物综合利用量（吨）
	 */
	private String ybgygtfwzhlyl;

	/**
	 * 其中：综合利用往年贮存量（吨）
	 */
	private String zhlywnzcl;

	/**
	 * 一般工业固体废物处置量（吨）
	 */
	private String ybgygtfwczl;

	/**
	 * 其中：处置往年贮存量（吨）
	 */
	private String czwnzcl;

	/**
	 * 一般工业固体废物贮存量（吨）
	 */
	private String ybgygtfwzcl;

	/**
	 * 一般工业固体废物倾倒丢弃量（吨）
	 */
	private String ybgygtfwqddql;

	/**
	 * 危险废物上年末贮存量（吨）
	 */
	private String wxfwsnmzcl;

	/**
	 * 危险废物产生量（吨）
	 */
	private String wxfwcsl;

	/**
	 * 危险废物利用处置量（吨）
	 */
	private String wxfwlyczl;

	/**
	 * 危险废物处置量_其中：处置往年贮存量
	 */
	private String wxfwczlQzCzwnzcl;

	/**
	 * 危险废物处置量_其中：送外单位综合处置量
	 */
	private String wxfwczlQzSwdwzhczl;

	/**
	 * 危险废物本年末贮存量（吨）
	 */
	private String wxfwbnmzcl;

	/**
	 * 危险废物倾倒丢弃量（吨）
	 */
	private String wxfwqddql;

	/**
	 * 内部年利用处置能力（吨）
	 */
	private String nbnlycznl;

}
