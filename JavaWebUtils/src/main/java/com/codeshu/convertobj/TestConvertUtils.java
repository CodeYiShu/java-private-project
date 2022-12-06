package com.codeshu.convertobj;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CodeShu
 * @date 2022/12/6 14:19
 */
@RestController
public class TestConvertUtils {
	@GetMapping("/testConvertObj")
	public TargetObj testConvertUtils(){
		SourceObj sourceObj = new SourceObj();
		sourceObj.setName("张三");
		sourceObj.setAge(12);
		TargetObj targetObj = ConvertUtils.sourceToTarget(sourceObj, TargetObj.class);
		//{"name":"张三","age":12,"targetAttribute":null}
		return targetObj;
	}

	@GetMapping("/testConvertList")
	public List<TargetObj> testConvertList(){
		SourceObj sourceObj1 = new SourceObj();
		sourceObj1.setName("张三");
		sourceObj1.setAge(12);
		SourceObj sourceObj2 = new SourceObj();
		sourceObj2.setName("李四");
		sourceObj2.setAge(13);
		List<SourceObj> sourceObjList = new ArrayList<>();
		sourceObjList.add(sourceObj1);
		sourceObjList.add(sourceObj2);
		List<TargetObj> targetObjList = ConvertUtils.sourceToTarget(sourceObjList, TargetObj.class);
		//[{"name":"张三","age":12,"targetAttribute":null},{"name":"李四","age":13,"targetAttribute":null}]
		return targetObjList;
	}
}
