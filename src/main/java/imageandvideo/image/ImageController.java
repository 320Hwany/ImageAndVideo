package imageandvideo.image;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/api/image")
    public void imageUpload(@RequestPart MultipartFile uploadImg) {
        imageService.imageUploadOnServer(uploadImg);
    }

    @PostMapping("/api/images")
    public void imagesUpload(@RequestPart MultipartFile[] uploadImgs) {
        imageService.imagesUploadOnServer(uploadImgs);
    }

    @GetMapping("/api/image")
    public ResponseEntity<UrlResource> getImage(@RequestParam String imgName) {
        UrlResource imageFromServer = imageService.getImageFromServer(imgName);
        MediaType mediaType = imageService.getMediaType(imgName);
        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(imageFromServer);
    }
}
