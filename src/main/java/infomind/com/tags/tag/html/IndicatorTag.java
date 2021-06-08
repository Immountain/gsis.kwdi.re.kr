package infomind.com.tags.tag.html;

import infomind.com.cmm.bean.InfoUserMenuCacheManager;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.utils.web.InfoWebRequestUtils;

import javax.servlet.jsp.JspTagException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IndicatorTag extends WpBaseHtmlTag {
    private String group = "main";
    private String skinName = "";
    private String siteMemuId = "";

    @Override
    protected int doStartTagWp() throws JspTagException {
        int result = super.doStartTagWp();
        try {

            InfoUserMenuCacheManager cacheUserMenu = applicationContext.getBean(InfoUserMenuCacheManager.class);

            List<InfoSiteMenuVO> list =cacheUserMenu.getMenuByALlList("");



            List<InfoSiteMenuVO> depth1List = new ArrayList<InfoSiteMenuVO>();
            List<InfoSiteMenuVO> depth2List = new ArrayList<InfoSiteMenuVO>();
            List<InfoSiteMenuVO> depth3List = new ArrayList<InfoSiteMenuVO>();


            String depth1Title = "";
            String depth2Title = "";
            String depth3Title = "";






            if(!"".equals(siteMemuId)){

                System.out.println("siteMemuId==>"+siteMemuId);

                InfoSiteMenuVO  currentDeptMenu = new InfoSiteMenuVO();
                currentDeptMenu =cacheUserMenu.getMenuByCode(siteMemuId);
                System.out.println("currentDeptMenu==>"+currentDeptMenu.getDepth());

                if (currentDeptMenu != null) {

                    if (currentDeptMenu.getDepth() == 3) {

                        InfoSiteMenuVO  currentDeptMenu3 = new InfoSiteMenuVO();
                        currentDeptMenu3 =cacheUserMenu.getMenuByCode(siteMemuId);
                        depth3List =cacheUserMenu.getMenuByParentId(currentDeptMenu3.getParentId(),currentDeptMenu3.getSiteMemuId());
                        depth3Title =currentDeptMenu3.getSiteMemuNm();


                        InfoSiteMenuVO currentDeptMenu2 = cacheUserMenu.getMenuByCode(currentDeptMenu3.getParentId());
                        depth2List =cacheUserMenu.getMenuByParentId(currentDeptMenu2.getParentId(),currentDeptMenu2.getSiteMemuId());
                        depth2Title =currentDeptMenu2.getSiteMemuNm();




                        InfoSiteMenuVO currentDeptMenu1 = cacheUserMenu.getMenuByCode(currentDeptMenu2.getParentId());
                        depth1List =cacheUserMenu.getMenuByParentId(currentDeptMenu1.getParentId(),currentDeptMenu1.getSiteMemuId());
                        depth1Title =currentDeptMenu1.getSiteMemuNm();




                    } else if (currentDeptMenu.getDepth() == 2) {

                        InfoSiteMenuVO currentDeptMenu2 = cacheUserMenu.getMenuByCode(currentDeptMenu.getSiteMemuId());
                        depth2List =cacheUserMenu.getMenuByParentId(currentDeptMenu2.getParentId(),currentDeptMenu2.getSiteMemuId());
                        depth2Title =currentDeptMenu2.getSiteMemuNm();


                        InfoSiteMenuVO currentDeptMenu1 = cacheUserMenu.getMenuByCode(currentDeptMenu2.getParentId());
                        depth1List =cacheUserMenu.getMenuByParentId(currentDeptMenu1.getParentId(),currentDeptMenu1.getSiteMemuId());
                        depth1Title =currentDeptMenu1.getSiteMemuNm();


                    } else if (currentDeptMenu.getDepth() == 1) {

                        System.out.println("currentDeptMenu==>여기");

                        InfoSiteMenuVO currentDeptMenu1 = cacheUserMenu.getMenuByCode(currentDeptMenu.getSiteMemuId());
                        depth1List =cacheUserMenu.getMenuByParentId(currentDeptMenu1.getParentId(),currentDeptMenu1.getSiteMemuId());
                        depth1Title =currentDeptMenu1.getSiteMemuNm();
                    }


                }

            }


            pageContext.getRequest().setAttribute("group", group);
            pageContext.getRequest().setAttribute("skinName", skinName);
            pageContext.getRequest().setAttribute("userMenuList", list);


            pageContext.getRequest().setAttribute("depth1List", depth1List);
            pageContext.getRequest().setAttribute("depth2List", depth2List);
            pageContext.getRequest().setAttribute("depth3List", depth3List);

            pageContext.getRequest().setAttribute("depth1Title", depth1Title);
            pageContext.getRequest().setAttribute("depth2Title", depth2Title);
            pageContext.getRequest().setAttribute("depth3Title", depth3Title);


            InfoWebRequestUtils.include(pageContext, pageContext.getOut(), "/menu/latest.do?skinName="+this.skinName);
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

    public String getSiteMemuId() {
        return siteMemuId;
    }

    public void setSiteMemuId(String siteMemuId) {
        this.siteMemuId = siteMemuId;
    }



}
