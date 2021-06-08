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
public class WpInputTag extends WpBaseHtmlTag {
    private static final long serialVersionUID = 1L;
    private String type;
    private String edittype;
    private Date dateval;
    private String fmt;
    private String first;
    private String cid;
    private String sid;
    private String label;
    private String name2;
    private String value2;
    private String style2;
    private boolean show2 = true;
    private boolean checked;
    private boolean showBtn = true;
    private boolean neg = false;
    private int stepHour = 1;
    private int stepMinute = 1;
    private int stepSecond = 1;
    private String dtpCollapse;
    private String dtpOrientation;
    private String onEnterPress;
    private int cbDataStep = 1;
    private String cbDataStepText;

    private List<WpSelectVO> list;

    public int doEndTag() throws JspTagException {
        try {
            StringBuffer tag = null;

            if ("search".equalsIgnoreCase(this.type)) {
                this.type = "text";
                tag = this.buildSearch(true);
            } else if ("search2".equalsIgnoreCase(this.type)) {
                this.type = "text";
                tag = this.buildSearch2(true);
            } else if ("psearch".equalsIgnoreCase(this.type)) {
                this.type = "text";
                tag = this.buildPSearch();
            } else if (!"checkbox".equalsIgnoreCase(this.type) && !"radio".equalsIgnoreCase(this.type)) {
                if ("hidden".equalsIgnoreCase(this.type)) {
                    tag = this.buildHidden();
                } else if (!StringUtils.defaultString(this.edittype).contains("date") && !StringUtils.defaultString(this.edittype).contains("time")) {
                    if ("search".equalsIgnoreCase(this.edittype)) {
                        tag = this.buildSearch(false);
                    } else if ("search2".equalsIgnoreCase(this.edittype)) {
                        tag = this.buildSearch2(false);
                    } else if ("psearch".equalsIgnoreCase(this.edittype)) {
                        tag = this.buildPSearch();
                    } else {
                        tag = this.buildText();
                    }
                } else {
                    tag = this.buildDate(null);
                }
            } else {
                tag = this.buildCheck(list);
            }

            assert tag != null;

            this.pageContext.getOut().write(tag.toString());

            return 0;
        } catch (Exception var6) {
            throw new JspTagException(var6);
        }
    }

