package infomind.com.tags.tag.html;


import infomind.com.cms.info.banner.service.InfoBannerGroupService;
import infomind.com.cms.info.banner.service.InfoBannerService;
import infomind.com.cms.info.banner.vo.InfoBannerGroupVO;
import infomind.com.cms.info.banner.vo.InfoBannerVO;
import infomind.com.cms.info.board.service.InfoBoardItemService;
import infomind.com.utils.web.InfoWebRequestUtils;

import javax.servlet.jsp.JspTagException;
import java.util.ArrayList;
import java.util.List;

public class InfoBannerTag extends WpBaseHtmlTag {
    private String group = "main";
    private String skinName = "";

    @Override
    protected int doStartTagWp() throws JspTagException {
        int result = super.doStartTagWp();
        try {



            InfoBannerGroupService bannerGroupService = applicationContext.getBean(InfoBannerGroupService.class);
            InfoBannerService infoBannerService = applicationContext.getBean(InfoBannerService.class);


            InfoBannerGroupVO bnfoBannerGroupVO = new InfoBannerGroupVO();
            bnfoBannerGroupVO.setBannerGroupId(group);

            bnfoBannerGroupVO =bannerGroupService.selectBannerGroup(bnfoBannerGroupVO);


            List<InfoBannerVO> bannerList = new ArrayList<>();
            InfoBannerVO infoBannerVO = new InfoBannerVO();
            infoBannerVO.setBannerGroupId(group);
            infoBannerVO.setRecordCountPerPage(999999);
            infoBannerVO.setFirstIndex(0);


            bannerList = (List<InfoBannerVO>) infoBannerService.selectBannerList(infoBannerVO);

            //기본 일때

          if("".equals(this.skinName)){


              StringBuilder sb = new StringBuilder();

              if (!bannerList.isEmpty()) {
                  sb.append(String.format("<ul class='wp-banner wp-banner-%s'>", getGroup()));
              }

              for (InfoBannerVO vo : bannerList) {
                  sb.append("<li>");
                  sb.append(String.format("<a href='%s' target='_blank'>", vo.getLinkUrl()));
                  sb.append(String.format("<img src='%s'/>", "/cmm/fms/getImage.do?atchFileId=" + vo.getBannerImage()));
                  sb.append("</a>");
                  sb.append("</li>");
              }

              if (!bannerList.isEmpty()) {
                  sb.append("</ul>");
              }

              this.pageContext.getOut().write(sb.toString());
          }else{

              pageContext.getRequest().setAttribute("group", group);
              pageContext.getRequest().setAttribute("folder", skinName);
              pageContext.getRequest().setAttribute("bannerGrp", bnfoBannerGroupVO);
              pageContext.getRequest().setAttribute("bannerList", bannerList);

              InfoWebRequestUtils.include(pageContext, pageContext.getOut(), "/banner/latest.do?skinName="+this.skinName);



          }








        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }
}
