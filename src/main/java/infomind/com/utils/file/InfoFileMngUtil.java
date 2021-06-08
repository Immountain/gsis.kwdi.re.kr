package infomind.com.utils.file;

import egovframework.com.cmm.EgovBrowserUtil;
import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.util.EgovBasicLogger;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cmm.InfoConstants;
import infomind.com.file.service.InfoFileService;
import infomind.com.file.vo.InfoFileDetailVO;
import infomind.com.file.vo.InfoImageSize;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;




@Component("InfoFileMngUtil")
public class InfoFileMngUtil {


    @Resource(name = "egovFileIdGnrService")
    private EgovIdGnrService idgenService;


    @Resource(name = "InfoFileService")
    private InfoFileService infoFileService;


    private List<InfoImageSize> thumbSizes = new ArrayList<>();


    public Map<String, Object> parseFileOneInf(MultipartFile multipartFile ,  String atchFileId,String KeyStr) throws Exception {


        return parseFileOneInf(multipartFile,atchFileId,KeyStr,"");

    }




    public Map<String, Object> parseFileOneInf(MultipartFile multipartFile ,  String atchFileId,String KeyStr,String byteYn) throws Exception {


        Map<String, Object> fileMap = null;
        String atchFileIdString ="";
        String storePathString = InfoConstants.IMAGE_TEMP_DIR()+InfoFileUtil.getTodayString()+"/";
        String filePath = "";
        byte[] blob;
        blob =multipartFile.getBytes();

        if ("".equals(atchFileId) || atchFileId == null) {
            atchFileIdString = idgenService.getNextStringId();
        } else {
            atchFileIdString = atchFileId;
        }

        InfoFileDetailVO fvo = new InfoFileDetailVO();
        fvo.setAtchFileId(atchFileId);

        int fileKey = infoFileService.getMaxFileSN(fvo);
        String originalFileName = multipartFile.getOriginalFilename();
        int index = originalFileName.lastIndexOf(".");
        String fileExt = originalFileName.substring(index + 1);
        String newName = KeyStr + getTimeStamp() + fileKey;
        long size = multipartFile.getSize();

        if(InfoFileUtil.isFileExtImage(fileExt)){
            storePathString = InfoConstants.IMAGE_TEMP_DIR()+InfoFileUtil.getTodayString()+"/";
        }else{
            storePathString = InfoConstants.FILE_TEMP_DIR()+InfoFileUtil.getTodayString()+"/";
        }

        File saveFolder = new File(EgovWebUtil.filePathBlackList(InfoConstants.DIR_PATH()+storePathString));
        if (!saveFolder.exists() || saveFolder.isFile()) {
            //2017.03.03 	조성원 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
            if (saveFolder.mkdirs()){

            }else{

            }
        }
        if (!"".equals(originalFileName)) {
            filePath = InfoConstants.DIR_PATH()+storePathString + File.separator + newName;
            multipartFile.transferTo(new File(EgovWebUtil.filePathBlackList(filePath)));
        }


        fileMap = new HashMap<String, Object>();
        fileMap.put("atchFileId", atchFileIdString);
        fileMap.put("fileSn", String.valueOf(fileKey));
        fileMap.put("fileStreCours", storePathString);
        fileMap.put("streFileNm", newName);
        fileMap.put("orignlFileNm", originalFileName);
        fileMap.put("fileExtsn", fileExt);
        fileMap.put("fileSize", Long.toString(size));


        fvo.setFileExtsn(fileExt);
        fvo.setFileStreCours(storePathString);
        fvo.setFileSize(Long.toString(size));
        fvo.setOrignlFileNm(originalFileName);
        fvo.setStreFileNm(newName);
        fvo.setAtchFileId(atchFileIdString);
        fvo.setFileSn(String.valueOf(fileKey));
        fvo.setPrixFixe(KeyStr);
        fvo.setTempYn("Y");
        fvo.setFiles(blob);

        infoFileService.insertInfoFileDetail(fvo);

        if("Y".equals(byteYn)){

            infoFileService.insertInfoFileByte(fvo);
        }



        return fileMap;
    }



    private static String getTimeStamp() {

        String rtnStr = null;

        // 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
        String pattern = "yyyyMMddhhmmssSSS";

        SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
        Timestamp ts = new Timestamp(System.currentTimeMillis());

        rtnStr = sdfCurrent.format(ts.getTime());

        return rtnStr;
    }


