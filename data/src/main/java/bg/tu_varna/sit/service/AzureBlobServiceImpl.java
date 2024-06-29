package bg.tu_varna.sit.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.InputStream;
import java.time.OffsetDateTime;
import java.util.UUID;

@ApplicationScoped
@RequiredArgsConstructor
public class AzureBlobServiceImpl implements AzureBlobService {
    private final BlobServiceClient blobServiceClient;
    @ConfigProperty(name = "quarkus.azure.storage.blob.container-name")
    String containerName;

    @Override
    public Tuple2<String, String> uploadFile(InputStream fileInputStream, String fileName, String contentType) {
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
        String uniqueFileName = UUID.randomUUID() + "-" + fileName;
        BlobClient blobClient = blobContainerClient.getBlobClient(uniqueFileName);
        blobClient.upload(fileInputStream);
        blobClient.setHttpHeaders(new BlobHttpHeaders().setContentType(contentType));
        return Tuple.of(uniqueFileName, blobClient.getBlobUrl());
    }

    @Override
    public InputStream downloadFile(String fileName) {
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);
        return blobClient.openInputStream();
    }

    @Override
    public String getSasToken(String blobName) {
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = blobContainerClient.getBlobClient(blobName);
        BlobSasPermission blobSasPermission = new BlobSasPermission().setReadPermission(true);
        OffsetDateTime expiration = OffsetDateTime.now().plusHours(1);
        BlobServiceSasSignatureValues sasSignatureValues = new BlobServiceSasSignatureValues(expiration, blobSasPermission);
        return blobClient.generateSas(sasSignatureValues);
    }
}
