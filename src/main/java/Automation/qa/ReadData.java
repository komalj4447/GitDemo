package Automation.qa;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadData {

	
	
	public void getData(String excelSheetName)
	{
		File f = new File(System.getProperty("user.dir")+"src\\test\\resources\\testdata.xlsx.xlsx");
		
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new WorkbookFactory.create(fis);
	}
	

}
