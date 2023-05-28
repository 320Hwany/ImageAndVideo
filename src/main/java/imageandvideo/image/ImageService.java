package imageandvideo.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {

    public void imageUploadOnServer(MultipartFile uploadImg, String imgDir) {
        String fullPath = imgDir + uploadImg.getOriginalFilename();
        try {
            uploadImg.transferTo(new File(fullPath));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public void imagesUploadOnServer(MultipartFile[] uploadImgs, String imgDir) {
        for (MultipartFile uploadImg : uploadImgs) {
            String fullPath = imgDir + uploadImg.getOriginalFilename();
            try {
                uploadImg.transferTo(new File(fullPath));
            } catch (IOException e) {
                throw new IllegalArgumentException();
            }
        }
    }
}
