package services.impl;

import dao.FileRepository;
import exception.FileSizeException;
import exception.NotFoundException;
import model.FileInfo;
import services.FileService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class FileServiceImpl implements FileService {

    String path = "/Users/polinom/Documents/uploads/";

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public FileInfo saveFileToStorage(InputStream inputStream, String originalFileName, String contentType, Long size) {
        if(size > 10_000_000) {
            throw new FileSizeException("File is too large");
        }
        FileInfo fileInfo = new FileInfo(
                null,
                originalFileName,
                UUID.randomUUID().toString(),
                size,
                contentType
        );
        try {
            Files.copy(inputStream, Paths.get(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]));
            fileInfo = fileRepository.save(fileInfo);
            return fileInfo;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void readFileFromStorage(Integer fileId, OutputStream outputStream) {
        FileInfo fileInfo = fileRepository.getById(fileId)
                .orElseThrow(() -> new NotFoundException("File not found"));

        File file = new File(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]);
        try {
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public FileInfo getFileInfo(Integer fileId) {
        return fileRepository.getById(fileId).orElseThrow(() -> new NotFoundException("File not found"));
    }
}
