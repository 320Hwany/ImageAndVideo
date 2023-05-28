package imageandvideo.image;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static imageandvideo.image.CommonConstant.STORAGE_ADDRESS;

@RequiredArgsConstructor
@RestController
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/api/image")
    public void imageUpload(@RequestPart MultipartFile uploadImg) {
        imageService.imageUploadOnServer(uploadImg, STORAGE_ADDRESS);
    }

    @PostMapping("/api/images")
    public void imagesUpload(@RequestPart MultipartFile[] uploadImgs) {
        imageService.imagesUploadOnServer(uploadImgs, STORAGE_ADDRESS);
    }
}
