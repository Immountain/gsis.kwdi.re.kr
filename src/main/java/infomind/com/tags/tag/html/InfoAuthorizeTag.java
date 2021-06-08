package infomind.com.tags.tag.html;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
public class InfoAuthorizeTag extends BodyTagSupport {

    private String memuId=""; // 콤마(,)로 구분되어 있다.
    private String userId="";
    private String btnType="";

    @Override
    public int doAfterBody() throws JspException {




        return SKIP_BODY; // jsp Body에 출력 안함
    }
}