    public void fileImageView(InfoFileDetailVO fileVo,String ThumbnailYn, HttpServletResponse response) throws Exception {


        String fileSn = fileVo.getFileSn();
        //------------------------------------------------------------
        // fileSn이 없는 경우 마지막 파일 참조
        //------------------------------------------------------------
        if (fileSn == null || fileSn.equals("")) {
            int newMaxFileSN = infoFileService.getOneFileSN(fileVo);
            fileVo.setFileSn(Integer.toString(newMaxFileSN));
        }
        //------------------------------------------------------------


        InfoFileDetailVO f = new InfoFileDetailVO();
        if("".equals(fileVo.getImageId())||fileVo.getImageId()==null){
            if("N".equals(ThumbnailYn)){
                //ATCH_FILE_ID 아이디로 원복 이미지
                f = infoFileService.getFileInf(fileVo);
            }else{
                //ATCH_FILE_ID 썸네일
                f = infoFileService.getFileThumbnailInf(fileVo);

            }
        }else{

            if("N".equals(ThumbnailYn)){
                //IMAGE_ID  아이디로 원복 이미지
                f = infoFileService.getFileImageId(fileVo);
            }else{
                // IMAGE_ID 썸네일 이미지
                f = infoFileService.getFileThumbnailImageId(fileVo);

            }


        }



//        InfoFileDetailVO
        //String fileLoaction = fvo.getFileStreCours() + fvo.getStreFileNm();

        File file = null;
        FileInputStream fis = null;

        BufferedInputStream in = null;
        ByteArrayOutputStream bStream = null;

        try {
            file = new File(InfoConstants.DIR_PATH()+f.getFileStreCours(), f.getStreFileNm());
            fis = new FileInputStream(file);

            in = new BufferedInputStream(fis);
            bStream = new ByteArrayOutputStream();

            int imgByte;
            while ((imgByte = in.read()) != -1) {
                bStream.write(imgByte);
            }

            String type = "";

            if (f.getFileExtsn() != null && !"".equals(f.getFileExtsn())) {

                if(InfoFileUtil.isFileExtImage(f.getFileExtsn())){

                    if ("jpg".equals(f.getFileExtsn().toLowerCase())) {
                        type = "image/jpeg";
                    } else {
                        type = "image/" + f.getFileExtsn().toLowerCase();
                    }
                    /*type = "image/" + fvo.getFileExtsn().toLowerCase();*/

                }
            } else {

                System.out.println("Image fileType is null.");

            }

            response.setHeader("Content-Type", EgovWebUtil.removeCRLF(type));
            response.setContentLength(bStream.size());

            bStream.writeTo(response.getOutputStream());

            response.getOutputStream().flush();
            response.getOutputStream().close();

        } finally {
            EgovResourceCloseHelper.close(bStream, in, fis);
        }
    }

