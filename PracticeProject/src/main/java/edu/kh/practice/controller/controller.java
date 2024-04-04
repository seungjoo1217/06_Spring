package edu.kh.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.practice.model.dto.Student;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class controller {

	@PostMapping("student/select")
	public String selectStudent(HttpServletRequest req,
								@RequestParam("name") String stdName,
								@RequestParam("age") int stdAge,
								@RequestParam("addr") String stdAddress
								) {
		
		Student student = new Student();
		student.setStdName(stdName);
		student.setStdAge(stdAge);
		student.setStdAddress(stdAddress);

		req.setAttribute("stdName", student.getStdName());

		req.setAttribute("stdAge", student.getStdAge());

		req.setAttribute("stdAddress", student.getStdAddress());

		return "student/select";

		}
}
