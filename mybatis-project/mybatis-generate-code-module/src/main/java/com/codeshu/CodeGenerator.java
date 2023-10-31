package com.codeshu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * 配置看 https://baomidou.com/pages/981406
 *
 * @author CodeShu
 * @date 2023/10/31 14:35
 */
@Component
public class CodeGenerator {
	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	public void generate() {
		//创建一个代码生成器
		FastAutoGenerator.create(url, username, password)
				//全局配置(GlobalConfig)
				.globalConfig(builder -> {
					builder.author("CodeShu") // 设置作者，可以写自己名字
							//.enableSwagger() // 开启 swagger 模式，这个是接口文档生成器，如果开启的话，就还需要导入swagger依赖
							.fileOverride() // 覆盖已生成文件
							.dateType(DateType.TIME_PACK) //时间策略
							.commentDate("yyyy-MM-dd") //注释日期
							.outputDir("E:\\java\\program\\java-private-project\\mybatis-project\\mybatis-generate-code-module\\src\\main\\java"); // 指定输出目录，一般指定到java目录
				})
				//包配置(PackageConfig)
				.packageConfig(builder -> {
					builder.parent("com.codeshu") // 设置父包名
							.entity("entity") //实体类包名
							.mapper("mapper") //Mapper包名
							.service("service") //Service包名
							.serviceImpl("service.impl") //ServiceImpl包名
							.controller("controller") //Controller包名
							.moduleName("") // 设置父包模块名，这里一般不设置
							.pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E:\\java\\program\\java-private-project\\mybatis-project\\mybatis-generate-code-module\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径，这里是Mapper配置文件的路径，建议使用绝对路径
				})
				//策略配置(StrategyConfig)
				.strategyConfig(builder -> {
					builder.addInclude("mybatis_generate_code_test_table") // 设置需要生成的表名
							//.addInclude("tbl_found") // 设置需要生成的表名
							.addTablePrefix("mybatis_generate_code_"); // 设置过滤表前缀

					builder.serviceBuilder()
							.formatServiceFileName("%sService") //设置service的命名策略,没有这个配置的话，生成的service和serviceImpl类前面会有一个I，比如IUserService和IUserServiceImpl
							.formatServiceImplFileName("%sServiceImpl"); //设置serviceImpl的命名策略
					builder.controllerBuilder()
							.enableRestStyle(); // 开启生成@RestController 控制器，不配置这个默认是Controller注解，RestController是返回Json字符串的，多用于前后端分离项目。
					builder.mapperBuilder()
							.enableMapperAnnotation();//开启 @Mapper 注解，也就是在dao接口上添加一个@Mapper注解，这个注解的作用是开启注解模式，就可以在接口的抽象方法上面直接使用@Select和@Insert和@Update和@Delete注解。
					entityConfig(builder);
				})
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
				.templateEngine(new VelocityTemplateEngine())
				.execute(); //执行以上配置
	}

	/**
	 * 实体类配置
	 */
	public void entityConfig(StrategyConfig.Builder builder) {
		//开启 Lombok、雪花算法主键策略
		builder.entityBuilder().enableLombok().idType(IdType.ASSIGN_ID);
	}

	/**
	 * Mapper配置
	 */
	public void mapperConfig(StrategyConfig.Builder builder) {
		builder.mapperBuilder();
	}

	/**
	 * Service配置
	 */
	public void serviceConfig(StrategyConfig.Builder builder) {
		builder.serviceBuilder();
	}

	/**
	 * Controller配置
	 */
	public void controllerConfig(StrategyConfig.Builder builder) {
		//开启驼峰转连字符
		builder.controllerBuilder().enableHyphenStyle();
	}
}