    public void fileDown(InfoFileDetailVO fileVo, HttpServletRequest request, HttpServletResponse response) throws Exception {


        String fileSn = fileVo.getFileSn();
        //------------------------------------------------------------
        // fileSn이 없는 경우 마지막 파일 참조
        //------------------------------------------------------------
        if (fileSn == null || fileSn.equals("")) {
            int newMaxFileSN = infoFileService.getOneFileSN(fileVo);
            fileVo.setFileSn(Integer.toString(newMaxFileSN));
        }

        InfoFileDetailVO f = infoFileService.getFileInf(fileVo);
        File uFile = new File(InfoConstants.DIR_PATH()+f.getFileStreCours(), f.getStreFileNm());
        long fSize = uFile.length();
        if (fSize > 0) {
            String mimetype = "application/x-msdownload";
            String userAgent = request.getHeader("User-Agent");
            HashMap<String,String> result = EgovBrowserUtil.getBrowser(userAgent);
            if ( !EgovBrowserUtil.MSIE.equals(result.get(EgovBrowserUtil.TYPEKEY)) ) {
                mimetype = "application/x-stuff";
            }

            String contentDisposition = EgovBrowserUtil.getDisposition(f.getOrignlFileNm(),userAgent,"UTF-8");
            //response.setBufferSize(fSize);	// OutOfMemeory 발생
            response.setContentType(mimetype);
            //response.setHeader("Content-Disposition", "attachment; filename=\"" + contentDisposition + "\"");
            response.setHeader("Content-Disposition", contentDisposition);
            response.setContentLengthLong(fSize);

            /*
             * FileCopyUtils.copy(in, response.getOutputStream());
             * in.close();
             * response.getOutputStream().flush();
             * response.getOutputStream().close();
             */
            BufferedInputStream in = null;
            BufferedOutputStream out = null;

            try {
                in = new BufferedInputStream(new FileInputStream(uFile));
                out = new BufferedOutputStream(response.getOutputStream());

                FileCopyUtils.copy(in, out);
                out.flush();
            } catch (IOException ex) {
                // 다음 Exception 무시 처리
                // Connection reset by peer: socket write error
                EgovBasicLogger.ignore("IO Exception", ex);
            } finally {
                EgovResourceCloseHelper.close(in, out);
            }

        }else{


            response.setContentType("application/x-msdownload");
            PrintWriter printwriter = response.getWriter();
            printwriter.println("<html>");
            printwriter.println("<br><br><br><h2>Could not get file name:<br>" + f.getOrignlFileNm() + "</h2>");
            printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
            printwriter.println("<br><br><br>&copy; webAccess");
            printwriter.println("</html>");
            printwriter.flush();
            printwriter.close();
        }
    }





    private void createThumbnails(InfoFileDetailVO vo) throws Exception {
        String attachId = vo.getAtchFileId();
        List<InfoFileDetailVO> thumbnails = new ArrayList<>();

        int fileSn = 0;

        if (thumbSizes.isEmpty()) {

            for (InfoImageSize thumbSize : InfoConstants.FILE_THUMBS()) {
                InfoFileDetailVO thumb = vo.createThumbnail(thumbSize.getWidth(), thumbSize.getHeight());

                if (thumb != null) {
                    thumb.setAtchFileId(attachId);
                    thumbnails.add(thumb);
                    thumb.setFileSn(String.valueOf(vo.getFileSn()));
                }

                fileSn++;
            }
        } else {
            for (InfoImageSize thumbSize : thumbSizes) {
                InfoFileDetailVO thumb = vo.createThumbnail(thumbSize.getWidth(), thumbSize.getHeight());

                if (thumb != null) {
                    thumb.setAtchFileId(attachId);
                    thumb.setFileSn(String.valueOf(vo.getFileSn()));
                    thumbnails.add(thumb);
                }

                fileSn++;
            }
        }

        if (!thumbnails.isEmpty()) {
            // 쌈네일 저장 처리
            infoFileService.insertInfoFileThumbnail(thumbnails);

        }
    }


    public void copyFile(String atchFileId) throws Exception {
        copyFile(atchFileId,"N");
    }



    public void copyFile(String atchFileId, String thumbnailYn) throws Exception {



        List<InfoFileDetailVO> fileList = new ArrayList<>();
        InfoFileDetailVO fileVo = new InfoFileDetailVO();
        fileVo.setAtchFileId(atchFileId);
        fileList =infoFileService.getInfoFileTempList(fileVo);

        if(fileList.size()>0){

            for(InfoFileDetailVO vo :fileList){

                String copyFileDir =vo.getFileDir();
                String orgFileDir =vo.getFileStreCours();

                if("Y".equals(vo.getTempYn())){

                    File uploadFile = vo.toFile();

                    File saveFolder = new File(EgovWebUtil.filePathBlackList(copyFileDir));
                    if (!saveFolder.exists() || saveFolder.isFile()) {
                        //2017.03.03 	조성원 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
                        if (saveFolder.mkdirs()){

                        }else{

                        }
                    }

                    File copyFile = new File(InfoConstants.DIR_PATH()+copyFileDir + File.separator + vo.getStreFileNm());
                    FileUtils.copyFile(uploadFile, copyFile);
                    vo.setFileStreCours(copyFileDir);
                    vo.setTempYn("N");

                    infoFileService.updateInfoFileDetail(vo);

                    if("Y".equals(thumbnailYn)){
                         createThumbnails(vo);
                     }
                    InfoFileUtil.deleteFile(InfoConstants.DIR_PATH()+orgFileDir+vo.getStreFileNm());
                 }
            }

        }

    }


  }
