package infomind.com.board.tld;


import infomind.com.cms.info.banner.service.InfoBannerGroupService;
import infomind.com.cms.info.banner.service.InfoBannerService;
import infomind.com.cms.info.banner.vo.InfoBannerGroupVO;
import infomind.com.cms.info.banner.vo.InfoBannerVO;
import infomind.com.cms.info.board.service.InfoBoardItemService;
import infomind.com.cms.info.board.vo.InfoBoardItemVO;
import infomind.com.tags.tag.html.WpBaseHtmlTag;
import infomind.com.utils.web.InfoWebRequestUtils;

import javax.servlet.jsp.JspTagException;
import java.util.ArrayList;
import java.util.List;

public class InfoNoticTag extends WpBaseHtmlTag {
    private String boardId = "main";
    private String skinName = "";
    private String noticYn = "";
    private String moreLink = "N";
    private int listCount = 5;




    @Override
    protected int doStartTagWp() throws JspTagException {
        int result = super.doStartTagWp();
        try {


            String url ="";
            url ="/board/NoticLatest.do";



            InfoBoardItemService boardService = applicationContext.getBean(InfoBoardItemService.class);
            InfoBoardItemVO board = new InfoBoardItemVO();
            board.setBoardId(this.boardId);
            board.setFirstIndex(0);
            board.setRecordCountPerPage(listCount);
            board.setNoticeYn(noticYn);


            List<?> boardItems = boardService.getInfoBoardItemNoticeYnList(board);
            pageContext.getRequest().setAttribute("moreLink", moreLink);
            pageContext.getRequest().setAttribute("latestBoard", board);
            pageContext.getRequest().setAttribute("latestBoardItems", boardItems);


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

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public String getNoticYn() {
        return noticYn;
    }

    public void setNoticYn(String noticYn) {
        this.noticYn = noticYn;
    }

    public String getMoreLink() {
        return moreLink;
    }

    public void setMoreLink(String moreLink) {
        this.moreLink = moreLink;
    }

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }
}
