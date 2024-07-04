package bg.tu_varna.sit.service;

import io.vavr.Tuple2;

import java.io.InputStream;

public interface AzureBlobService {
    Tuple2<String, String> uploadFile(InputStream fileInputStream, String fileName, String contentType);

    String getSasToken(String fileName);
}
