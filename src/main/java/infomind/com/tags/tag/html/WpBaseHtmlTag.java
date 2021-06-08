package infomind.com.tags.tag.html;



import infomind.com.tags.tag.InfoBaseTag;
import javax.servlet.jsp.JspTagException;

public class WpBaseHtmlTag extends InfoBaseTag {


    private static final long serialVersionUID = 1L;
    protected boolean doDisabled;
    protected boolean doReadonly;
    protected boolean disabled;
    protected boolean readonly;
    protected boolean required;
    protected String name;
    protected String property;
    protected String value;
    protected String maxlength;
    protected String minlength;
    protected String fixlength;
    protected String size;
    protected String alt;
    protected String title;
    protected String tabindex;
    protected String style;
    protected String styleClass;
    protected String imeMode;
    protected String onblur;
    protected String onfocus;
    protected String onclick;
    protected String ondblclick;
    protected String onselect;
    protected String onchange;
    protected String onmouseover;
    protected String onmouseout;
    protected String onmousemove;
    protected String onmousedown;
    protected String onmouseup;
    protected String onkeydown;
    protected String onkeyup;
    protected String onkeypress;
    protected String dataRole;
    protected String param;
    protected String p1;
    protected String p2;
    protected String p3;
    protected String p4;
    protected String p5;
    protected String p6;
    protected String p7;
    protected String p8;
    protected String p9;
    protected Integer authCnt =null;

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

    protected String prepareEventHandlers() {
        StringBuffer sb = new StringBuffer();
        this.prepareMouseEvents(sb);
        this.prepareKeyEvents(sb);
        this.prepareTextEvents(sb);
        this.prepareFocusEvents(sb);
        return sb.toString();
    }

    protected void prepareMouseEvents(StringBuffer sb) {
        this.appendAttr(sb, "onclick", this.getOnclick());
        this.appendAttr(sb, "ondblclick", this.getOndblclick());
        this.appendAttr(sb, "onmouseover", this.getOnmouseover());
        this.appendAttr(sb, "onmouseout", this.getOnmouseout());
        this.appendAttr(sb, "onmousemove", this.getOnmousemove());
        this.appendAttr(sb, "onmousedown", this.getOnmousedown());
        this.appendAttr(sb, "onmouseup", this.getOnmouseup());
    }

    protected void prepareKeyEvents(StringBuffer sb) {
        this.appendAttr(sb, "onkeydown", this.getOnkeydown());
        this.appendAttr(sb, "onkeyup", this.getOnkeyup());
        this.appendAttr(sb, "onkeypress", this.getOnkeypress());
    }

    protected void prepareTextEvents(StringBuffer sb) {
        this.appendAttr(sb, "onselect", this.getOnselect());
        this.appendAttr(sb, "onchange", this.getOnchange());
    }

    protected void prepareFocusEvents(StringBuffer sb) {
        this.appendAttr(sb, "onblur", this.getOnblur());
        this.appendAttr(sb, "onfocus", this.getOnfocus());
        if (this.doDisabled && this.disabled) {
            sb.append(" disabled=\"disabled\"");
        }

        if (this.doReadonly && this.readonly) {
            sb.append(" readonly=\"readonly\"");
        }

    }

    protected String getBoundValue() {
        String val = this.value;
        if (val == null) {
            val = "";
        } else {
            val = val.replaceAll("'", "&#039;");
            val = val.replaceAll("\"", "&#034;");
        }

        return val;
    }

