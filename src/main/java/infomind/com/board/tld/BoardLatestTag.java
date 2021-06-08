package infomind.com.board.tld;

import infomind.com.cms.info.board.service.InfoBoardItemService;
import infomind.com.cms.info.board.vo.InfoBoardItemVO;
import infomind.com.tags.tag.html.WpBaseHtmlTag;
import infomind.com.utils.web.InfoWebRequestUtils;


import javax.servlet.jsp.JspTagException;
import java.util.List;

public class BoardLatestTag extends WpBaseHtmlTag {

    private String boardId;
    private int listCount = 5;
    private String skinName = "basic";
    private String moreLink = "";
    private String linkUrl = "";
    private String menuId = "";
    private String urlType = "";

    @Override
    protected int doStartTagWp() throws JspTagException {
        int result = super.doStartTagWp();

        try {


            String url ="";

            if("CMS".equals(urlType)){

                url ="/cms/info/board/latest.do";

            }else{

                url ="/board/latest.do";
            }
            InfoBoardItemService boardService = applicationContext.getBean(InfoBoardItemService.class);
            InfoBoardItemVO board = new InfoBoardItemVO();
            board.setBoardId(this.boardId);
            board.setFirstIndex(0);
            board.setRecordCountPerPage(listCount);


            List<?> boardItems = boardService.selectInfoBoardItemList(board);
            pageContext.getRequest().setAttribute("moreLink", moreLink);
            pageContext.getRequest().setAttribute("latestBoard", board);
            pageContext.getRequest().setAttribute("latestBoardItems", boardItems);
            pageContext.getRequest().setAttribute("linkUrl", linkUrl);
            pageContext.getRequest().setAttribute("menuId", menuId);

            InfoWebRequestUtils.include(pageContext, pageContext.getOut(), url+"?skinName="+this.skinName);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public String getMoreLink() {
        return moreLink;
    }

    public void setMoreLink(String moreLink) {
        this.moreLink = moreLink;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }
}
