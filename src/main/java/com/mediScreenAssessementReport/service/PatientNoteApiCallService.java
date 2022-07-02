package com.mediScreenAssessementReport.service;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.mediScreenAssessementReport.model.PatientNote;
import reactor.core.publisher.Flux;
@Service
public class PatientNoteApiCallService {
	/**
	 * this service consumes "http://localhost:8082/patHistory/list/{id}", by patientId
	 * @param patientId
	 * @return patient note taken by a doctor
	 */
	 public List<PatientNote> getDoctorsNote(int patientId) {
		 WebClient.Builder webClientBuilder = WebClient.builder();
	        Flux<PatientNote> patientNote = webClientBuilder.build()
	                .get()
	                .uri("http://localhost:8082/patHistory/list/{id}", patientId)
	                .retrieve()
	                .bodyToFlux(PatientNote.class);
	                List<PatientNote> patientNoteList = patientNote.collectList().block();
	        return patientNoteList;
}
}
