package gsis.com.cms.datainfo.jgDtitle.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import gsis.com.cms.datainfo.jgDtitle.service.JgD03TitleService;
import gsis.com.cms.datainfo.jgDtitle.service.JgD04TitleService;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD03TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD04TiileDataVO;
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
public class JgD04TitleController extends BaseAjaxController {



    @Resource(name="JgD04TitleService")
    private JgD04TitleService jgD04TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/d04/List.do")
    public ModelAndView List(@RequestBody JewD04TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgD04TitleService.selectList(searchVO));

        return modelAndView;
    }



    /**
     * 엑셀 업로드
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/d04/upload.do", method = RequestMethod.POST)
    @ResponseBody
    public Object excelUpload(@RequestParam(value = "uploadFile") MultipartFile file) throws Exception {
        final XlsxReader reader = new XlsxReader();

        List<JewD04TiileDataVO> list = reader.read(JewD04TiileDataVO.class, file.getInputStream(), 0);
        return list;
    }


    /**
     * 저장 처리
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cms/gsis/d04/updateObject.do")
    @ResponseBody
    public ModelAndView updateObject(@RequestBody JewD04TiileDataVO vo) throws Exception {



        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        jgD04TitleService.insert(vo);

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
    @RequestMapping(value="/cms/gsis/d04/download.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity excelDownload(JewD04TiileDataVO searchVO, HttpServletResponse response) throws Exception {




        jgD04TitleService.excelDownload(searchVO,response);


    return new ResponseEntity(true, HttpStatus.OK);
    }

}
