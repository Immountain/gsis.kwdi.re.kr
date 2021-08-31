package gsis.com.cms.datainfo.jgBtitle.web;

import gsis.com.cms.datainfo.jgBtitle.service.JgB03TitleService;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB03TiileDataVO;
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
public class JgB03TitleController extends BaseAjaxController {



    @Resource(name="JgB03TitleService")
    private JgB03TitleService jgB03TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/b03/List.do")
    public ModelAndView List(@RequestBody JewB03TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgB03TitleService.selectList(searchVO));

        return modelAndView;
    }



    /**
     * 연령별 인구 엑셀 업로드
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/b03/upload.do", method = RequestMethod.POST)
    @ResponseBody
    public Object excelUpload(@RequestParam(value = "uploadFile") MultipartFile file) throws Exception {
        final XlsxReader reader = new XlsxReader();

        List<JewB03TiileDataVO> list = reader.read(JewB03TiileDataVO.class, file.getInputStream(), 0);
        return list;
    }


    /**
     * 연령별 인구 엑셀 저정 처리
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cms/gsis/b03/updateObject.do")
    @ResponseBody
    public ModelAndView updateObject(@RequestBody JewB03TiileDataVO vo) throws Exception {



        //kpiEquipRetService.updateList(list);




        jgB03TitleService.insert(vo);

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
    @RequestMapping(value="/cms/gsis/b03/download.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity excelDownload(JewB03TiileDataVO searchVO, HttpServletResponse response) throws Exception {




        jgB03TitleService.excelDownload(searchVO,response);


    return new ResponseEntity(true, HttpStatus.OK);
    }

}
