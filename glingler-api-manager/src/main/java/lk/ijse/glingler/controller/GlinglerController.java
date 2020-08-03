package lk.ijse.glingler.controller;

import lk.ijse.glingler.util.SysConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/{appType}")
public class GlinglerController {

    @GetMapping("/welcome")
    public String welcome(@PathVariable("appType") String appType, @Value("${app.message}") String appMsg) {
        return appMsg + " | " + ((appType.equalsIgnoreCase(SysConfig.APP_TYPE_ADMIN)) ? SysConfig.APP_TYPE_ADMIN : SysConfig.APP_TYPE_USER).toUpperCase();
    }

}
