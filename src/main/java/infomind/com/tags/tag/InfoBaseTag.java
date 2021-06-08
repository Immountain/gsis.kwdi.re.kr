package infomind.com.tags.tag;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import javax.servlet.jsp.JspTagException;

public abstract class InfoBaseTag extends RequestContextAwareTag {
    protected ApplicationContext applicationContext;

    @Override
    protected int doStartTagInternal() throws Exception {


       // WebApplicationContext ctx =

        this.applicationContext = getRequestContext().getWebApplicationContext();

        if (this.applicationContext != null)
            this.applicationContext.getAutowireCapableBeanFactory().autowireBean(this);






        return doStartTagWp();
    }

    protected abstract int doStartTagWp() throws JspTagException;
}
