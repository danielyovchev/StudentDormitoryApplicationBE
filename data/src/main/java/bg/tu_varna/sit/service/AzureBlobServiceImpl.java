package bg.tu_varna.sit.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.InputStream;
import java.util.UUID;

@ApplicationScoped
@RequiredArgsConstructor
public class AzureBlobServiceImpl implements AzureBlobService {
    private final BlobServiceClient blobServiceClient;
    @ConfigProperty(name = "quarkus.azure.storage.blob.container-name")
    String containerName;
    @Override
    public String uploadFile(InputStream fileInputStream, String fileName, String contentType) {
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
        String uniqueFileName = UUID.randomUUID() + "-" + fileName;
        BlobClient blobClient = blobContainerClient.getBlobClient(uniqueFileName);
        blobClient.upload(fileInputStream);
        blobClient.setHttpHeaders(new BlobHttpHeaders().setContentType(contentType));
        return blobClient.getBlobUrl();
    }

    @Override
    public InputStream downloadFile(String fileName) {
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);
        return blobClient.openInputStream();
    }
}
