package bg.sofia.uni.fmi.piss.project.wevip.dto;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;

import static org.springframework.util.Base64Utils.encodeToString;

public class ImageDto {

    String encodedImage;

    public ImageDto(String location) throws IOException {
        fromByteArrayToBase64String(location);
    }

    private void fromByteArrayToBase64String(String location) throws IOException {

            FileInputStream in = new FileInputStream(location);
            this.encodedImage = encodeToString(IOUtils.toByteArray(in));
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }
}
