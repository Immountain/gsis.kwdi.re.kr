package infomind.com.utils.file;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.utl.fcc.service.EgovFormBasedUUID;
import infomind.com.cmm.InfoConstants;
import infomind.com.file.vo.InfoFileDetailVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InfoFileUtil {



    /**
     * 오늘 년도 문자열 취득.
     * ex) 20090101
     * @return
     */
    public static String getYearString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy", Locale.getDefault());

        return format.format(new Date());
    }

    /**
     * 오늘 월 문자열 취득.
     * ex) 20090101
     * @return
     */
    public static String getMonthString() {
        SimpleDateFormat format = new SimpleDateFormat("MM", Locale.getDefault());

        return format.format(new Date());
    }

    /**
     * 오늘 날짜 문자열 취득.
     * ex) 20090101
     * @return
     */
    public static String getTodayString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

        return format.format(new Date());
    }

    /**
     * 물리적 파일명 생성.
     * @return
     */
    public static String getPhysicalFileName() {
        return EgovFormBasedUUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * 파일명 변환.
     * @param filename String
     * @return
     * @throws Exception
     */
    protected static String convert(String filename) throws Exception {
        //return java.net.URLEncoder.encode(filename, "utf-8");
        return filename;
    }


    public static Boolean isFileExtImage(String fileExt){

        String tempFileExt =fileExt.toUpperCase();

        boolean isPermissionFileExt = false;

        final String[] PERMISSION_FILE_EXT_ARR = {"GIF", "JPEG", "JPG", "PNG", "BMP"};


        for( int i = 0; i < PERMISSION_FILE_EXT_ARR.length; i++ ) {
            if( PERMISSION_FILE_EXT_ARR[i].equals(tempFileExt) ) {
                isPermissionFileExt = true;
                break;
            }
        }
        return isPermissionFileExt;
    }


    public static InfoFileDetailVO createThumbnail(InfoFileDetailVO vo, int width, int height) throws Exception {
        return createThumbnail(vo, width, height, Scalr.Mode.AUTOMATIC);
    }


    public static InfoFileDetailVO createThumbnail(InfoFileDetailVO vo,int width, int height, Scalr.Mode mode) throws Exception {
        File uploadFile = vo.toFile();

        if (!vo.isImage()) {
            return null;
        }

       ;
        String strePath =  vo.getFileStreCours()+"/thumbnail/" + width + "_" + height;
        String thumbnailUploadDirPath = strePath;


        System.out.println("thumbnailUploadDirPath==>"+thumbnailUploadDirPath);

        InfoFileDetailVO thumbnailWpFile = new InfoFileDetailVO();
        BeanUtils.copyProperties(thumbnailWpFile, vo);

        File thumbnailDir = new File(InfoConstants.DIR_PATH()+thumbnailUploadDirPath);
        thumbnailWpFile.setFileStreCours(strePath);

        if (!thumbnailDir.isDirectory()) {
            thumbnailDir.mkdirs();
        }

        File thumbnailFile = new File(InfoConstants.DIR_PATH()+thumbnailUploadDirPath + File.separator + thumbnailWpFile.getStreFileNm());
        FileUtils.copyFile(uploadFile, thumbnailFile);

        BufferedImage targetFile = ImageIO.read(thumbnailFile);
        BufferedImage thumbnail = null;

        if (width != 0 && height != 0) {
            thumbnail = Scalr.resize(targetFile, mode, width, height);
        } else if (width != 0) {
            thumbnail = Scalr.resize(targetFile, mode, width);
        }

        if (thumbnail != null) {
            ImageIO.write(thumbnail, thumbnailWpFile.getFileExtsn(), thumbnailFile);
        }

        thumbnailWpFile.setFileMg(String.valueOf(FileUtils.sizeOf(new File(thumbnailFile.getPath()))));
        thumbnailWpFile.setWidth(width);
        thumbnailWpFile.setHeight(height);

        return thumbnailWpFile;
    }



    public static String deleteFile(String fileDeletePath) {

        // 인자값 유효하지 않은 경우 블랭크 리턴
        if (fileDeletePath == null || fileDeletePath.equals("")) {
            return "";
        }
        String result = "";
        File file = new File(EgovWebUtil.filePathBlackList(fileDeletePath));
        if (file.isFile()) {
            result = deletePath(fileDeletePath);
        } else {
            result = "";
        }

        return result;
    }


    public static String deletePath(String filePath) {
        File file = new File(EgovWebUtil.filePathBlackList(filePath));
        String result = "";

        if (file.exists()) {
            result = file.getAbsolutePath();
            if (!file.delete()) {
                result = "";
            }
        }

        return result;
    }
}
