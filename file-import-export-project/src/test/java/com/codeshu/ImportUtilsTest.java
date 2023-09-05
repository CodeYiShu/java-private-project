package com.codeshu;

import com.codeshu.utils.ImportUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 导入的工具类测试
 *
 * @author CodeShu
 * @date 2023/9/5 14:54
 */
@Slf4j
public class ImportUtilsTest {
	String PATH = "E:\\java\\program\\java-private-project\\file-import-export-project";

	@Test
	public void testUtils() throws IOException {
		//获取文件流
		FileInputStream fis = new FileInputStream(PATH + "/user02.xlsx");
		//获取一个工作簿
		Workbook workbook = new XSSFWorkbook(fis);

		List<String[]> dataList = ImportUtils.readExcelData(workbook, 0, 1);

		assert dataList != null;
		for (String[] data : dataList) {
			System.out.println(Arrays.toString(data));
		}
	}
}
