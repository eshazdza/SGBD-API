package com.ronfas.SGBDAPI.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DocumentService {

    @Autowired
    private PdfGenerator pdfGenerator;

    public void getDocumentByUser(Long id) {
        Map<String, String> data = new HashMap<>();
        data.put("schoolName", "IEPSCF");
        try {
            pdfGenerator.createPdf("bulletin-template.html", data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
