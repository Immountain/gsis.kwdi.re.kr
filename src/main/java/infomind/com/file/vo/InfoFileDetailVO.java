package infomind.com.file.vo;


import infomind.com.cmm.InfoConstants;
import infomind.com.utils.file.InfoFileUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InfoFileDetailVO {

    private String atchFileId = "";
    private String fileSn = "";
    private String fileStreCours = "";
    private String streFileNm = "";
    private String orignlFileNm = "";
    private String fileExtsn = "";
    private String fileCn = "";
    private String fileSize = "";
    private String filePathUrl = "";
    private int width = 0;
    private int height = 0;
    private String regDt = "";
    private String modDt = "";
    private String regId = "";
    private String modId = "";

    private String deleteYn = "";
    private String tempYn = "";
    private String prixFixe = "";

    private String imageId = "";
    private String imageNm = "";
    private String imageGb = "";
    private String imageDc = "";

    private String fileMg = "";
    private String byteYn = "N";
    private boolean thumbnail = false;

    private byte[] files;


    public boolean isImage() {
        return InfoFileUtil.isFileExtImage(this.getFileExtsn());
    }

    public boolean isThumbnail() {
        return thumbnail;
    }

    public InfoFileDetailVO createThumbnail(int width, int height) throws Exception {
        return InfoFileUtil.createThumbnail(this, width, height);
    }


    public String getFileStreCours() {


        return this.fileStreCours;
    }

    public String getFileDir() {


        String pathDir = InfoFileUtil.getYearString() + "/" + InfoFileUtil.getMonthString();
        if (InfoFileUtil.isFileExtImage(this.getFileExtsn())) {

            return InfoConstants.IMAGE_DIR() + pathDir;
        } else {

            return InfoConstants.FILE_DIR() + pathDir;
        }
    }


    public File toFile() {
        if (isThumbnail()) {
            return new File(InfoConstants.DIR_PATH() + getFileStreCours() + "/thumbnail/" + width + "_" + height + "/" + getStreFileNm());
        } else {
            return new File(InfoConstants.DIR_PATH() + getFileStreCours() + "/" + getStreFileNm());
        }
    }
}

