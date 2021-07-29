package gsis.com.cms.datainfo.jgAtitle.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import gsis.com.cms.datainfo.jgAtitle.service.JgA03TitleService;
import gsis.com.cms.datainfo.jgAtitle.service.JgA04TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA03TiileDataVO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA04TiileDataVO;
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
public class JgA04TitleController extends BaseAjaxController {



    @Resource(name="JgA04TitleService")
    private JgA04TitleService jgA04TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/a04/List.do")
    public ModelAndView List(@RequestBody JewA04TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgA04TitleService.selectList(searchVO));

        return modelAndView;
    }



    /**
     * 연령별 인구 엑셀 업로드
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/a04/upload.do", method = RequestMethod.POST)
    @ResponseBody
    public Object excelUpload(@RequestParam(value = "uploadFile") MultipartFile file) throws Exception {
        final XlsxReader reader = new XlsxReader();

        List<JewA04TiileDataVO> list = reader.read(JewA04TiileDataVO.class, file.getInputStream(), 0);
        return list;
    }


    /**
     * 연령별 인구 엑셀 저정 처리
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cms/gsis/a04/updateObject.do")
    @ResponseBody
    public ModelAndView updateObject(@RequestBody JewA04TiileDataVO vo) throws Exception {



        //kpiEquipRetService.updateList(list);





        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        jgA04TitleService.insert(vo);

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
    @RequestMapping(value="/cms/gsis/a04/download.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity excelDownload(JewA04TiileDataVO searchVO, HttpServletResponse response) throws Exception {




        jgA04TitleService.excelDownload(searchVO,response);


    return new ResponseEntity(true, HttpStatus.OK);
    }

}
