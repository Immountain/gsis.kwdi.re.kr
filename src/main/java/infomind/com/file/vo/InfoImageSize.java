package infomind.com.file.vo;

import lombok.Data;

@Data
public class InfoImageSize {



    private int width;
    private int height;



    public InfoImageSize() {
    }
    public InfoImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
