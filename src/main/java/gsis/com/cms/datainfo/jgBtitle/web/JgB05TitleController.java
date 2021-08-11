package gsis.com.cms.datainfo.jgBtitle.web;

import gsis.com.cms.datainfo.jgAtitle.service.JgA02TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA02TiileDataVO;
import gsis.com.cms.datainfo.jgBtitle.service.JgB05TitleService;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB05TiileDataVO;
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
public class JgB05TitleController extends BaseAjaxController {



    @Resource(name="JgB05TitleService")
    private JgB05TitleService jgB05TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/b05/List.do")
    public ModelAndView List(@RequestBody JewB05TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgB05TitleService.selectList(searchVO));

        return modelAndView;
    }



    /**
     * 연령별 인구 엑셀 업로드
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/b05/upload.do", method = RequestMethod.POST)
    @ResponseBody
    public Object excelUpload(@RequestParam(value = "uploadFile") MultipartFile file) throws Exception {
        final XlsxReader reader = new XlsxReader();

        List<JewB05TiileDataVO> list = reader.read(JewB05TiileDataVO.class, file.getInputStream(), 0);
        return list;
    }


    /**
     * 연령별 인구 엑셀 저정 처리
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cms/gsis/b05/updateObject.do")
    @ResponseBody
    public ModelAndView updateObject(@RequestBody JewB05TiileDataVO vo) throws Exception {



        //kpiEquipRetService.updateList(list);




        jgB05TitleService.insert(vo);

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
    @RequestMapping(value="/cms/gsis/b05/download.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity excelDownload(JewB05TiileDataVO searchVO, HttpServletResponse response) throws Exception {




        jgB05TitleService.excelDownload(searchVO,response);


    return new ResponseEntity(true, HttpStatus.OK);
    }

}
