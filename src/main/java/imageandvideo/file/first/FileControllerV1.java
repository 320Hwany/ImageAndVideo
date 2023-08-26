package imageandvideo.file.first;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileControllerV1 {

    private final FileServiceV1 fileServiceV1;

    @PostMapping("/api/v1/file")
    public void fileUpload(@RequestPart final MultipartFile uploadFile) {
        fileServiceV1.fileUploadOnServer(uploadFile);
    }

    @PostMapping("/api/v1/files")
    public void filesUpload(@RequestPart final MultipartFile[] uploadFiles) {
        fileServiceV1.filesUploadOnServer(uploadFiles);
    }

    @GetMapping("/api/v1/file")
    public ResponseEntity<Resource> getFile(@RequestParam final String fileName) {
        Resource fileFromServer = fileServiceV1.getFileFromServer(fileName);
        MediaType mediaType = fileServiceV1.getMediaType(fileName);

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(fileFromServer);
    }
}
