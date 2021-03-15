package com.ronfas.SGBDAPI.document;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InputStreamResource> getDocumentByUser(
            @PathVariable Long id
    ) throws FileNotFoundException {
        UUID docUuid = documentService.createDocForUser(id);
        FileInputStream createdDoc = documentService.getDocByUuid(docUuid);
        InputStreamResource inputStreamResource = new InputStreamResource(createdDoc);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setCacheControl(CacheControl.noCache().getHeaderValue());
        httpHeaders.setContentDisposition(ContentDisposition.parse("attachment; filename=" + docUuid.toString() + ".pdf"));

        return new ResponseEntity<>(inputStreamResource, httpHeaders, HttpStatus.OK);
    }

}
