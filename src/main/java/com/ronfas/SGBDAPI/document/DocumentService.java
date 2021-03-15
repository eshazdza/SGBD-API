package com.ronfas.SGBDAPI.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class DocumentService {

    @Autowired
    private PdfGenerator pdfGenerator;

    public UUID createDocForUser(Long id) {
        Map<String, String> data = new HashMap<>();
        data.put("schoolName", "IEPSCF");
        try {
            return pdfGenerator.createPdf("bulletin-template.html", data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public FileInputStream getDocByUuid(UUID uuid) throws FileNotFoundException {
        String basePath = "C:\\Users\\mEH\\Documents\\IEPSCF\\SGBD\\PROJET\\SGBD-API\\tmp\\";
        String path = basePath + uuid.toString() + ".pdf";
        File file = new File(path);
        return new FileInputStream(file);
    }

}
