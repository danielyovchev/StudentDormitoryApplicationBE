package bg.tu_varna.sit;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.application.family.get.*;
import bg.tu_varna.sit.model.application.student.GetStudentApplicationRequest;
import bg.tu_varna.sit.model.application.student.GetStudentApplicationResponse;
import bg.tu_varna.sit.operation.student.GetStudentApplicationDataOperation;
import bg.tu_varna.sit.operation.student.document.GetStudentDocumentOperation;
import bg.tu_varna.sit.operation.student.family.GetStudentChildDataOperation;
import bg.tu_varna.sit.operation.student.family.GetStudentParentDataOperation;
import bg.tu_varna.sit.operation.student.family.GetStudentSiblingDataOperation;
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
    private final GetStudentDocumentOperation getStudentDocumentOperation;

    @GET
    @Path("/student/{studentId}/data")
    public Response getStudentData(@PathParam("studentId") String studentId) {
        GetStudentApplicationRequest request = new GetStudentApplicationRequest(studentId);
        Either<Error, GetStudentApplicationResponse> process = getStudentApplicationDataOperation.process(request);
        return Response.ok(process).build();
    }

    @GET
    @Path("/student/{studentId}/parent")
    public Response getStudentFamilyData(String studentId) {
        GetStudentParentApplicationRequest request = new GetStudentParentApplicationRequest(studentId);
        Either<Error, GetStudentParentApplicationResponse> process = getStudentParentDataOperation.process(request);
        return Response.ok().build();
    }

    @GET
    @Path("/student/{studentId}/siblings")
    public Response getStudentSiblingsData(String studentId) {
        GetStudentSiblingDataRequest request = new GetStudentSiblingDataRequest(studentId);
        Either<Error, GetStudentSiblingDataResponse> process = getStudentSiblingDataOperation.process(request);
        return Response.ok().build();
    }

    @GET
    @Path("/student/{studentId}/childrem")
    public Response getStudentChildrenData(String studentId) {
        GetStudentChildDataRequest request = new GetStudentChildDataRequest(studentId);
        Either<Error, GetStudentChildDataResponse> process = getStudentChildDataOperation.process(request);
        return Response.ok().build();
    }

    @GET
    @Path("/student/{studentId}/documents")
    public Response getStudentDocument(String studentId) {
        return Response.ok().build();
    }
}