    private StringBuffer buildCheck(List<WpSelectVO> list) {
        StringBuffer tag = new StringBuffer();

        if (list == null || list.isEmpty()) {
            return tag;
        }

        int cnt = 0;

        if (this.first != null) {
            tag.append("<input");
            this.appendAttr(tag, "type", this.type);
            this.appendAttr(tag, "id", this.name + "_" + cnt);
            this.appendAttr(tag, "name", this.name);
            this.appendAttr(tag, "value", "");
            this.appendAttr(tag, "disabled", this.disabled);
            this.appendAttr(tag, "style", this.style);
            this.appendAttr(tag, "class", "iCheck " + StringUtils.defaultString(this.styleClass));
            this.appendAttr(tag, "data-role", this.dataRole);
            if (!this.readonly && !this.disabled) {
                tag.append(this.prepareEventHandlers());
            }

            if (this.readonly) {
                this.appendAttr(tag, "onclick", "return false;");
            }

            if (this.getBoundValue() != null && this.getBoundValue().equals("")) {
                this.appendAttr(tag, "checked", "checked");
            }

            if (this.title != null) {
                this.appendAttr(tag, "title", this.title);
            }

            this.appendAttr(tag, "data-step", Integer.valueOf(this.cbDataStep));
            if (this.cbDataStepText != null) {
                this.appendAttr(tag, "data-stepText", this.cbDataStepText);
            }

            tag.append(" />");
            tag.append("<label");
            this.appendAttr(tag, "for", this.name + "_" + cnt);
            this.appendAttr(tag, "class", "label_check");
            tag.append(">");
            tag.append(this.first);
            tag.append("</label>");
            ++cnt;
        }

        for (Iterator i$ = list.iterator(); i$.hasNext(); ++cnt) {
            WpSelectVO codeVO = (WpSelectVO) i$.next();
            if (cnt > 0 && cnt % this.cbDataStep == 0 && this.cbDataStepText != null) {
                tag.append(this.cbDataStepText);
            }

            tag.append("<input");
            this.appendAttr(tag, "type", this.type);
            this.appendAttr(tag, "id", this.name + "_" + cnt);
            this.appendAttr(tag, "name", this.name);
            this.appendAttr(tag, "value", codeVO.getCode());
            this.appendAttr(tag, "tabindex", this.tabindex);
            this.appendAttr(tag, "disabled", this.disabled);
            this.appendAttr(tag, "style", this.style);
            this.appendAttr(tag, "class", "iCheck " + StringUtils.defaultString(this.styleClass));
            this.appendAttr(tag, "data-role", this.dataRole);
            if (!this.readonly && !this.disabled) {
                tag.append(this.prepareEventHandlers());
            }

            if (this.readonly) {
                this.appendAttr(tag, "onclick", "return false;");
            }

            if (this.getBoundValue() != null && this.getBoundValue().equals(codeVO.getCode())) {
                this.appendAttr(tag, "checked", "checked");
            }

            if (this.title != null) {
                this.appendAttr(tag, "title", this.title);
            }

            this.appendAttr(tag, "data-step", this.cbDataStep);
            if (this.cbDataStepText != null) {
                this.appendAttr(tag, "data-stepText", this.cbDataStepText);
            }

            tag.append(" />");
            tag.append("<label");
            this.appendAttr(tag, "for", this.name + "_" + cnt);
            this.appendAttr(tag, "class", "label_check");
            tag.append(">");
            tag.append(codeVO.getCodeNm());
            tag.append("</label>");
        }

        return tag;
    }

    public void release() {
        super.release();
    }

    private StringBuffer buildText() {
        String cssClass = "iText";
        if (this.readonly) {
            cssClass = cssClass + " inp_read";
        }

        if (this.required) {
            cssClass = cssClass + " inp_reqr";
        }

        StringBuffer tag = new StringBuffer();
        tag.append("<input");
        this.appendAttr(tag, "type", this.type);
        this.appendAttr(tag, "id", this.id);
        this.appendAttr(tag, "name", this.name);
        this.appendAttr(tag, "title", this.title);
        this.appendAttr(tag, "size", this.size);
        this.appendAttr(tag, "maxlength", this.maxlength);
        this.appendAttr(tag, "minlength", this.minlength);
        this.appendAttr(tag, "fixlength", this.fixlength);
        this.appendAttr(tag, "tabindex", this.tabindex);
        this.appendAttr(tag, "readonly", this.readonly);
        this.appendAttr(tag, "disabled", this.disabled);
        this.appendAttr(tag, "class", cssClass + " " + StringUtils.defaultString(this.styleClass));
        this.appendAttr(tag, "style", this.style);
        this.appendAttr(tag, "imeMode", this.imeMode);
        this.appendAttr(tag, "edittype", this.edittype);
        this.appendAttr(tag, "fmt", this.fmt);
        this.appendAttr(tag, "neg", this.neg);
        this.appendAttr(tag, "value", this.getBoundValue());
        this.appendAttr(tag, "require", this.required);
        this.appendAttr(tag, "data-role", this.dataRole);
        if (!this.readonly && !this.disabled) {
            tag.append(this.prepareEventHandlers());
        }

        tag.append(">");
        return tag;
    }

