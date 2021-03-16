package com.ronfas.SGBDAPI.document;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.Map;

@Component
public class PdfGenerator {
    @Autowired
    private TemplateEngine templateEngine;

    public byte[] createPdf(String templateName, Map map) throws Exception {
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
        ByteArrayOutputStream target = new ByteArrayOutputStream();

        String uri =  new ClassPathResource("/pdf-resources/").getURL().toExternalForm();

        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri(uri);

        HtmlConverter.convertToPdf(processedHtml, target, converterProperties);
        return target.toByteArray();

    }

}
