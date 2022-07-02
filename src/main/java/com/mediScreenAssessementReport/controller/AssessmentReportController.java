package com.mediScreenAssessementReport.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mediScreenAssessementReport.model.PatientAssessmentReport;
import com.mediScreenAssessementReport.service.AssessmentReportService;

@RestController
public class AssessmentReportController {
	private AssessmentReportService assessmentReport;

	@Autowired
	public AssessmentReportController(AssessmentReportService assessmentReport) {
		this.assessmentReport = assessmentReport;
	}
	/**
	 * 
	 * @param patientId
	 * @param model
	 * @param redirectAttributes
	 * @return ModelView of patientAssessment Report
	 */
	@GetMapping("/assess/{id}")
	public ModelAndView getAssesmentReportOfAPatient(@PathVariable("id") Integer patientId, Model model,RedirectAttributes redirectAttributes) {
		PatientAssessmentReport assessMentReport = assessmentReport.reportAssessmentOfPateint(patientId);
		if(assessMentReport != null) {
		model.addAttribute("assessmentReport", assessMentReport);
		}
		redirectAttributes.addFlashAttribute("message", "patient does not exist");
		redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		return new ModelAndView("assessmentReport");
	}
}
