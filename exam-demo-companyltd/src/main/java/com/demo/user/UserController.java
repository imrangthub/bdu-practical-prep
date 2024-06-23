package com.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/home")
	public String index(Model model) {
		model.addAttribute("currentUser", userService.getUserInof());
		model.addAttribute("userList", userService.findAll());
		return "user/home";
	}

	@GetMapping("/user/status/create")
	public String userStatus(Model model) {
		UserEntity obj = new UserEntity();
		model.addAttribute("userObj", obj);
		return "user/userCreate";
	}
	
	
	
    @GetMapping("/user/test-report")
    public ResponseEntity<byte[]> generateReport() {
    	
    	 
        try {
        	
        	
//        	/home/bs960/imranMadbar/bduPracticalExam/demo-companyltd/src/main/resources/reportFile/hello_world.jrxml
        	
            // Load the JRXML file
        	   InputStream reportStream = new ClassPathResource("reportFile/hello_world.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // Set parameters
            Map<String, Object> params = new HashMap<>();
            params.put("message", "This is a Simple Message !");

            // Create an empty data source
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(null);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            // Export the report to PDF
            byte[] pdfData = JasperExportManager.exportReportToPdf(jasperPrint);

            // Return the PDF as a response
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, "application/pdf");
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=hello_world.pdf");

            return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/user/report-user-list")
    public ResponseEntity<byte[]> generateUserListReport() {
    	
    	 
        try {
        	
        	
//        	/home/bs960/imranMadbar/bduPracticalExam/demo-companyltd/src/main/resources/reportFile/hello_world.jrxml
        	
            // Load the JRXML file
        	   InputStream reportStream = new ClassPathResource("reportFile/userListReport.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // Set parameters
            Map<String, Object> params = new HashMap<>();
            params.put("message", "This is a Simple Message !");

            // Create an empty data source
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(null);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            // Export the report to PDF
            byte[] pdfData = JasperExportManager.exportReportToPdf(jasperPrint);

            // Return the PDF as a response
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, "application/pdf");
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=hello_world.pdf");

            return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
	

}
