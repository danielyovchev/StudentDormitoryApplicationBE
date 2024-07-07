package bg.tu_varna.sit;

import bg.tu_varna.sit.model.application.documents.UploadDocumentRequest;
import bg.tu_varna.sit.model.application.family.save.*;
import bg.tu_varna.sit.model.application.student.SaveStudentApplicationRequest;
import bg.tu_varna.sit.model.enums.*;
import bg.tu_varna.sit.operation.student.SaveStudentApplicationDataOperation;
import bg.tu_varna.sit.operation.student.document.UploadStudentDocumentOperation;
import bg.tu_varna.sit.operation.student.family.SaveStudentChildDataOperation;
import bg.tu_varna.sit.operation.student.family.SaveStudentParentDataOperation;
import bg.tu_varna.sit.operation.student.family.SaveStudentSiblingDataOperation;
import bg.tu_varna.sit.operation.student.family.SaveStudentSpouseDataOperation;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import jakarta.inject.Inject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class StudentApplicationSaveResourceTest {

    @Inject
    SaveStudentApplicationDataOperation saveStudentApplicationDataOperation;

    @Inject
    SaveStudentParentDataOperation saveStudentParentDataOperation;

    @Inject
    SaveStudentChildDataOperation saveStudentChildDataOperation;

    @Inject
    SaveStudentSiblingDataOperation saveStudentSiblingDataOperation;

    @Inject
    UploadStudentDocumentOperation uploadStudentDocumentOperation;

    @Inject
    SaveStudentSpouseDataOperation saveStudentSpouseDataOperation;

    @Test
    @TestSecurity(user = "student", roles = {"student"})
    @TestTransaction
    public void testSaveStudentData() {
        SaveStudentApplicationRequest request = new SaveStudentApplicationRequest();
        request.setName("John Doe");
        request.setEducationForm(EducationForm.REGULAR);
        request.setFaculty(Faculty.FITA);
        request.setSpecialty(Specialty.SIT);
        request.setCity("Varna");
        request.setMunicipality("Varna Municipality");
        request.setAddress("123 Main St");
        request.setPersonalNumber("1234567890");
        request.setStudentNumber("202020");
        request.setPhoneNumber("123-456-7890");
        request.setGrade(5.5);
        request.setDormitoryNumber(18);
        request.setRoomNumber(201);
        request.setSex("Male");

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/upload/student/data")
                .then()
                .statusCode(200)
                .body("message", is("Student data updated"));
    }

    @Test
    @TestSecurity(user = "student", roles = {"student"})
    @TestTransaction
    public void testSaveStudentFamilyData() {
        SaveStudentParentApplicationRequest request = new SaveStudentParentApplicationRequest();
        request.setStudentNumber("202020");
        request.setCity("s");
        request.setName("ss");
        request.setAddress("sda");
        request.setParentType(ParentType.FATHER);
        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/upload/student/parent")
                .then()
                .statusCode(200)
                .body("message", is("Data for parent saved"));
    }

    @Test
    @TestSecurity(user = "student", roles = {"student"})
    @TestTransaction
    public void testSaveStudentSpouseData() {
        SaveStudentSpouseApplicationRequest request = new SaveStudentSpouseApplicationRequest();
        request.setStudentNumber("202020");

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/upload/student/spouse")
                .then()
                .statusCode(200)
                .body("message", is("Data for spouse saved"));
    }

    @Test
    @TestSecurity(user = "student", roles = {"student"})
    @TestTransaction
    public void testSaveStudentSiblingData() {
        SaveStudentSiblingDataRequest request = new SaveStudentSiblingDataRequest();
        request.setStudentNumber("202020");
        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/upload/student/sibling")
                .then()
                .statusCode(200)
                .body("message", is("Sibling data saved"));
    }

    @Test
    @TestSecurity(user = "student", roles = {"student"})
    @TestTransaction
    public void testSaveStudentChildData() {
        SaveStudentChildDataRequest request = new SaveStudentChildDataRequest();
        request.setStudentNumber("202020");

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/upload/student/child")
                .then()
                .statusCode(200)
                .body("message", is("Child data saved"));
    }
}
