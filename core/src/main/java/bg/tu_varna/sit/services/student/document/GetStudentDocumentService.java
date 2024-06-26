package bg.tu_varna.sit.services.student.document;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Document;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.mapper.DocumentMapper;
import bg.tu_varna.sit.model.application.documents.GetDocumentRequest;
import bg.tu_varna.sit.model.application.documents.GetDocumentResponse;
import bg.tu_varna.sit.model.dto.DocumentDTO;
import bg.tu_varna.sit.operation.student.document.GetStudentDocumentOperation;
import bg.tu_varna.sit.repository.DocumentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class GetStudentDocumentService implements GetStudentDocumentOperation {
    private final DocumentMapper documentMapper;
    private final DocumentRepository documentRepository;
    @Override
    @Transactional
    public Either<Error, GetDocumentResponse> process(GetDocumentRequest input) {
        return Try.of(() -> {
                    List<Document> documents = documentRepository.getUnverifiedDocuments(input.getStudentId());
                    List<DocumentDTO> documentDTOS = documents.stream()
                            .map(documentMapper::toDocumentDTO)
                            .toList();
                    return GetDocumentResponse.builder()
                            .documents(documentDTOS)
                            .build();
                }).toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
