package bg.sofia.uni.fmi.piss.project.tm.controllers;

import bg.sofia.uni.fmi.piss.project.tm.services.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload/{username}")
    public ResponseEntity uploadFile(@RequestParam("profile_pic") MultipartFile file,
        @PathVariable String username) {
        return fileService.uploadFile(file, username);
    }
}