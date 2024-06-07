package com.imranmabar.report;

import org.springframework.transaction.annotation.Transactional;

import com.imranmabar.book.BookEntity;
import com.imranmabar.book.BookService;
import com.imranmabar.util.CommonFunction;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ReportGenerationService {
	
	 @Autowired
	 JasperService jasperService;
	 
	@Autowired
	private BookService bookService;

	
	public CusJasperReportDef prescription(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		 parameterMap.put("IMRAN","HOSSAIN");
		 String reportFormate = "PDF";	 
		 List<BookEntity> authorList = new ArrayList<BookEntity>();
		 authorList =  bookService.getAllBooks();
		 
		CusJasperReportDef report = new CusJasperReportDef();
		report.setOutputFilename("prescription");
		report.setReportName("prescription");
		//report.setReportDir(CommonFunction.getReportPath(request,"/src/main/resources/reportFile/"));
		report.setReportDir("/home/bs960/imranMadbar/myPROJECT/bdu-practical-prep/spring-jasper-reports/src/main/java/com/imranmabar/report/");

		report.setReportFormat(CommonFunction.printFormat(reportFormate));
		report.setParameters(parameterMap);
		report.setReportData(authorList);
		ByteArrayOutputStream baos = null;
		try {
			baos = jasperService.generateReport(report);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		report.setContent(baos.toByteArray()); 
		return report;
		
	}


}
