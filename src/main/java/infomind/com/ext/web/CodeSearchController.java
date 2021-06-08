package infomind.com.ext.web;



import infomind.com.ext.service.CodeSearchService;
import infomind.com.ext.vo.CodeSearchVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class CodeSearchController {



    @Resource(name = "CodeSearchService")
    private CodeSearchService codeSearchService;

    /**
     * 공통분류코드
     * @param codeSearchVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/code/search/selectComtccmmnclcodeList.do")
    @ResponseBody
    public  List<CodeSearchVO> selectComtccmmnclcodeList(CodeSearchVO codeSearchVO  , ModelMap model) throws Exception {

        List<CodeSearchVO> list =codeSearchService.selectComtccmmnclcodeList(codeSearchVO);
        return list;

    }


    /**
     * 공통코드
     * @param codeSearchVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/code/search/selectComtccmmncodeList.do")
    @ResponseBody
    public  List<CodeSearchVO> selectComtccmmncodeList(CodeSearchVO codeSearchVO  , ModelMap model) throws Exception {

        List<CodeSearchVO> list =codeSearchService.selectComtccmmncodeList(codeSearchVO);
        return list;

    }


    /**
     * 공통상세 코드
     * @param codeSearchVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/code/search/selectComtccmmndetailcodeList.do")
    @ResponseBody
    public  List<CodeSearchVO> selectComtccmmndetailcodeList(CodeSearchVO codeSearchVO  , ModelMap model) throws Exception {

        List<CodeSearchVO> list =codeSearchService.selectComtccmmndetailcodeList(codeSearchVO);
        return list;
     }

}
