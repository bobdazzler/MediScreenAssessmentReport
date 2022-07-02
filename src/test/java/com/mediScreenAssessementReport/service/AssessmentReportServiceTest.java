package com.mediScreenAssessementReport.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mediScreenAssessementReport.model.Patient;
import com.mediScreenAssessementReport.model.PatientNote;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AssessmentReportServiceTest {
	 @InjectMocks
	 AssessmentReportService reportService;

	    @Mock
	    PatientDemoGrapicsApiCallService patientInfoService;

	    @Mock
	    PatientNoteApiCallService patientNoteService;
	@Test
	public void testGettingAListOfTriggers() {
		List<String> methodUnderTest = reportService.gettingAListOfTriggers();
		assertNotNull(methodUnderTest);
	}

	@Test
	public void testTriggerCount() {
		 PatientNote patient = new PatientNote();
	        patient.setPatientId(21);
	        patient.setNote("Hemoglobin A1C");
	        PatientNote patientNote = new PatientNote();
	        patientNote.setPatientId(21);
	        patientNote.setNote("Microalbumin");
	        List<PatientNote> patientNoteDtoList = new ArrayList<>();
	        patientNoteDtoList.add(patient);
	        patientNoteDtoList.add(patientNote);
	        Mockito.when(patientNoteService.getDoctorsNote(21)).thenReturn(patientNoteDtoList);
	        Assert.assertEquals(2, reportService.triggerCount(21));
	}


	@Test
	public void testReportAssessmentOfPateint() {
		 Patient patient = new Patient();
	        patient.setId(21);
	        patient.setPatientName("Test1");
	        patient.setFamilyName("TestFamily1");
	        patient.setSex("M");
	        patient.setDateOfBirth("1957-08-08");
	        Mockito.when(patientInfoService.getPatient(21)).thenReturn(patient);
	        Assert.assertEquals(64, reportService.reportAssessmentOfPateint(21).getAge());
	}

}
