package com.ronfas.SGBDAPI.document;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/{id}")
    public void getDocumentByUser(
            @PathVariable Long id
    ) {
        documentService.getDocumentByUser(id);
    }

}