    private StringBuffer buildHidden() {
        StringBuffer tag = new StringBuffer();
        tag.append("<input");
        this.appendAttr(tag, "type", "hidden");
        this.appendAttr(tag, "id", this.id);
        this.appendAttr(tag, "name", this.name);
        this.appendAttr(tag, "title", this.title);
        this.appendAttr(tag, "edittype", this.edittype);
        this.appendAttr(tag, "fmt", this.fmt);
        this.appendAttr(tag, "value", this.getBoundValue());
        this.appendAttr(tag, "require", this.required);
        tag.append(">");
        return tag;
    }

    private StringBuffer buildSearch(boolean isType) {
        StringBuffer tag = new StringBuffer();
        tag.append(this.buildText());
        if (isType) {
            tag.append("<img");
            this.appendAttr(tag, "src", this.pageContext.getServletContext().getContextPath() + "/common/images/btn_search.gif");
            this.appendAttr(tag, "id", "btn_" + this.name);
            this.appendAttr(tag, "class", "btn_textsearch");
            if (this.disabled) {
                this.appendAttr(tag, "style", "cursor:default; filter:progid:DXImageTransform.Microsoft.BasicImage(grayScale=1);");
            } else {
                this.appendAttr(tag, "style", "cursor:pointer;");
                tag.append(this.prepareEventHandlers());
            }

            tag.append(">");
        } else {
            tag.append("<input");
            this.appendAttr(tag, "type", "button");
            this.appendAttr(tag, "id", "btn_" + this.name);
            this.appendAttr(tag, "class", "btn_textsearch");
            this.appendAttr(tag, "data-role", this.dataRole);
            if (this.disabled) {
                this.appendAttr(tag, "style", "cursor:default; filter:progid:DXImageTransform.Microsoft.BasicImage(grayScale=1);");
            } else {
                this.appendAttr(tag, "style", "cursor:pointer;");
                tag.append(this.prepareEventHandlers());
            }
        }

        return tag;
    }

    private StringBuffer buildSearch2(boolean isType) {
        StringBuffer tag = new StringBuffer();
        tag.append("<input");
        this.appendAttr(tag, "type", "text");
        this.appendAttr(tag, "name", this.name2);
        this.appendAttr(tag, "value", this.value2);
        if (this.show2) {
            this.appendAttr(tag, "size", "10");
            this.appendAttr(tag, "class", "iText inp_code");
            this.appendAttr(tag, "style", this.style2);
            this.appendAttr(tag, "readonly", Boolean.TRUE);
            this.appendAttr(tag, "onfocus", "this.blur();");
        } else {
            this.appendAttr(tag, "style", "display:none;");
        }

        tag.append(">");
        tag.append(this.buildSearch(isType));
        return tag;
    }

    private StringBuffer buildPSearch() {
        StringBuffer tag = new StringBuffer();
        tag.append(this.buildDate("st"));
        tag.append(" ~ ");
        tag.append(this.buildDate("ed"));
        return tag;
    }

