package imageandvideo.file;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;

    @PostMapping("/api/file")
    public void fileUpload(@RequestPart MultipartFile uploadFile) {
        fileService.fileUploadOnServer(uploadFile);
    }

    @PostMapping("/api/files")
    public void filesUpload(@RequestPart MultipartFile[] uploadFiles) {
        fileService.filesUploadOnServer(uploadFiles);
    }

    @GetMapping("/api/file")
    public ResponseEntity<UrlResource> getFile(@RequestParam String fileName) {
        UrlResource fileFromServer = fileService.getFileFromServer(fileName);
        MediaType mediaType = fileService.getMediaType(fileName);
        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(fileFromServer);
    }
}
