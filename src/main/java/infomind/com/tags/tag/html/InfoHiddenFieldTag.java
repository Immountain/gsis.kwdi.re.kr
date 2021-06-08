package infomind.com.tags.tag.html;

import com.fasterxml.jackson.databind.ObjectMapper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationManager;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationManager;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;
import infomind.com.ext.vo.CmsHiddenSearchVO;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.tags.model.WpSelectVO;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InfoHiddenFieldTag extends TagSupport {

    private Object searchHiddenField;
    private String type="hidden";


    @Override
    public int doEndTag() throws JspException {



        try {

            JspWriter out = pageContext.getOut();
            out.println(buildSelect(searchHiddenField));

            return EVAL_PAGE;

        } catch (IOException e) {
            throw new JspException();
        }
    }

    private StringBuffer buildSelect(Object obj) {


        //선택
        CmsSearchVO vo =(CmsSearchVO)obj;
        ObjectMapper objectMapper = new ObjectMapper();


        CmsHiddenSearchVO cmsHiddenSearchVO = new CmsHiddenSearchVO(vo);


        Map result = objectMapper.convertValue(cmsHiddenSearchVO, Map.class);
        System.out.println("result2==>"+result);



        StringBuffer tag = new StringBuffer();
        try {

            Iterator<String> iter = result.keySet().iterator();
            while(iter.hasNext()) {
                String key = iter.next();
                String value = String.valueOf(result.get(key));
                if(!"".equals(value) && !"null".equals(value)) {

                    if(!"contentInfo".equals(key)){

                        if("text".equals(type)){

                            tag.append(key+"=");
                        }

                        tag.append("<input");
                        this.appendAttrHiddn(tag, "type", type);
                        this.appendAttrHiddn(tag, "id", key);
                        this.appendAttrHiddn(tag, "name", key);
                        this.appendAttrHiddn(tag, "value", value);
                        tag.append("/>");
                        if("text".equals(type)){

                            tag.append("</br>");
                        }



                    }

                }


//                System.out.println(key + " : " + value);

            }

        }catch (Exception e){

            System.out.println("e===>"+e);
        }


        return tag;
    }


    protected void appendAttrHiddn(StringBuffer sb, String name, Object value) {

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

    public Object getSearchHiddenField() {
        return searchHiddenField;
    }

    public void setSearchHiddenField(Object searchHiddenField) {
        this.searchHiddenField = searchHiddenField;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}