    private StringBuffer buildDate(String prefix) {
        String format;
        String cssClass = "iText";
        if ("time".equals(this.edittype)) {
            format = "HH:mm:ss";
            cssClass = cssClass + " time_01";
        } else if ("time_hm".equals(this.edittype)) {
            format = "HH:mm";
            cssClass = cssClass + " time_02";
        } else if ("time_ms".equals(this.edittype)) {
            format = "mm:ss";
            cssClass = cssClass + " time_02";
        } else if (!"date_y".equals(this.edittype) && !"date.y".equals(this.edittype)) {
            if (!"date_m".equals(this.edittype) && !"date.m".equals(this.edittype)) {
                if (!"date_d".equals(this.edittype) && !"date.d".equals(this.edittype)) {
                    if (!"date_ym".equals(this.edittype) && !"date.ym".equals(this.edittype)) {
                        if (!"date_md".equals(this.edittype) && !"date.md".equals(this.edittype)) {
                            if (!"date_h".equals(this.edittype) && !"date.h".equals(this.edittype)) {
                                if (!"date_hm".equals(this.edittype) && !"date.hm".equals(this.edittype)) {
                                    if (!"date_hms".equals(this.edittype) && !"date.hms".equals(this.edittype)) {
                                        if (this.edittype.contains(".")) {
                                            format = "yyyy.MM.dd";
                                        } else {
                                            format = "yyyy-MM-dd";
                                        }

                                        cssClass = cssClass + " date_01";
                                    } else {
                                        if (this.edittype.contains(".")) {
                                            format = "yyyy.MM.dd HH:mm:ss";
                                        } else {
                                            format = "yyyy-MM-dd HH:mm:ss";
                                        }

                                        cssClass = cssClass + " date_04";
                                    }
                                } else {
                                    if (this.edittype.contains(".")) {
                                        format = "yyyy.MM.dd HH:mm";
                                    } else {
                                        format = "yyyy-MM-dd HH:mm";
                                    }

                                    cssClass = cssClass + " date_03";
                                }
                            } else {
                                if (this.edittype.contains(".")) {
                                    format = "yyyy.MM.dd HH";
                                } else {
                                    format = "yyyy-MM-dd HH";
                                }

                                cssClass = cssClass + " date_02";
                            }
                        } else {
                            if (this.edittype.contains(".")) {
                                format = "MM.dd";
                            } else {
                                format = "MM-dd";
                            }

                            cssClass = cssClass + " date_01";
                        }
                    } else {
                        if (this.edittype.contains(".")) {
                            format = "yyyy.MM";
                        } else {
                            format = "yyyy-MM";
                        }

                        cssClass = cssClass + " date_01";
                    }
                } else {
                    format = "dd";
                    cssClass = cssClass + " date_01";
                }
            } else {
                format = "MM";
                cssClass = cssClass + " date_01";
            }
        } else {
            format = "yyyy";
            cssClass = cssClass + " date_01";
        }

        if (this.dateval != null) {
            this.value = InfoDateUtils.convertToString(this.dateval, format);
        }

        if (this.readonly) {
            cssClass = cssClass + " inp_read";
        }

        if (this.required) {
            cssClass = cssClass + " inp_reqr";
        }

        String reName = this.name;
        if (prefix != null) {
            reName = this.prefixName(this.name, prefix);
        }

        StringBuffer tag = new StringBuffer();
        tag.append("<input");
        this.appendAttr(tag, "type", "text");
        this.appendAttr(tag, "id", this.id);
        this.appendAttr(tag, "name", reName);
        this.appendAttr(tag, "title", this.title);
        this.appendAttr(tag, "size", this.size);
        this.appendAttr(tag, "tabindex", this.tabindex);
        this.appendAttr(tag, "readonly", this.readonly);
        this.appendAttr(tag, "disabled", this.disabled);
        this.appendAttr(tag, "style", this.style);
        this.appendAttr(tag, "class", cssClass + " " + StringUtils.defaultString(this.styleClass));
        this.appendAttr(tag, "showBtn", this.showBtn);
        this.appendAttr(tag, "edittype", this.edittype);
        this.appendAttr(tag, "fmt", this.fmt);
        if (this.edittype.endsWith("h") || this.edittype.endsWith("hm") || this.edittype.endsWith("hms")) {
            this.appendAttr(tag, "stepHour", this.stepHour);
            this.appendAttr(tag, "stepMinute", this.stepMinute);
            this.appendAttr(tag, "stepSecond", this.stepSecond);
        }

        this.appendAttr(tag, "dtpCollapse", this.dtpCollapse);
        this.appendAttr(tag, "dtpOrientation", this.dtpOrientation);
        this.appendAttr(tag, "value", this.getBoundValue());
        this.appendAttr(tag, "require", this.required);
        this.appendAttr(tag, "data-role", this.dataRole);
        if (!this.readonly && !this.disabled) {
            tag.append(this.prepareEventHandlers());
        }

        tag.append(">");
        return tag;
    }

