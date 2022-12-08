package services;

import model.FileInfo;

import java.io.InputStream;
import java.io.OutputStream;

public interface FileService {
    FileInfo saveFileToStorage(InputStream file, String originalFileName, String contentType, Long size);
    void readFileFromStorage(Integer fileId, OutputStream outputStream);
    FileInfo getFileInfo(Integer fileId);
}
