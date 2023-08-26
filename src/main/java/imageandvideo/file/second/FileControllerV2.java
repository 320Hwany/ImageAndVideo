package imageandvideo.file.second;

import imageandvideo.file.first.FileServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileControllerV2 {

    private final FileServiceV2 fileServiceV2;

    @GetMapping("/api/file")
    public ResponseEntity<Resource> getFile(@RequestParam String fileName) {
        Resource fileFromServer = fileServiceV2.loadAsResource(fileName);
        MediaType mediaType = fileServiceV2.getMediaType(fileName);

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(fileFromServer);
    }

    @PostMapping("/api/v2/file")
    public void fileUpload(@RequestPart MultipartFile uploadFile) {
        fileServiceV2.saveToServer(uploadFile);
    }
}
