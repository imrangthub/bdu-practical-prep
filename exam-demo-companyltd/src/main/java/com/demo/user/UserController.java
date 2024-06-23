package com.demo.user;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=user-list.pdf");

			return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
