package infomind.com.tags.tag.core;

import infomind.com.cmm.InfoConstants;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class UrlTag extends TagSupport {
    private String value ="";

    @Override
    public int doEndTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.print(buildSelect());
            return EVAL_PAGE;
        } catch (IOException e) {
            throw new JspException();
        }
    }

    private StringBuffer buildSelect() {
        StringBuffer tag = new StringBuffer();
        InfoSiteVO infoSite = (InfoSiteVO) WebUtils.getSessionAttribute((HttpServletRequest) pageContext.getRequest(), InfoConstants.SESSION_SITE_INFO_NAME);
        //tag.append(contextPath);

        tag.append(pageContext.getServletContext().getContextPath());

        if(StringUtils.isNotEmpty(infoSite.getSubPath())) {
            tag.append("/");
            tag.append(infoSite.getSubPath());

            if(value.indexOf("/") != 0) {
                tag.append("/");
            }
        }

        tag.append(value);
        return tag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
