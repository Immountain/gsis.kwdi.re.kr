package infomind.com.cms.info.sample.web;

import egovframework.com.cmm.annotation.IncludedInfo;
import infomind.com.cms.info.sample.vo.SampleExcelConverVO;
import infomind.com.utils.poi.ss.reader.XlsxReader;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class InfoCmsSampleController {



    private String pagePath ="info/sample/";


    @IncludedInfo(name="인포마인드 샘플",listUrl="/cms/info/sample/view.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/sample/view.do")
    public String samplePage() throws Exception {
        return InfoViewUtils.adminTilesView(pagePath,"view","ax5ui");
    }

    @RequestMapping(value="/cms/info/sample/excelUpload.do", method = RequestMethod.POST)
    @ResponseBody
    public Object excelUpload(@RequestParam(value = "uploadFile") MultipartFile file) throws Exception {
        final XlsxReader reader = new XlsxReader();
        List<SampleExcelConverVO> list = reader.read(SampleExcelConverVO.class, file.getInputStream());
        System.out.println(list);
        return list;
    }
}

