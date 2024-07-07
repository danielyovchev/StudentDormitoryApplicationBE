package bg.tu_varna.sit;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.application.documents.UploadDocumentRequest;
import bg.tu_varna.sit.model.application.documents.UploadDocumentResponse;
import bg.tu_varna.sit.model.application.family.save.*;
import bg.tu_varna.sit.model.application.student.SaveStudentApplicationRequest;
import bg.tu_varna.sit.model.application.student.SaveStudentApplicationResponse;
import bg.tu_varna.sit.operation.student.SaveStudentApplicationDataOperation;
import bg.tu_varna.sit.operation.student.document.UploadStudentDocumentOperation;
import bg.tu_varna.sit.operation.student.family.SaveStudentChildDataOperation;
import bg.tu_varna.sit.operation.student.family.SaveStudentParentDataOperation;
import bg.tu_varna.sit.operation.student.family.SaveStudentSiblingDataOperation;
import bg.tu_varna.sit.operation.student.family.SaveStudentSpouseDataOperation;
import io.quarkus.logging.Log;
import io.vavr.control.Either;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/upload")
@RequiredArgsConstructor
public class StudentApplicationSaveResource {
    private final SaveStudentApplicationDataOperation saveStudentApplicationDataOperation;
    private final SaveStudentParentDataOperation saveStudentParentDataOperation;
    private final SaveStudentChildDataOperation saveStudentChildDataOperation;
    private final SaveStudentSiblingDataOperation saveStudentSiblingDataOperation;
    private final UploadStudentDocumentOperation uploadStudentDocumentOperation;
    private final SaveStudentSpouseDataOperation saveStudentSpouseDataOperation;

    @POST
    @Path("/student/data")
    @RolesAllowed("student")
    public Response saveStudentData(SaveStudentApplicationRequest request) {
        Either<Error, SaveStudentApplicationResponse> process = saveStudentApplicationDataOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get()).build();
    }

    @POST
    @Path("/student/parent")
    @RolesAllowed("student")
    public Response saveStudentFamilyData(SaveStudentParentApplicationRequest request) {
        Either<Error, SaveStudentParentApplicationResponse> process = saveStudentParentDataOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get()).build();
    }

    @POST
    @Path("/student/spouse")
    @RolesAllowed("student")
    public Response saveStudentSpouseData(SaveStudentSpouseApplicationRequest request) {
        Either<Error, SaveStudentSpouseApplicationResponse> process = saveStudentSpouseDataOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get()).build();
    }

    @POST
    @Path("/student/sibling")
    @RolesAllowed("student")
    public Response saveStudentSiblingData(SaveStudentSiblingDataRequest request) {
        Either<Error, SaveStudentSiblingDataResponse> process = saveStudentSiblingDataOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get()).build();
    }

    @POST
    @Path("/student/child")
    @RolesAllowed("student")
    public Response saveStudentChildData(SaveStudentChildDataRequest request) {
        Either<Error, SaveStudentChildDataResponse> process = saveStudentChildDataOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get()).build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/student/documents")
    @RolesAllowed("student")
    public Response saveStudentDocument(UploadDocumentRequest request) {
        Log.info("Request to upload student document for student: " + request.getStudentNumber());
        Either<Error, UploadDocumentResponse> process = uploadStudentDocumentOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok()
                .entity(process.get())
                .build();
    }
}
