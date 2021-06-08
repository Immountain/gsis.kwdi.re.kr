package infomind.com.site.web;


import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cmm.visit.InfoVisitFactory;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Controller
public class SiteFileController {



    @Resource(name="InfoFileMngUtil")
    private InfoFileMngUtil fileUtil;

    @Resource(name = "InfoFileService")
    private InfoFileService infoFileService;

    @Resource(name = "egovFileIdGnrService")
    private EgovIdGnrService idgenService;



    @RequestMapping(value = "/site/info/file/atchFileId.do")
    @ResponseBody
    public String atchFileId(InfoFileDetailVO file)
            throws Exception {


        String atchFileId =idgenService.getNextStringId();;

        return atchFileId;

    }


    @RequestMapping(value = "/site/info/file/upload.do", method = RequestMethod.POST)
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
           fileMap=  fileUtil.parseFileOneInf(multipartFile,file.getAtchFileId(),prixFixe);
        }


        return fileMap ;
    }

    @RequestMapping(value = "/site/info/file/list.do")
    @ResponseBody
    public List<InfoFileDetailVO> list(InfoFileDetailVO file)
            throws Exception {
        return infoFileService.getInfoFileList(file);

    }

    @RequestMapping(value = "/site/info/file/tempList.do")
    @ResponseBody
    public List<InfoFileDetailVO> tempList(InfoFileDetailVO file)
            throws Exception {
        return infoFileService.getInfoFileTempList(file);

    }




    @ResponseBody
    @RequestMapping(value = "/site/info/file/ImageView.do", method = RequestMethod.GET)
    public void view(InfoFileDetailVO fileVo, HttpServletResponse response) throws Exception {

        fileUtil.fileImageView(fileVo,"N",response);

    }

    @ResponseBody
    @RequestMapping(value = "/site/info/file/ImageThumbnailView.do", method = RequestMethod.GET)
    public void ImageThumbnailView(InfoFileDetailVO fileVo, HttpServletResponse response) throws Exception {

        fileUtil.fileImageView(fileVo,"Y",response);

    }



    @RequestMapping(value = "/site/info/file/delete.do")
    @ResponseBody
    public String delete(InfoFileDetailVO file)
            throws Exception {


        infoFileService.deleteInfoFileDetail(file);
       return "ok";

    }


    @RequestMapping(value = "/site/info/file/fileDown.do")
    public void fileDown(InfoFileDetailVO fileVo, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        InfoFileDownloadInfoVO file = InfoVisitFactory.downliadRequest(request);
        file.setAtchFileId(fileVo.getAtchFileId());
        file.setFileSn(fileVo.getFileSn());


        fileUtil.fileDown(fileVo,request,response);
    }





}
