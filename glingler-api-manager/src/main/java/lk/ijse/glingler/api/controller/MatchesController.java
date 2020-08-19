package lk.ijse.glingler.api.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/{appType}/matches")
@CrossOrigin
public class MatchesController {

    @GetMapping(value = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public void matchProfiles(@PathVariable(value = "appType") String appType) {
        System.out.println();
        System.out.println("Test");
        System.out.println();
    }

}
