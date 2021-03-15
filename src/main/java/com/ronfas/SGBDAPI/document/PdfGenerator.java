package com.ronfas.SGBDAPI.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Component
public class PdfGenerator {
    @Autowired
    private TemplateEngine templateEngine;

    public UUID createPdf(String templateName, Map map) throws Exception {
        Assert.notNull(templateName, "Template name cannot be null");

//        Load data Map into Context
        Context context = new Context();
        if (map != null) {
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator.next();
                context.setVariable(pair.getKey().toString(), pair.getValue());
            }
        }

//        Generate html from template name and context
        String processedHtml = templateEngine.process(templateName, context);
//        Create file
        FileOutputStream outputStream = null;
        String basePath = "C:\\Users\\mEH\\Documents\\IEPSCF\\SGBD\\PROJET\\SGBD-API\\tmp\\";
        UUID fileName = UUID.randomUUID();
        String path = basePath+fileName.toString()+".pdf";

        try {
            final File outPutFile = new File(path);
            outputStream = new FileOutputStream(outPutFile);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml, new ClassPathResource("/pdf-resources/").getURL().toExternalForm());
            renderer.layout();
            renderer.createPDF(outputStream, false);
            renderer.finishPDF();
            return fileName;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
