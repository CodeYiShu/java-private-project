package com.codeshu.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author CodeShu
 * @date 2023/7/21 14:23
 */
public class MyImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		String[] imports = {"com.codeshu.bean.User","com.codeshu.bean.Pet"};
		return imports;
	}
}
