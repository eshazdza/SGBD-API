package com.ronfas.SGBDAPI.document;

import com.ronfas.SGBDAPI.classes.ClasseService;
import com.ronfas.SGBDAPI.inscription.Inscription;
import com.ronfas.SGBDAPI.user.User;
import com.ronfas.SGBDAPI.user.UserService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class DocumentService {

    private final PdfGenerator pdfGenerator;
    private final UserService userService;
    private final ClasseService classeService;

    public DocumentService(PdfGenerator pdfGenerator, UserService userService, ClasseService classeService) {
        this.pdfGenerator = pdfGenerator;
        this.userService = userService;
        this.classeService = classeService;
    }

    public byte[] createDocForUser(Long id) {
        Map<String, Bulletin> data = new HashMap<>();

        User user = userService.getUserById(id);

        Bulletin bulletin = new Bulletin();
        bulletin.setStudentFirstName(user.getFirstname());
        bulletin.setStudentLastName(user.getLastname());

        BulletinClasse classe = new BulletinClasse();

        for (Inscription inscription :
                user.getInscriptionList()) {
            classe.setId(inscription.getClasse().getId());
            classe.setName(inscription.getClasse().getName());
            classe.setTeacherFirstName(classeService.getTeacherForClasse(inscription.getClasse().getUuid()).getFirstname());
            bulletin.addClasse(classe);
        }


        data.put("bulletin", bulletin);

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
