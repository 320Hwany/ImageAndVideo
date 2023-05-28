package imageandvideo.image;

import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static imageandvideo.image.CommonConstant.STORAGE_ADDRESS;

@Service
public class ImageService {

    public void imageUploadOnServer(MultipartFile uploadImg) {
        String fullPath = STORAGE_ADDRESS + uploadImg.getOriginalFilename();
        try {
            uploadImg.transferTo(new File(fullPath));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public void imagesUploadOnServer(MultipartFile[] uploadImgs) {
        for (MultipartFile uploadImg : uploadImgs) {
            String fullPath = STORAGE_ADDRESS + uploadImg.getOriginalFilename();
            try {
                uploadImg.transferTo(new File(fullPath));
            } catch (IOException e) {
                throw new IllegalArgumentException();
            }
        }
    }

    public UrlResource getImageFromServer(String imgName) {
        try {
            return new UrlResource("file:" + STORAGE_ADDRESS + imgName);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException();
        }
    }

    public MediaType getMediaType(String imgName) {
        try {
            return MediaType.parseMediaType(
                    Files.probeContentType(Paths.get("file:" + STORAGE_ADDRESS + imgName)));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
