package gsis.com.cms.excell.web;


import egovframework.com.cmm.annotation.IncludedInfo;
import gsis.com.cms.excell.vo.ExcellVO;
import infomind.com.cmm.web.BaseAjaxController;
import infomind.com.utils.poi.ss.reader.XlsxReader;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GsisExcellController extends BaseAjaxController {


    private String pagePath ="excell/";

    /** 테스트엑셀업로드 목록 */
    @IncludedInfo(name="테스트엑셀업로드", listUrl ="/cms/gsis/excell/testList.do", order = 1111, gid = 60)
    @RequestMapping(value="/cms/gsis/excell/testList.do")
    public String jewMdisList(@ModelAttribute("searchVO") ExcellVO searchVO)throws Exception{



        return InfoViewUtils.adminJsView(pagePath,"testExcellList","ax5ui");
    }


    @RequestMapping(value="/cms/gsis/excel/upload.do", method = RequestMethod.POST)
    @ResponseBody
    public Object excelUpload(@RequestParam(value = "uploadFile") MultipartFile file) throws Exception {
        final XlsxReader reader = new XlsxReader();

        List<ExcellVO> list = reader.read(ExcellVO.class, file.getInputStream(), 0);
        return list;
    }


//    @RequestMapping(value = "/jtp/cms/kpiEquipRet/updateObject.do")
//    @ResponseBody
//    public ModelAndView updateObject(@RequestBody List<KpiEquipRetVO> list) throws Exception {
//
//        kpiEquipRetService.updateList(list);
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("jsonView");
//        modelAndView.setStatus(HttpStatus.OK);
//        modelAndView.addObject("message", egovMessageSource.getMessage("success.common.update"));
//        return modelAndView;
//    }


}
