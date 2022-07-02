package com.mediScreenAssessementReport.controller;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mediScreenAssessementReport.model.PatientAssessmentReport;
import com.mediScreenAssessementReport.service.AssessmentReportService;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AssessmentReportControllerTest {

	 @Mock
	    AssessmentReportService reportService;

	    @InjectMocks
	   AssessmentReportController assessmentReportController;

	    @Mock
	    Model model;
	    @Mock
	    RedirectAttributes redirectAttributes;
	    
	@Test
	public void testGetAssesmentReportOfAPatient() {
		 PatientAssessmentReport patientReport = new PatientAssessmentReport();
	        patientReport.setPatientId(21);
	        patientReport.setThreatLevel("Borderline");
	        patientReport.setAge(50);
	        Mockito.when(reportService.reportAssessmentOfPateint(21)).thenReturn(patientReport);
	        ModelAndView modelAndView = assessmentReportController.getAssesmentReportOfAPatient(21,model,redirectAttributes);
	        Assert.assertEquals("assessmentReport", modelAndView.getViewName());
	}

}
