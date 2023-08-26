package imageandvideo.file.first;

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
public class FileServiceV1 {

    public void fileUploadOnServer(final MultipartFile uploadFile) {
        String fullPath = STORAGE_ADDRESS + uploadFile.getOriginalFilename();
        try {
            uploadFile.transferTo(new File(fullPath));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public void filesUploadOnServer(final MultipartFile[] uploadFiles) {
        for (MultipartFile uploadFile : uploadFiles) {
            String fullPath = STORAGE_ADDRESS + uploadFile.getOriginalFilename();
            try {
                uploadFile.transferTo(new File(fullPath));
            } catch (IOException e) {
                throw new IllegalArgumentException();
            }
        }
    }

    public UrlResource getFileFromServer(final String fileName) {
        try {
            return new UrlResource("file:" + STORAGE_ADDRESS + fileName);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException();
        }
    }

    public MediaType getMediaType(final String fileName) {
        try {
            return MediaType.parseMediaType(
                    Files.probeContentType(Paths.get("file:" + STORAGE_ADDRESS + fileName)));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
