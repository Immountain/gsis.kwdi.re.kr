package infomind.com.tags.tag.html;

import com.fasterxml.jackson.databind.ObjectMapper;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.file.service.InfoFileService;
import infomind.com.file.vo.InfoFileDetailVO;

import javax.annotation.Resource;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class InfoImageSrcTag extends TagSupport {

    private String contextPath ="";
    private String imageId;
    private String title="";
    private String alt="";
    private String style="";
    private String styleClass="";
    private String width="";
    private String height="";


    @Override
    public int doEndTag() throws JspException {



        try {

            JspWriter out = pageContext.getOut();
            out.println(buildSelect());

            return EVAL_PAGE;

        } catch (IOException e) {
            throw new JspException();
        }
    }

    private StringBuffer buildSelect() {



        StringBuffer tag = new StringBuffer();
        try {


            String imgSrc =contextPath+"/site/info/file/ImageView.do?imageId="+this.imageId;
////
//            InfoFileDetailVO f = new InfoFileDetailVO();
//            f.setImageId(imageId);
//            f = infoFileService.getFileImageId(f);
//
//
//            if("".equals(this.title)){
//                this.title = f.getImageNm();
//            }else{
//
//                this.title =this.title;
//            }
//
//            if("".equals(this.alt)){
//
//                this.alt = f.getImageDc();
//            }else{
//
//                this.alt =this.alt;
//            }
//




            tag.append("<img");
            this.appendAttr(tag, "src", imgSrc);
            this.appendAttr(tag, "style", this.style);
            this.appendAttr(tag, "width", this.width);
            this.appendAttr(tag, "height", this.height);
            this.appendAttr(tag, "alt", this.alt);
            this.appendAttr(tag, "class", this.styleClass);
            this.appendAttr(tag, "title", this.title);


            tag.append("/>");


//            tag.append("<input");
//            this.appendAttrHiddn(tag, "type", type);
//            this.appendAttrHiddn(tag, "id", key);
//            this.appendAttrHiddn(tag, "name", key);
//            this.appendAttrHiddn(tag, "value", value);
//            tag.append("/>");
//            if("text".equals(type)){
//
//                tag.append("</br>");
//            }

        }catch (Exception e){

            System.out.println("e===>"+e);
        }


        return tag;
    }


    protected void appendAttr(StringBuffer sb, String name, Object value) {

        if (!"readonly".equals(name) && !"disabled".equals(name)) {
            if (value != null) {
                sb.append(" ");
                sb.append(name);
                sb.append("=\"");
                sb.append(value);
                sb.append("\"");
            }
        } else if (Boolean.TRUE.equals(value)) {
            sb.append(" ");
            sb.append(name);
            sb.append("=\"");
            sb.append(name);
            sb.append("\"");
        }

    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}