package bg.tu_varna.sit.service;

import java.io.InputStream;

public interface AzureBlobService {
    String uploadFile(InputStream fileInputStream, String fileName, String contentType);
    InputStream downloadFile(String fileName);
}
