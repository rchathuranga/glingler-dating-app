package lk.ijse.glingler.api.controller;

import lk.ijse.glingler.api.service.ProfileService;
import lk.ijse.glingler.util.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/{appType}")
@CrossOrigin
public class GlinglerController {

    @GetMapping("/welcome")
    public String welcome(@PathVariable("appType") String appType, @Value("${app.message}") String appMsg) {
        return appMsg + " | " + ((appType.equalsIgnoreCase(SysConfig.APP_TYPE_ADMIN)) ? SysConfig.APP_TYPE_ADMIN : SysConfig.APP_TYPE_USER).toUpperCase();
    }

}
