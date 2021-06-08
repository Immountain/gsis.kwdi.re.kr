package infomind.com.tags.tag.model;


import infomind.com.cmm.bean.InfoUserMenuCacheManager;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.tags.tag.InfoBaseTag;

import javax.servlet.jsp.JspTagException;
import java.util.ArrayList;
import java.util.List;


public class GetMenuModel extends InfoBaseTag {
    private String modelName = "";
    private String groupId = "";
    private String siteMemuId = "";
    private boolean isView = false;

    @Override
    protected int doStartTagWp() throws JspTagException {
        try {

            InfoUserMenuCacheManager cacheUserMenu = applicationContext.getBean(InfoUserMenuCacheManager.class);

            List<InfoSiteMenuVO> list = cacheUserMenu.getMenuByALlList(groupId);

            System.out.println("list====>"+list.size());

            pageContext.getRequest().setAttribute(modelName, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSiteMemuId() {
        return siteMemuId;
    }

    public void setSiteMemuId(String siteMemuId) {
        this.siteMemuId = siteMemuId;
    }

    public boolean isView() {
        return isView;
    }

    public void setView(boolean view) {
        isView = view;
    }
}
