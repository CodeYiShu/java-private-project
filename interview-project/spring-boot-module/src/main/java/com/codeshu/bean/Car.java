package com.codeshu.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author CodeShu
 * @date 2023/7/21 14:58
 */
@Data
//@Component
@ConfigurationProperties(prefix = "mycar")
public class Car {
	private String brand; //品牌
	private Integer price; //价格
}
