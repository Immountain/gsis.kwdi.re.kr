package infomind.com.tags.tag.html;

import infomind.com.tags.model.WpSelectVO;
import infomind.com.utils.date.InfoDateUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.jsp.JspTagException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dhrod0325 on 2017-07-12.
 */
public class InfoButtonTag extends WpBaseHtmlTag {

    private String type;


    public int doEndTag() throws JspTagException {
        try {


            StringBuffer tag = null;

            tag=build();
            this.pageContext.getOut().write(tag.toString());


            return 0;
        } catch (Exception var6) {
            throw new JspTagException(var6);
        }
    }


    private StringBuffer build() {
        String cssClass = "";
      // <button type="button" class="button main" onclick="gotoOrganMst()" title="기관검색">기업/기관 검색</button>

        StringBuffer tag = new StringBuffer();

        if(this.authCnt !=null){


            if(this.authCnt >0){
                tag.append("<button");
                this.appendAttr(tag, "type", this.type);
                this.appendAttr(tag, "id", this.id);
                this.appendAttr(tag, "name", this.name);
                this.appendAttr(tag, "title", this.title);
                this.appendAttr(tag, "style", this.style);
                this.appendAttr(tag, "class", cssClass + "" + StringUtils.defaultString(this.styleClass));
                tag.append(this.prepareEventHandlers());
                tag.append(">");
                tag.append(this.title);
                tag.append("</button>");
            }else{

            }


        }else{



                tag.append("<button");
                this.appendAttr(tag, "type", this.type);
                this.appendAttr(tag, "id", this.id);
                this.appendAttr(tag, "name", this.name);
                this.appendAttr(tag, "title", this.title);
                this.appendAttr(tag, "style", this.style);
                this.appendAttr(tag, "class", cssClass + "" + StringUtils.defaultString(this.styleClass));
                tag.append(this.prepareEventHandlers());
                tag.append(">");
                tag.append(this.title);
                tag.append("</button>");
        }


        return tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }







}