package imageandvideo.file;

import lombok.Getter;

import java.nio.file.Path;

@Getter
public class CommonConstant {

    private CommonConstant() {
    }

    public static final String STORAGE_ADDRESS = "/Users/jeong-youhwan/file/";

    public static final Path STORAGE_ADDRESS_PATH = Path.of("/Users/jeong-youhwan/file/");
}
