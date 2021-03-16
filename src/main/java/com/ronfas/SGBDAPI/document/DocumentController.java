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
    public ResponseEntity<?> getDocumentByUser(
            @PathVariable Long id
    ) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setCacheControl(CacheControl.noCache().getHeaderValue());
        httpHeaders.setContentDisposition(ContentDisposition.parse("attachment; filename=bulletin.pdf"));

        return new ResponseEntity<>(documentService.createDocForUser(id), httpHeaders, HttpStatus.OK);
    }

}
