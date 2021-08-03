package gsis.com.cms.datainfo.jgDtitle.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import gsis.com.cms.datainfo.jgCtitle.service.JgC01TitleService;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC01TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.service.JgD01TitleService;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD01TiileDataVO;
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
public class JgD01TitleController extends BaseAjaxController {



    @Resource(name="JgD01TitleService")
    private JgD01TitleService jgD01TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/d01/List.do")
    public ModelAndView List(@RequestBody JewD01TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgD01TitleService.selectList(searchVO));

        return modelAndView;
    }



    /**
     * 엑셀 업로드
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/d01/upload.do", method = RequestMethod.POST)
    @ResponseBody
    public Object excelUpload(@RequestParam(value = "uploadFile") MultipartFile file) throws Exception {
        final XlsxReader reader = new XlsxReader();

        List<JewD01TiileDataVO> list = reader.read(JewD01TiileDataVO.class, file.getInputStream(), 0);
        return list;
    }


    /**
     * 저장 처리
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cms/gsis/d01/updateObject.do")
    @ResponseBody
    public ModelAndView updateObject(@RequestBody JewD01TiileDataVO vo) throws Exception {



        //kpiEquipRetService.updateList(list);





        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        jgD01TitleService.insert(vo);

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
    @RequestMapping(value="/cms/gsis/d01/download.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity excelDownload(JewD01TiileDataVO searchVO, HttpServletResponse response) throws Exception {




        jgD01TitleService.excelDownload(searchVO,response);


    return new ResponseEntity(true, HttpStatus.OK);
    }

}
