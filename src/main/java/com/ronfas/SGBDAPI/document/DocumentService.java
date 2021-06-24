package com.ronfas.SGBDAPI.document;

import com.ronfas.SGBDAPI.classes.ClasseService;
import com.ronfas.SGBDAPI.inscription.Inscription;
import com.ronfas.SGBDAPI.role.RoleType;
import com.ronfas.SGBDAPI.user.User;
import com.ronfas.SGBDAPI.user.UserService;
import com.ronfas.SGBDAPI.userTest.UserTest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

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
        bulletin.setDateGen(new Date());

        for (Inscription inscription :
                user.getInscriptionList()) {
            if (inscription.getRole().getRoleType() == RoleType.STUDENT) {
                BulletinClasse classe = new BulletinClasse();
                classe.setId(inscription.getClasse().getId());
                classe.setName(inscription.getClasse().getName());
                classe.setTeacherFirstName(classeService.getTeacherForClasse(inscription.getClasse().getUuid()).getFirstname());
                classe.setTeacherLastName(classeService.getTeacherForClasse(inscription.getClasse().getUuid()).getLastname());
                HashMap<String, Long> pointsAndMissed = computePointsAndMissed(inscription.getUserTestList());
                classe.setStudentPoints((pointsAndMissed.get("points")));
                classe.setMissedTest(pointsAndMissed.get("missed"));
                classe.setComment("Exemple de commentaire");
                bulletin.addClasse(classe);
            }
        }

        data.put("bulletin", bulletin);

        try {
            return pdfGenerator.createPdf("bulletin-template.html", data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private HashMap<String, Long> computePointsAndMissed(List<UserTest> userTestList) {
        HashMap<String, Long> pointsAndMissed = new HashMap<>();

        Long total = 0L;
        Long missed = 0L;

        for (UserTest userTest : userTestList) {
            total += userTest.getPoints();
            if (!userTest.isPresent()) {
                missed++;
            }
        }

        if (userTestList.size() != 0) {
            pointsAndMissed.put("points", (total / userTestList.size()));
        }

        pointsAndMissed.put("missed", missed);
        return pointsAndMissed;
    }

    public FileInputStream getDocByUuid(UUID uuid) throws FileNotFoundException {
        String basePath = "C:\\Users\\mEH\\Documents\\IEPSCF\\SGBD\\PROJET\\SGBD-API\\tmp\\";
        String path = basePath + uuid.toString() + ".pdf";
        File file = new File(path);
        return new FileInputStream(file);
    }

}
