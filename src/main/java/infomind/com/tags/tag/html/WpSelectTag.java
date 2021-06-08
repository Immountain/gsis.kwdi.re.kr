package infomind.com.tags.tag.html;


import infomind.com.tags.model.WpSelectVO;
import org.apache.commons.lang.StringUtils;

import javax.servlet.jsp.JspTagException;
import java.util.List;

public class WpSelectTag extends WpBaseHtmlTag {
    private static final long serialVersionUID = 1L;
    private String type;
    private String mode;
    private String first;
    private String cid;
    private String sid;
    private String val;
    private String width;
    private String height;
    private boolean showUp;
    private boolean header;
    private boolean filter;
    private boolean group;
    private boolean groupLabel = true;
    private boolean multi;
    private int max = 0;
    private int view = 1;
    private List<WpSelectVO> list;

    @Override
    protected int doStartTagWp() throws JspTagException {
        try {
            StringBuffer tag = this.buildSelect(list);
            this.pageContext.getOut().write(tag.toString());

            return 0;
        } catch (Exception var6) {
            throw new JspTagException(var6);
        }
    }

    private StringBuffer buildSelect(List<WpSelectVO> list) {
        String cssClass = "";
        if (this.required) {
            cssClass = "req_slt";
        }

        StringBuffer tag = new StringBuffer();
        tag.append("<select");
        this.appendAttr(tag, "id", this.id);
        this.appendAttr(tag, "name", this.name);
        this.appendAttr(tag, "val", this.val);
        if ("layer".equals(this.mode)) {
            this.appendAttr(tag, "mode", this.mode);
            this.appendAttr(tag, "group", this.group);
            this.appendAttr(tag, "groupLabel", this.groupLabel);
            this.appendAttr(tag, "multi", this.multi);
            this.appendAttr(tag, "showUp", this.showUp);
            if (this.multi) {
                this.appendAttr(tag, "header", this.header);
                this.appendAttr(tag, "filter", this.filter);
                this.appendAttr(tag, "max", this.max);
                this.appendAttr(tag, "view", this.view);
            }

            this.appendAttr(tag, "width", this.width);
            this.appendAttr(tag, "height", this.height);
        }

        this.appendAttr(tag, "tabindex", this.tabindex);
        this.appendAttr(tag, "style", this.style);
        this.appendAttr(tag, "class", cssClass + " " + StringUtils.defaultString(this.styleClass));
        this.appendAttr(tag, "title", this.title);
        this.appendAttr(tag, "require", this.required);


//        System.out.println("this.authCnt===>"+this.authCnt);
//        System.out.println("this.disabled===>"+this.disabled);

        //권한에 따라
        if(this.authCnt !=null){
            if(this.authCnt >0){
                this.appendAttr(tag, "disabled", false);
            }else{
                this.appendAttr(tag, "disabled", true);
            }
        }else{

            this.appendAttr(tag, "disabled", this.disabled);

        }





        this.appendAttr(tag, "data-role", this.dataRole);
        tag.append(this.prepareEventHandlers());
        tag.append(">");
        if (this.multi && this.first == null) {
            this.first = "";
        }

        if (this.first != null) {
            tag.append("<option value=\"\">");
            tag.append(this.first);
            tag.append("</option>");
        }

        WpSelectVO codeVO;
        String preGroupLabel = null;
        String curGroupLabel;

        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); ++i) {
                codeVO = list.get(i);
                curGroupLabel = StringUtils.defaultString(codeVO.getGroupLabel());
                if (this.group && !curGroupLabel.equals(preGroupLabel)) {
                    if (preGroupLabel != null) {
                        tag.append("</optgroup>");
                    }

                    tag.append("<optgroup label=\"" + curGroupLabel + "\">");
                }

                tag.append("<option");
                this.appendAttr(tag, "value", codeVO.getCode());
                if (this.getBoundValue() != null && this.getBoundValue().equals(codeVO.getCode())) {
                    tag.append(" selected");
                }

                this.appendAttr(tag, "title", codeVO.getCodeNm());
                tag.append(">");
                tag.append(codeVO.getCodeNm());
                tag.append("</option>");
                if (this.group && i == list.size() - 1) {
                    tag.append("</optgroup>");
                }

                preGroupLabel = curGroupLabel;
            }
        }

        tag.append("</select>");
        return tag;
    }

    public List<WpSelectVO> getList() {
        return list;
    }

    public void setList(List<WpSelectVO> list) {
        this.list = list;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
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

    public boolean isShowUp() {
        return showUp;
    }

    public void setShowUp(boolean showUp) {
        this.showUp = showUp;
    }

    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }

    public boolean isFilter() {
        return filter;
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    public boolean isGroup() {
        return group;
    }

    public void setGroup(boolean group) {
        this.group = group;
    }

    public boolean isGroupLabel() {
        return groupLabel;
    }

    public void setGroupLabel(boolean groupLabel) {
        this.groupLabel = groupLabel;
    }

    public boolean isMulti() {
        return multi;
    }

    public void setMulti(boolean multi) {
        this.multi = multi;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }
}
