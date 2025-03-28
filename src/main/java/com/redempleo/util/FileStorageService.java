package com.redempleo.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Service for handling file storage operations.
 */
@Service
public class FileStorageService {

    @Value("${file.upload.directory}")
    private String uploadDir;

    private Path rootLocation;

    @PostConstruct
    public void init() {
        rootLocation = Paths.get(uploadDir);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo inicializar el directorio de almacenamiento", e);
        }
    }

    /**
     * Store a file in the file system.
     *
     * @param file The file to store
     * @param fileName The file name
     * @return The path to the stored file
     * @throws RuntimeException if there is an error storing the file
     */
    public String storeFile(MultipartFile file, String fileName) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("No se puede almacenar un archivo vacío");
            }

            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(fileName))
                    .normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new RuntimeException(
                        "No se puede almacenar el archivo fuera del directorio actual.");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Error al almacenar el archivo " + fileName, e);
        }
    }

    /**
     * Get the file path for a given file name.
     *
     * @param fileName The file name
     * @return The path to the file
     */
    public Path getFilePath(String fileName) {
        return rootLocation.resolve(fileName);
    }

    /**
     * Delete a file.
     *
     * @param fileName The file name
     * @throws RuntimeException if there is an error deleting the file
     */
    public void deleteFile(String fileName) {
        try {
            Path file = rootLocation.resolve(fileName);
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Error al eliminar el archivo " + fileName, e);
        }
    }
}