package bg.tu_varna.sit;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.application.documents.GetDocumentRequest;
import bg.tu_varna.sit.model.application.documents.GetDocumentResponse;
import bg.tu_varna.sit.model.application.family.get.*;
import bg.tu_varna.sit.model.application.student.GetStudentApplicationRequest;
import bg.tu_varna.sit.model.application.student.GetStudentApplicationResponse;
import bg.tu_varna.sit.operation.student.GetStudentApplicationDataOperation;
import bg.tu_varna.sit.operation.student.document.GetStudentDocumentOperation;
import bg.tu_varna.sit.operation.student.family.GetStudentChildDataOperation;
import bg.tu_varna.sit.operation.student.family.GetStudentParentDataOperation;
import bg.tu_varna.sit.operation.student.family.GetStudentSiblingDataOperation;
import bg.tu_varna.sit.operation.student.family.GetStudentSpouseDataOperation;
import io.vavr.control.Either;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/get")
@RequiredArgsConstructor
public class StudentApplicationGetResource {
    private final GetStudentApplicationDataOperation getStudentApplicationDataOperation;
    private final GetStudentParentDataOperation getStudentParentDataOperation;
    private final GetStudentChildDataOperation getStudentChildDataOperation;
    private final GetStudentSiblingDataOperation getStudentSiblingDataOperation;
    private final GetStudentSpouseDataOperation getStudentSpouseDataOperation;
    private final GetStudentDocumentOperation getStudentDocumentOperation;

    @GET
    @Path("/student/{studentId}/data")
    public Response getStudentData(@PathParam("studentId") String studentId) {
        GetStudentApplicationRequest request = new GetStudentApplicationRequest(studentId);
        Either<Error, GetStudentApplicationResponse> process = getStudentApplicationDataOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get().getStudentDTO()).build();
    }

    @GET
    @Path("/student/{studentId}/parent")
    public Response getStudentFamilyData(String studentId) {
        GetStudentParentApplicationRequest request = new GetStudentParentApplicationRequest(studentId);
        Either<Error, GetStudentParentApplicationResponse> process = getStudentParentDataOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get().getParents()).build();
    }

    @GET
    @Path("/student/{studentId}/spouse")
    public Response getStudentSpouseData(String studentId) {
        GetStudentSpouseApplicationRequest request = new GetStudentSpouseApplicationRequest(studentId);
        Either<Error, GetStudentSpouseApplicationResponse> process = getStudentSpouseDataOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get().getSpouse()).build();
    }

    @GET
    @Path("/student/{studentId}/siblings")
    public Response getStudentSiblingsData(String studentId) {
        GetStudentSiblingDataRequest request = new GetStudentSiblingDataRequest(studentId);
        Either<Error, GetStudentSiblingDataResponse> process = getStudentSiblingDataOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get().getSiblings()).build();
    }

    @GET
    @Path("/student/{studentId}/children")
    public Response getStudentChildrenData(String studentId) {
        GetStudentChildDataRequest request = new GetStudentChildDataRequest(studentId);
        Either<Error, GetStudentChildDataResponse> process = getStudentChildDataOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get().getChildren()).build();
    }

    @GET
    @Path("/student/{studentId}/documents")
    public Response getStudentDocument(String studentId) {
        GetDocumentRequest request = new GetDocumentRequest(studentId);
        Either<Error, GetDocumentResponse> process = getStudentDocumentOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get()).build();
    }
}
