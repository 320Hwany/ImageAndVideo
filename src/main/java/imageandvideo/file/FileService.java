package imageandvideo.file;

import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static imageandvideo.file.CommonConstant.STORAGE_ADDRESS;

@Service
public class FileService {

    public void fileUploadOnServer(MultipartFile uploadFile) {
        String fullPath = STORAGE_ADDRESS + uploadFile.getOriginalFilename();
        try {
            uploadFile.transferTo(new File(fullPath));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public void filesUploadOnServer(MultipartFile[] uploadFiles) {
        for (MultipartFile uploadFile : uploadFiles) {
            String fullPath = STORAGE_ADDRESS + uploadFile.getOriginalFilename();
            try {
                uploadFile.transferTo(new File(fullPath));
            } catch (IOException e) {
                throw new IllegalArgumentException();
            }
        }
    }

    public UrlResource getFileFromServer(String fileName) {
        try {
            return new UrlResource("file:" + STORAGE_ADDRESS + fileName);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException();
        }
    }

    public MediaType getMediaType(String fileName) {
        try {
            return MediaType.parseMediaType(
                    Files.probeContentType(Paths.get("file:" + STORAGE_ADDRESS + fileName)));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