    protected boolean isCheckValue() {
        boolean isCheck = false;
        if (this.value != null && "Y".equals(this.value.toUpperCase())) {
            isCheck = true;
        }

        return isCheck;
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isReadonly() {
        return this.readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public boolean isRequired() {
        return this.required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty() {
        return this.property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMaxlength() {
        return this.maxlength;
    }

    public void setMaxlength(String maxlength) {
        this.maxlength = maxlength;
    }

    public String getMinlength() {
        return this.minlength;
    }

    public void setMinlength(String minlength) {
        this.minlength = minlength;
    }

    public String getFixlength() {
        return this.fixlength;
    }

    public void setFixlength(String fixlength) {
        this.fixlength = fixlength;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAlt() {
        return this.alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTabindex() {
        return this.tabindex;
    }

    public void setTabindex(String tabindex) {
        this.tabindex = tabindex;
    }

    public String getStyle() {
        return this.style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleClass() {
        return this.styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getImeMode() {
        return this.imeMode;
    }

    public void setImeMode(String imeMode) {
        this.imeMode = imeMode;
    }

    public String getOnblur() {
        return this.onblur;
    }

    public void setOnblur(String onblur) {
        this.onblur = onblur;
    }

    public String getOnfocus() {
        return this.onfocus;
    }

    public void setOnfocus(String onfocus) {
        this.onfocus = onfocus;
    }

    public String getOnclick() {
        return this.onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public String getOndblclick() {
        return this.ondblclick;
    }

    public void setOndblclick(String ondblclick) {
        this.ondblclick = ondblclick;
    }

    public String getOnselect() {
        return this.onselect;
    }

    public void setOnselect(String onselect) {
        this.onselect = onselect;
    }

    public String getOnchange() {
        return this.onchange;
    }

    public void setOnchange(String onchange) {
        this.onchange = onchange;
    }

    public String getOnmouseover() {
        return this.onmouseover;
    }

    public void setOnmouseover(String onmouseover) {
        this.onmouseover = onmouseover;
    }

    public String getOnmouseout() {
        return this.onmouseout;
    }

    public void setOnmouseout(String onmouseout) {
        this.onmouseout = onmouseout;
    }

    public String getOnmousemove() {
        return this.onmousemove;
    }

    public void setOnmousemove(String onmousemove) {
        this.onmousemove = onmousemove;
    }

    public String getOnmousedown() {
        return this.onmousedown;
    }

    public void setOnmousedown(String onmousedown) {
        this.onmousedown = onmousedown;
    }

    public String getOnmouseup() {
        return this.onmouseup;
    }

    public void setOnmouseup(String onmouseup) {
        this.onmouseup = onmouseup;
    }

    public String getOnkeydown() {
        return this.onkeydown;
    }

    public void setOnkeydown(String onkeydown) {
        this.onkeydown = onkeydown;
    }

    public String getOnkeyup() {
        return this.onkeyup;
    }

    public void setOnkeyup(String onkeyup) {
        this.onkeyup = onkeyup;
    }

    public String getOnkeypress() {
        return this.onkeypress;
    }

    public void setOnkeypress(String onkeypress) {
        this.onkeypress = onkeypress;
    }

    public String getDataRole() {
        return this.dataRole;
    }

    public void setDataRole(String dataRole) {
        this.dataRole = dataRole;
    }

    public String getParam() {
        return this.param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getP1() {
        return this.p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return this.p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return this.p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public String getP4() {
        return this.p4;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }

    public String getP5() {
        return this.p5;
    }

    public void setP5(String p5) {
        this.p5 = p5;
    }

    public String getP6() {
        return this.p6;
    }

    public void setP6(String p6) {
        this.p6 = p6;
    }

    public String getP7() {
        return this.p7;
    }

    public void setP7(String p7) {
        this.p7 = p7;
    }

    public String getP8() {
        return this.p8;
    }

    public void setP8(String p8) {
        this.p8 = p8;
    }

    public String getP9() {
        return this.p9;
    }

    public void setP9(String p9) {
        this.p9 = p9;
    }


    public Integer getAuthCnt() {
        return authCnt;
    }

    public void setAuthCnt(Integer authCnt) {
        this.authCnt = authCnt;
    }

    @Override
    protected int doStartTagWp() throws JspTagException {
        return 0;
    }
}
