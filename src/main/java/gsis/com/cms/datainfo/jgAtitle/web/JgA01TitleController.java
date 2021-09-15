package gsis.com.cms.datainfo.jgAtitle.web;

import gsis.com.cms.datainfo.jgAtitle.service.JgA01TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA01TiileDataVO;
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
public class JgA01TitleController extends BaseAjaxController {



    @Resource(name="JgA01TitleService")
    private JgA01TitleService jgA01TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/a01/List.do")
    public ModelAndView List(@RequestBody JewA01TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgA01TitleService.selectList(searchVO));

        return modelAndView;
    }



    /**
     * 연령별 인구 엑셀 업로드
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/a01/upload.do", method = RequestMethod.POST)
    @ResponseBody
    public Object excelUpload(@RequestParam(value = "uploadFile") MultipartFile file) throws Exception {
        final XlsxReader reader = new XlsxReader();

        List<JewA01TiileDataVO> list = reader.read(JewA01TiileDataVO.class, file.getInputStream(), 0);
        return list;
    }


    /**
     * 연령별 인구 엑셀 저정 처리
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cms/gsis/a01/updateObject.do")
    @ResponseBody
    public ModelAndView updateObject(@RequestBody JewA01TiileDataVO vo) throws Exception {



        //kpiEquipRetService.updateList(list);




        jgA01TitleService.insert(vo);

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
    @RequestMapping(value="/cms/gsis/a01/download.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity excelDownload(JewA01TiileDataVO searchVO, HttpServletResponse response) throws Exception {




        jgA01TitleService.excelDownload(searchVO,response);


    return new ResponseEntity(true, HttpStatus.OK);
    }

}
