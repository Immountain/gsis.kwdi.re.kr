package infomind.com.tags.tag.html;
import infomind.com.cmm.InfoConstants;
import infomind.com.cms.info.page.service.InfoPageInfoService;
import infomind.com.cms.info.page.vo.InfoPageInfoVO;
import infomind.com.tags.tag.InfoBaseTag;
import infomind.com.utils.web.InfoWebRequestUtils;

import javax.servlet.jsp.JspTagException;
import java.io.File;

public class InfoIncludeTag extends InfoBaseTag {



    protected String id;

    @Override
    protected int doStartTagWp() throws JspTagException {

        InfoPageInfoService infoPageInfoService = applicationContext.getBean(InfoPageInfoService.class);

        InfoPageInfoVO infoPageInfoVO = new InfoPageInfoVO();
        infoPageInfoVO.setPageId(id);


        try {
            infoPageInfoVO =infoPageInfoService.selecPageInfoDetail(infoPageInfoVO);

            String filePath ="/WEB-INF/jsp/page/"+infoPageInfoVO.getPageGroupId()+"/"+infoPageInfoVO.getPageId();

            if("JSP".equals(infoPageInfoVO.getModType())){

                File includeFile = new  File(InfoConstants.DIR_PATH()+filePath+"/info","info.jsp");
                if (!includeFile.exists()) {
                    throw new Exception(String.format("include 할 파일을 찾을수 없습니다. : %s", includeFile.getAbsolutePath()));
                }
                InfoWebRequestUtils.include(pageContext, pageContext.getOut(), filePath + "/info/" + "info" + ".jsp");

            }else if("DB".equals(infoPageInfoVO.getModType())){
                pageContext.getOut().write(infoPageInfoVO.getContentInfoDecode());
            }else{
                File includeFile = new  File(InfoConstants.DIR_PATH()+filePath,"info.jsp");
                if (!includeFile.exists()) {
                    throw new Exception(String.format("include 할 파일을 찾을수 없습니다. : %s", includeFile.getAbsolutePath()));
                }

                InfoWebRequestUtils.include(pageContext, pageContext.getOut(), filePath + "/" + "view" + ".jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
