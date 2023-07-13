package com.example.assignmentjava6.service.impl;

import com.example.assignmentjava6.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {
   private final ServletContext app ;
    @Override
    public File save(MultipartFile file, String folder) {
        File dir = new File(app.getRealPath("/assets/"+folder));
//        File dir = Paths.get(app.getRealPath("/assets/"),folder).toFile();
        if (!dir.exists()){
            dir.mkdirs();
        }
        String s = System.currentTimeMillis()+ file.getOriginalFilename();
        String name = Integer.toHexString(s.hashCode())+ s.substring(s.lastIndexOf("."));
        try {
            File saveFile = new File(dir,name);
            file.transferTo(saveFile);
            System.out.println("dir"+dir);
//            System.out.println(name);
            System.out.println(saveFile);
            return saveFile;
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
