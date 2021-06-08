package infomind.com.file.web;


import egovframework.com.cmm.EgovBrowserUtil;
import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovBasicLogger;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cmm.visit.InfoVisitFactory;
import infomind.com.cms.info.site.vo.InfoSiteVisitVO;
import infomind.com.file.service.InfoFileDownloadInfoService;
import infomind.com.file.service.InfoFileService;
import infomind.com.file.vo.InfoFileDetailVO;
import infomind.com.file.vo.InfoFileDownloadInfoVO;
import infomind.com.utils.file.InfoFileMngUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;


@Controller
public class InfoFileController {



    @Resource(name="InfoFileMngUtil")
    private InfoFileMngUtil fileUtil;

    @Resource(name = "InfoFileService")
    private InfoFileService infoFileService;

    @Resource(name = "egovFileIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name = "InfoFileDownloadInfoService")
    private InfoFileDownloadInfoService infoFileDownloadInfoService;




    @RequestMapping(value = "/cms/info/file/atchFileId.do")
    @ResponseBody
    public String atchFileId(InfoFileDetailVO file)
            throws Exception {


        String atchFileId =idgenService.getNextStringId();;

        return atchFileId;

    }


    @RequestMapping(value = "/cms/info/file/upload.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>fileUpload(InfoFileDetailVO file,MultipartHttpServletRequest multiRequest)
            throws Exception {


        Iterator<String> iterator = multiRequest.getFileNames();
        MultipartFile multipartFile = null;
        Map<String, Object> fileMap = null;


        System.out.println("file==>"+file.getAtchFileId());

        multipartFile = multiRequest.getFile(iterator.next());


       if (multipartFile.isEmpty() == false) {
           String prixFixe ="";

           if("".equals(file.getPrixFixe())||file.getPrixFixe()==null){
               prixFixe ="FILE_";
           }else{
               prixFixe =file.getPrixFixe();
           }
           fileMap=  fileUtil.parseFileOneInf(multipartFile,file.getAtchFileId(),prixFixe,file.getByteYn());
        }


        return fileMap ;
    }





    @RequestMapping(value = "/cms/info/file/list.do")
    @ResponseBody
    public List<InfoFileDetailVO> list(InfoFileDetailVO file)
            throws Exception {
        return infoFileService.getInfoFileList(file);

    }

    @RequestMapping(value = "/cms/info/file/tempList.do")
    @ResponseBody
    public List<InfoFileDetailVO> tempList(InfoFileDetailVO file)
            throws Exception {
        return infoFileService.getInfoFileTempList(file);

    }




    @ResponseBody
    @RequestMapping(value = "/cms/info/file/ImageView.do", method = RequestMethod.GET)
    public void view(InfoFileDetailVO fileVo, HttpServletResponse response) throws Exception {

        fileUtil.fileImageView(fileVo,"N",response);

    }

    @ResponseBody
    @RequestMapping(value = "/cms/info/file/ImageThumbnailView.do", method = RequestMethod.GET)
    public void ImageThumbnailView(InfoFileDetailVO fileVo, HttpServletResponse response) throws Exception {

        fileUtil.fileImageView(fileVo,"Y",response);

    }



    @RequestMapping(value = "/cms/info/file/delete.do")
    @ResponseBody
    public String delete(InfoFileDetailVO file)
            throws Exception {


        infoFileService.deleteInfoFileDetail(file);
       return "ok";

    }


    @RequestMapping(value = "/cms/info/file/fileDown.do")
    public void fileDown(InfoFileDetailVO fileVo, HttpServletRequest request, HttpServletResponse response)
            throws Exception {



        //메뉴 통계
        InfoFileDownloadInfoVO file = InfoVisitFactory.downliadRequest(request);
        file.setAtchFileId(fileVo.getAtchFileId());
        file.setFileSn(fileVo.getFileSn());

        infoFileDownloadInfoService.getInfoFileDownloadInfo(file);

        fileUtil.fileDown(fileVo,request,response);
    }

    @RequestMapping(value="/cms/info/file/preview.do", produces="text/plain;charset=UTF-8")
    public void preview(HttpServletRequest request, HttpServletResponse response){
        InfoFileDetailVO fileInfo = null;
        InputStream is = null;
        try {

            InfoFileDetailVO param = new InfoFileDetailVO();
            param.setAtchFileId("FILE_000000000000312");
            param.setFileSn("1");


            fileInfo = infoFileService.selectInfoFileByte(param);




            String contentType = "image/jpeg";
            response.setContentType(contentType);

            is = new ByteArrayInputStream(fileInfo.getFiles());

            ServletOutputStream os = response.getOutputStream();
            int binaryRead;

            while ((binaryRead = is.read()) != -1)    {
                os.write(binaryRead);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{

        }
    }




}
