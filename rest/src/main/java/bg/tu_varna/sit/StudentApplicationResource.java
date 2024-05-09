package bg.tu_varna.sit;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.application.documents.UploadDocumentRequest;
import bg.tu_varna.sit.model.application.documents.UploadDocumentResponse;
import bg.tu_varna.sit.model.application.family.GetStudentParentApplicationRequest;
import bg.tu_varna.sit.model.application.family.GetStudentParentApplicationResponse;
import bg.tu_varna.sit.model.application.family.SaveStudentParentApplicationRequest;
import bg.tu_varna.sit.model.application.family.SaveStudentParentApplicationResponse;
import bg.tu_varna.sit.model.application.student.GetStudentApplicationRequest;
import bg.tu_varna.sit.model.application.student.GetStudentApplicationResponse;
import bg.tu_varna.sit.model.application.student.SaveStudentApplicationRequest;
import bg.tu_varna.sit.model.application.student.SaveStudentApplicationResponse;
import bg.tu_varna.sit.operation.student.GetStudentApplicationDataOperation;
import bg.tu_varna.sit.operation.student.GetStudentDocumentOperation;
import bg.tu_varna.sit.operation.student.SaveStudentApplicationDataOperation;
import bg.tu_varna.sit.operation.student.UploadStudentDocumentOperation;
import bg.tu_varna.sit.operation.student.family.GetStudentParentDataOperation;
import bg.tu_varna.sit.operation.student.family.SaveStudentParentDataOperation;
import io.vavr.control.Either;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/upload")
@RequiredArgsConstructor
public class StudentApplicationResource {
    private final SaveStudentApplicationDataOperation saveStudentApplicationDataOperation;
    private final GetStudentApplicationDataOperation getStudentApplicationDataOperation;
    private final SaveStudentParentDataOperation saveStudentParentDataOperation;
    private final UploadStudentDocumentOperation uploadStudentDocumentOperation;
    private final GetStudentParentDataOperation getStudentParentDataOperation;
    private final GetStudentDocumentOperation getStudentDocumentOperation;

    @POST
    @Path("/student/data")
    public Response saveStudentData(SaveStudentApplicationRequest request) {
        Either<Error, SaveStudentApplicationResponse> process = saveStudentApplicationDataOperation.process(request);
        return Response.ok(process).build();
    }

    @POST
    @Path("/student/family")
    public Response saveStudentFamilyData(SaveStudentParentApplicationRequest request) {
        Either<Error, SaveStudentParentApplicationResponse> process = saveStudentParentDataOperation.process(request);
        return Response.ok().build();
    }

    @POST
    @Path("/student/documents")
    public Response saveStudentDocument(UploadDocumentRequest request) {
        Either<Error, UploadDocumentResponse> process = uploadStudentDocumentOperation.process(request);
        return Response.ok().build();
    }

    @GET
    @Path("/student/{studentId}/data")
    public Response getStudentData(@PathParam("studentId") String studentId) {
        GetStudentApplicationRequest request = new GetStudentApplicationRequest(studentId);
        Either<Error, GetStudentApplicationResponse> process = getStudentApplicationDataOperation.process(request);
        return Response.ok(process).build();
    }

    @GET
    @Path("/student/{studentId}/family")
    public Response getStudentFamilyData(String studentId) {
        GetStudentParentApplicationRequest request = new GetStudentParentApplicationRequest(studentId);
        Either<Error, GetStudentParentApplicationResponse> process = getStudentParentDataOperation.process(request);
        return Response.ok().build();
    }

    @GET
    @Path("/student/{studentId}/documents")
    public Response getStudentDocument(String studentId) {
        return Response.ok().build();
    }
}
