package gsis.com.cms.datainfo.jgFtitle.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import gsis.com.cms.datainfo.jgFtitle.service.JgF01TitleService;
import gsis.com.cms.datainfo.jgFtitle.service.JgF02TitleService;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF01TiileDataVO;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF02TiileDataVO;
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
public class JgF02TitleController extends BaseAjaxController {



    @Resource(name="JgF02TitleService")
    private JgF02TitleService jgF02TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/f02/List.do")
    public ModelAndView List(@RequestBody JewF02TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgF02TitleService.selectList(searchVO));
        return modelAndView;
    }


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/f02/List2.do")
    public ModelAndView List2(@RequestBody JewF02TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgF02TitleService.selectList2(searchVO));
        return modelAndView;
    }




    /**
     * 엑셀 업로드
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/f02/upload.do", method = RequestMethod.POST)
    @ResponseBody
    public Object excelUpload(@RequestParam(value = "uploadFile") MultipartFile file) throws Exception {
        final XlsxReader reader = new XlsxReader();

        List<JewF02TiileDataVO> list = reader.read(JewF02TiileDataVO.class, file.getInputStream(), 0);
        return list;
    }


    /**
     * 저장 처리
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cms/gsis/f02/updateObject.do")
    @ResponseBody
    public ModelAndView updateObject(@RequestBody JewF02TiileDataVO vo) throws Exception {



        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        jgF02TitleService.insert(vo);

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
    @RequestMapping(value="/cms/gsis/f02/download.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity excelDownload(JewF02TiileDataVO searchVO, HttpServletResponse response) throws Exception {




        jgF02TitleService.excelDownload(searchVO,response);


    return new ResponseEntity(true, HttpStatus.OK);
    }

}
