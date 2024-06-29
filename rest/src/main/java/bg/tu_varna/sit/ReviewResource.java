package bg.tu_varna.sit;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.application.documents.ValidateDocumentRequest;
import bg.tu_varna.sit.model.application.documents.ValidateDocumentResponse;
import bg.tu_varna.sit.model.application.unverified.GetUnverifiedStudentsRequest;
import bg.tu_varna.sit.model.application.unverified.GetUnverifiedStudentsResponse;
import bg.tu_varna.sit.operation.review.GetUnverifiedStudentsOperation;
import bg.tu_varna.sit.operation.review.ValidateStudentDocumentOperation;
import io.vavr.control.Either;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/review")
@RequiredArgsConstructor
public class ReviewResource {
    private final GetUnverifiedStudentsOperation getUnverifiedStudentsOperation;
    private final ValidateStudentDocumentOperation validateStudentDocumentOperation;
    @GET
    @Path("/students")
    public Response getAllUnverifiedStudents() {
        GetUnverifiedStudentsRequest request = new GetUnverifiedStudentsRequest();
        Either<Error, GetUnverifiedStudentsResponse> process = getUnverifiedStudentsOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get()).build();
    }

    @PUT
    @Path("/document/validate/{documentId}")
    public Response validateDocument(@PathParam("documentId") Long documentId) {
        ValidateDocumentRequest request = new ValidateDocumentRequest(documentId);
        Either<Error, ValidateDocumentResponse> process = validateStudentDocumentOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get()).build();
    }
}
