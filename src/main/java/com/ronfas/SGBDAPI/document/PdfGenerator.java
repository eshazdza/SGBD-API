package com.ronfas.SGBDAPI.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

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

    public void createPdf(String templateName, Map map) throws Exception {
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
        System.out.println(processedHtml);
//        Create file
        FileOutputStream outputStream = null;
        String fileName = UUID.randomUUID().toString();

        try {
            final File outPutFile = File.createTempFile(fileName, ".pdf");
            System.out.println(outPutFile.getAbsolutePath());
            outputStream = new FileOutputStream(outPutFile);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(outputStream, false);
            renderer.finishPDF();
            System.out.println("PDF created ");
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