    private String prefixName(String name, String prefix) {
        return name == null ? null : prefix + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    protected String getPressEnterKey() {
        if (StringUtils.isEmpty(this.onEnterPress)) {
            return null;
        } else {
            StringBuffer sb = new StringBuffer();
            if (!this.readonly || !this.disabled) {
                sb.append("if (window.event.keyCode != 13){return;}");
                sb.append("isPressEnterKey");
                sb.append("(");
                sb.append("this");
                sb.append(",");
                sb.append("'");
                sb.append(this.onEnterPress.replace("'", "\\'").replace("\"", "\\\""));
                sb.append("');");
            }

            return sb.toString();
        }
    }

    protected void prepareKeyEvents(StringBuffer sb) {
        this.appendAttr(sb, "onkeydown", this.onkeydown);
        this.appendAttr(sb, "onkeyup", this.onkeyup);
        if ((!this.doReadonly || !this.readonly) && (!this.doDisabled || !this.disabled)) {
            if (this.onkeypress == null) {
                this.appendAttr(sb, "onkeypress", this.getPressEnterKey());
            } else {
                this.appendAttr(sb, "onkeypress", this.onkeypress.concat(";").concat(this.getPressEnterKey()));
            }
        } else {
            this.appendAttr(sb, "onkeypress", this.onkeypress);
        }

    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEdittype() {
        return this.edittype;
    }

    public void setEdittype(String edittype) {
        this.edittype = edittype;
    }

    public Date getDateval() {
        return this.dateval;
    }

    public void setDateval(Date dateval) {
        this.dateval = dateval;
    }

    public String getFmt() {
        return this.fmt;
    }

    public void setFmt(String fmt) {
        this.fmt = fmt;
    }

    public String getFirst() {
        return this.first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSid() {
        return this.sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName2() {
        return this.name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getValue2() {
        return this.value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getStyle2() {
        return this.style2;
    }

    public void setStyle2(String style2) {
        this.style2 = style2;
    }

    public boolean isShow2() {
        return this.show2;
    }

    public void setShow2(boolean show2) {
        this.show2 = show2;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isShowBtn() {
        return this.showBtn;
    }

    public void setShowBtn(boolean showBtn) {
        this.showBtn = showBtn;
    }

    public boolean isNeg() {
        return this.neg;
    }

    public void setNeg(boolean neg) {
        this.neg = neg;
    }

    public int getStepHour() {
        return this.stepHour;
    }

    public void setStepHour(int stepHour) {
        this.stepHour = stepHour;
    }

    public int getStepMinute() {
        return this.stepMinute;
    }

    public void setStepMinute(int stepMinute) {
        this.stepMinute = stepMinute;
    }

    public int getStepSecond() {
        return this.stepSecond;
    }

    public void setStepSecond(int stepSecond) {
        this.stepSecond = stepSecond;
    }

    public String getDtpCollapse() {
        return this.dtpCollapse;
    }

    public void setDtpCollapse(String dtpCollapse) {
        this.dtpCollapse = dtpCollapse;
    }

    public String getDtpOrientation() {
        return this.dtpOrientation;
    }

    public void setDtpOrientation(String dtpOrientation) {
        this.dtpOrientation = dtpOrientation;
    }

    public String getOnEnterPress() {
        return this.onEnterPress;
    }

    public void setOnEnterPress(String onEnterPress) {
        this.onEnterPress = onEnterPress;
    }

    public int getCbDataStep() {
        return this.cbDataStep;
    }

    public void setCbDataStep(int cbDataStep) {
        this.cbDataStep = cbDataStep;
    }

    public String getCbDataStepText() {
        return this.cbDataStepText;
    }

    public void setCbDataStepText(String cbDataStepText) {
        this.cbDataStepText = cbDataStepText;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public List<WpSelectVO> getList() {
        return list;
    }

    public void setList(List<WpSelectVO> list) {
        this.list = list;
    }
}