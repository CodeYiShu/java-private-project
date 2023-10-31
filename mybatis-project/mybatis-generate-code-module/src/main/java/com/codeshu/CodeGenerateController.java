package com.codeshu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CodeShu
 * @date 2023/10/31 14:44
 */
@RestController()
public class CodeGenerateController {
	@Autowired
	private CodeGenerator codeGenerator;

	@GetMapping("/generateCode")
	public void generateCode() {
		codeGenerator.generate();
	}
}
