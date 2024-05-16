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
import io.vavr.control.Either;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.reactive.MultipartForm;

@Path("/upload")
@RequiredArgsConstructor
public class StudentApplicationSaveResource {
    private final SaveStudentApplicationDataOperation saveStudentApplicationDataOperation;
    private final SaveStudentParentDataOperation saveStudentParentDataOperation;
    private final SaveStudentChildDataOperation saveStudentChildDataOperation;
    private final SaveStudentSiblingDataOperation saveStudentSiblingDataOperation;
    private final UploadStudentDocumentOperation uploadStudentDocumentOperation;

    @POST
    @Path("/student/data")
    public Response saveStudentData(SaveStudentApplicationRequest request) {
        Either<Error, SaveStudentApplicationResponse> process = saveStudentApplicationDataOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get().getMessage()).build();
    }

    @POST
    @Path("/student/family")
    public Response saveStudentFamilyData(SaveStudentParentApplicationRequest request) {
        Either<Error, SaveStudentParentApplicationResponse> process = saveStudentParentDataOperation.process(request);
        return Response.ok().build();
    }

    @POST
    @Path("/student/sibling")
    public Response saveStudentSiblingData(SaveStudentSiblingDataRequest request) {
        Either<Error, SaveStudentSiblingDataResponse> process = saveStudentSiblingDataOperation.process(request);
        return Response.ok().build();
    }

    @POST
    @Path("/student/child")
    public Response saveStudentChildData(SaveStudentChildDataRequest request) {
        Either<Error, SaveStudentChildDataResponse> process = saveStudentChildDataOperation.process(request);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/student/documents")
    public Response saveStudentDocument(@MultipartForm UploadDocumentRequest request) {
        Either<Error, UploadDocumentResponse> process = uploadStudentDocumentOperation.process(request);
        return Response.ok().build();
    }
}
