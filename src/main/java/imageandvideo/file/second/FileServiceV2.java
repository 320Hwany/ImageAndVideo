package imageandvideo.file.second;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

import static imageandvideo.file.CommonConstant.STORAGE_ADDRESS;
import static imageandvideo.file.CommonConstant.STORAGE_ADDRESS_PATH;

@Service
public class FileServiceV2 {

    public Resource loadAsResource(final String filename) {

        try {
            Path file = STORAGE_ADDRESS_PATH.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new IllegalArgumentException();
            }

        } catch (MalformedURLException e) {
            throw new IllegalArgumentException();
        }
    }


    public void saveToServer(final MultipartFile uploadVideo) {

        try {
            if (uploadVideo.isEmpty()) {
                throw new IllegalArgumentException();
            }

            Path destinationFile = STORAGE_ADDRESS_PATH.resolve(
                    Paths.get(Objects.requireNonNull(
                                    uploadVideo.getOriginalFilename()
                            ))
            );

            // security check
            if (!destinationFile.getParent().equals(STORAGE_ADDRESS_PATH.toAbsolutePath())) {
                throw new IllegalArgumentException();
            }

            try (InputStream inputStream = uploadVideo.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
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
