package gsis.com.cms.datainfo.jgCtitle.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import gsis.com.cms.datainfo.jgAtitle.service.JgA04TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA04TiileDataVO;
import gsis.com.cms.datainfo.jgCtitle.service.JgC01TitleService;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC01TiileDataVO;
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
public class JgC01TitleController extends BaseAjaxController {



    @Resource(name="JgC01TitleService")
    private JgC01TitleService jgC01TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/c01/List.do")
    public ModelAndView List(@RequestBody JewC01TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgC01TitleService.selectList(searchVO));

        return modelAndView;
    }



    /**
     * 엑셀 업로드
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/c01/upload.do", method = RequestMethod.POST)
    @ResponseBody
    public Object excelUpload(@RequestParam(value = "uploadFile") MultipartFile file) throws Exception {
        final XlsxReader reader = new XlsxReader();

        List<JewC01TiileDataVO> list = reader.read(JewC01TiileDataVO.class, file.getInputStream(), 0);
        return list;
    }


    /**
     * 저장 처리
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cms/gsis/c01/updateObject.do")
    @ResponseBody
    public ModelAndView updateObject(@RequestBody JewC01TiileDataVO vo) throws Exception {



        //kpiEquipRetService.updateList(list);





        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        jgC01TitleService.insert(vo);

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
    @RequestMapping(value="/cms/gsis/c01/download.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity excelDownload(JewC01TiileDataVO searchVO, HttpServletResponse response) throws Exception {




        jgC01TitleService.excelDownload(searchVO,response);


    return new ResponseEntity(true, HttpStatus.OK);
    }

}
