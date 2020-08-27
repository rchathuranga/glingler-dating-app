package lk.ijse.glingler.api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/{user}/file")
public class FileUploadController {


    @PostMapping
    public String handleFileUpload(@PathVariable("user") String user,@RequestParam("file") MultipartFile file) {

        try {

            System.out.println("transferring");
            file.transferTo(new File("E:\\glingler\\" + file.getOriginalFilename()));
            System.out.println("transferred");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Success";
    }
}
