package com.mediScreenAssessementReport.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.mediScreenAssessementReport.model.Patient;
import reactor.core.publisher.Flux;
@Service
public class PatientDemoGrapicsApiCallService {
	/**
	 * this service consumes "http://localhost:8081/patient/list" 
	 * @return a list of patient in a UI format
	 */
	 public List<Patient> getAllPatients() {
	        Flux<Patient> getPatientList= WebClient.create()
	                .get()
	                .uri("http://localhost:8081/patient/list")
	                .retrieve()
	                .bodyToFlux(Patient.class);
	        List<Patient> patientList = getPatientList.collectList().block();
	        return patientList;
}
	 /**
	  * this service consumes "http://localhost:8081/patient/info/{id}" by patientId
	  * @param patientId
	  * @return returns a patient object
	  */
	 public Patient getPatient(int patientId) {
		 WebClient.Builder webClientBuilder = WebClient.builder();
	        Patient JsonResponseFrom = webClientBuilder.build()
	                .get()
	                .uri("http://localhost:8081/patient/info/{id}",patientId)
	                .retrieve()
	                .bodyToMono(Patient.class)
	                .block();
	        return JsonResponseFrom;
	    }
}