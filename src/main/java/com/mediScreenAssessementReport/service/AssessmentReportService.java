package com.mediScreenAssessementReport.service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mediScreenAssessementReport.model.Patient;
import com.mediScreenAssessementReport.model.PatientAssessmentReport;
import com.mediScreenAssessementReport.model.PatientNote;

@Service
public class AssessmentReportService {
	@Autowired
	PatientDemoGrapicsApiCallService patientDemoGraphicsServiceCall;
	@Autowired
	PatientNoteApiCallService patientNoteApiCallService;
	Logger logger = LoggerFactory.getLogger(AssessmentReportService.class);
/**
 * this is a list of triggers given by client 
 * @return triggers
 */
	public List<String> gettingAListOfTriggers() {
		List<String> triggers = new ArrayList<>();
		triggers.add("Hemoglobin A1C");
		triggers.add("Microalbumin");
		triggers.add("Body Height");
		triggers.add("Body Weight");
		triggers.add("Smoker");
		triggers.add("Abnormal");
		triggers.add("Cholesterol");
		triggers.add("Dizziness");
		triggers.add("Relapse");
		triggers.add("Reaction");
		triggers.add("Antibodies");
		return triggers;
	}
	/**
	 * counts the number of trigger in a doctors note 
	 * @param patientId
	 * @return Number of triggers a patient has
	 */
	public int triggerCount(int patientId) {
		int count = 0;
		for (String trigger : gettingAListOfTriggers()) {
			for (PatientNote patientNote : patientNoteApiCallService.getDoctorsNote(patientId)) {
				if (patientNote.getNote().contains(trigger)) {
					count++;
				}
			}

		}
		return count;
	}
	/**
	 * calculate patient age 
	 * @param patientId
	 * @return age
	 */
	public int calcualtePatientAge(int patientId) {
		Patient patient = patientDemoGraphicsServiceCall.getPatient(patientId);
		LocalDate dateOfBirth = LocalDate.parse(patient.getDateOfBirth());
		LocalDate currentDate = LocalDate.now();
		int age = Period.between(dateOfBirth,currentDate).getYears();
		return age;
	}
	/**
	 * 
	 * @param patientId
	 * @return sex
	 */
	public String getSexOfPatient(int patientId) {
		Patient patient = patientDemoGraphicsServiceCall.getPatient(patientId);
		return patient.getSex();
	}
	/**
	 * 
	 * @param patientId
	 * @return PatientAssessmentReport
	 */
	public PatientAssessmentReport reportAssessmentOfPateint(int patientId) {
		PatientAssessmentReport patientAssessmentReport = new PatientAssessmentReport();
	int age = calcualtePatientAge(patientId);
	int trigger = triggerCount(patientId);
	String sex = getSexOfPatient(patientId);
	String riskLevel = null;
	logger.info("count =" + trigger);
	logger.info("Pateint age" +age);
	if(trigger == 0) {
		logger.info("count =" + trigger);
		riskLevel = "None";
	}
	else if(trigger <= 3 && age > 30){
		riskLevel = "Borderline";
	}else if(trigger == 3 && age < 30 && sex == "M" ) {
		riskLevel = "In danger";
	}else if(trigger == 4 && age < 30 && sex == "F"){
		riskLevel = "In danger";
	}else if(trigger == 6 && age > 30) {
		riskLevel = "In danger";
	}else if(trigger == 5 && age < 30 && sex == "M"){
		riskLevel = "Early Onset";
	}else if(trigger == 7 && age < 30 && sex == "F") {
		riskLevel = "Early Onset";
	}else if(trigger >= 8 && age > 30) {
		riskLevel = "Early Onset";
	} else {
		riskLevel = " Risk Level could not be Determined";
	}
	patientAssessmentReport.setAge(age);
	patientAssessmentReport.setPatientId(patientId);
	patientAssessmentReport.setThreatLevel(riskLevel);
	patientAssessmentReport.setSex(sex);
		return patientAssessmentReport;
	}
	
}
