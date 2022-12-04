package ch.xndr.lprogress.controllers;

import ch.xndr.lprogress.domains.Licence;
import ch.xndr.lprogress.services.LicencePayload.Invitation;
import ch.xndr.lprogress.services.LicencePayload.VerificationRequest;
import ch.xndr.lprogress.services.LicenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/licence", produces = MediaType.APPLICATION_JSON_VALUE)
public class LicenceController {

    @Autowired
    private LicenceService licenceService;

    @GetMapping(value = "/invitation")
    public String getInvitation() {
        return this.licenceService.getInvitation();
    }

    @GetMapping(value = "/rest")
    public Licence getLicenceRest() {
        return this.licenceService.getLicenceRest();
    }

}
