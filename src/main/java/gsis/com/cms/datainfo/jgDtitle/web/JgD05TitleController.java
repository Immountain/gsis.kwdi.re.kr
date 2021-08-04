package gsis.com.cms.datainfo.jgDtitle.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import gsis.com.cms.datainfo.jgDtitle.service.JgD04TitleService;
import gsis.com.cms.datainfo.jgDtitle.service.JgD05TitleService;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD04TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD05TiileDataVO;
import infomind.com.cmm.web.BaseAjaxController;
import infomind.com.utils.poi.ss.reader.XlsxReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class JgD05TitleController extends BaseAjaxController {



    @Resource(name="JgD05TitleService")
    private JgD05TitleService jgD05TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/d05/List.do")
    public ModelAndView List(@RequestBody JewD05TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgD05TitleService.selectList(searchVO));

        return modelAndView;
    }



    /**
     * 엑셀 업로드
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/d05/upload.do", method = RequestMethod.POST)
    @ResponseBody
    public Object excelUpload(@RequestParam(value = "uploadFile") MultipartFile file) throws Exception {
        final XlsxReader reader = new XlsxReader();

        List<JewD05TiileDataVO> list = reader.read(JewD05TiileDataVO.class, file.getInputStream(), 0);
        return list;
    }


    /**
     * 저장 처리
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cms/gsis/d05/updateObject.do")
    @ResponseBody
    public ModelAndView updateObject(@RequestBody JewD05TiileDataVO vo) throws Exception {



        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        jgD05TitleService.insert(vo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.addObject("message", "저장완료");


        return modelAndView;
    }


    /**
     * 엑셀샘플다운로드
     * @param searchVO
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/d05/download.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity excelDownload(JewD05TiileDataVO searchVO, HttpServletResponse response) throws Exception {




        jgD05TitleService.excelDownload(searchVO,response);


    return new ResponseEntity(true, HttpStatus.OK);
    }

}
