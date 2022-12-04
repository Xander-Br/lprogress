package ch.xndr.lprogress.services;

import ch.xndr.lprogress.domains.Licence;
import ch.xndr.lprogress.domains.Student;
import ch.xndr.lprogress.domains.repositories.LicenceRepository;
import ch.xndr.lprogress.domains.repositories.StudentRepository;
import ch.xndr.lprogress.services.LicencePayload.Invitation;
import ch.xndr.lprogress.services.LicencePayload.VerificationRequest;
import ch.xndr.lprogress.services.LicencePayload.VerificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static java.util.Arrays.asList;

@Service
public class LicenceService {

    private static Invitation invitation;

    @Autowired
    private RestService restService;

    @Autowired
    private LicenceRepository licenceRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String getInvitation() {
        //headers accept application/json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        invitation = this.restService.restTemplate.postForObject("http://localhost:8080/connection/invitation", headers, Invitation.class);
        return invitation.getInvitationUrl();
    }

    public Licence getLicenceRest() {
        while (this.getInvitation() != null) {
            while (!this.getConnection(invitation.getConnectionId()).equals("\"responded\"")) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            VerificationRequest verificationRequest = new VerificationRequest();
            verificationRequest.setConnectionId(invitation.getConnectionId());
            verificationRequest.setCredentialDefinitionId("GxgDCXeCGmqqz259Mva9h6:3:CL:63842:DrivingLicence");
            verificationRequest.setAttributes(asList("lastName", "licenceNumber", "validityStart", "category", "firstName", "validityEnd", "birthDate"));
            VerificationResponse response = null;
            while (response == null) {
                response = this.verifyProcess(verificationRequest);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (!this.verifyProcessState(response.getProcessId())) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return this.getLicence(response.getProcessId());
        }
        return null;
    }

    public String getConnection(String connectiongId) {
        String result = this.restService.restTemplate.getForObject("http://localhost:8080/connection/" + connectiongId, String.class);
        return result;
    }

    public VerificationResponse verifyProcess(VerificationRequest verificationRequest) {
        VerificationResponse verificationResponse = this.restService.restTemplate.postForObject("http://localhost:8081/verify/process", verificationRequest, VerificationResponse.class);
        return verificationResponse;
    }

    public Boolean verifyProcessState(String processId) {
        String result = this.restService.restTemplate.getForObject("http://localhost:8081/verify/process/" + processId + "/state", String.class);
        if(result.equals("\"VERIFIED\"")) {
            return true;
        }
        return false;
    }

    public Licence getLicence(String processId) {
        Licence licence = this.restService.restTemplate.getForObject("http://localhost:8081/verify/process/" + processId + "/claims", Licence.class);
        Optional<Student> optionalStudent = this.studentRepository.findById(1L);
        licence.setStudent(optionalStudent.get());
        licenceRepository.save(licence);
        return licence;
    }
}


