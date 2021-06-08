package infomind.com.tags.tag.html;


import infomind.com.cmm.bean.InfoUserMenuCacheManager;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.utils.web.InfoWebRequestUtils;
import javax.servlet.jsp.JspTagException;
import java.util.ArrayList;
import java.util.List;


public class InfoMenuTag extends WpBaseHtmlTag {
    private String group = "";
    private String skinName = "";
    private String siteMemuId = "";

    @Override
    protected int doStartTagWp() throws JspTagException {
        int result = super.doStartTagWp();
        try {

            InfoUserMenuCacheManager cacheUserMenu = applicationContext.getBean(InfoUserMenuCacheManager.class);

            List<InfoSiteMenuVO> list =cacheUserMenu.getMenuByALlList(group);


            List<InfoSiteMenuVO> depthList = new ArrayList<InfoSiteMenuVO>();


            if(!"".equals(siteMemuId)){

                InfoSiteMenuVO  currentDeptMenu =cacheUserMenu.getMenuByCode(siteMemuId);
                depthList =cacheUserMenu.getMenuByParentId(currentDeptMenu.getParentId(),currentDeptMenu.getSiteMemuId());

            }

            pageContext.getRequest().setAttribute("group", group);
            pageContext.getRequest().setAttribute("skinName", skinName);
            pageContext.getRequest().setAttribute("userMenuList", list);
            pageContext.getRequest().setAttribute("depthList", depthList);

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
