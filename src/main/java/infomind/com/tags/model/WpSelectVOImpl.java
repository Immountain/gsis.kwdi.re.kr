package infomind.com.tags.model;

/**
 * Created by dhrod0325 on 2017-07-13.
 */
public class WpSelectVOImpl implements WpSelectVO {
    private String code = "";
    private String codeNm = "";
    private String groupLabel = "";
    private String codeMast = "";
    private String codeMastNm = "";
    private String codeDesc = "";
    private String useYn = "";
    private String codeAtr = "";
    private int sortOdr = 0;
    private String sortStr = "";
    private String regId = "";
    private String regDt = "";
    private String updId = "";
    private String updDt = "";
    private String delId = "";
    private String delDt = "";
    private boolean group = false;

    public WpSelectVOImpl() {
    }

    public WpSelectVOImpl(String code, String codeNm) {
        this.code = code;
        this.codeNm = codeNm;
    }

    public WpSelectVOImpl(String code, String codeNm, String groupLabel) {
        this.code = code;
        this.codeNm = codeNm;
        this.groupLabel = groupLabel;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getCodeNm() {
        return codeNm;
    }

    public void setCodeNm(String codeNm) {
        this.codeNm = codeNm;
    }

    @Override
    public String getGroupLabel() {
        return groupLabel;
    }

    public void setGroupLabel(String groupLabel) {
        this.groupLabel = groupLabel;
    }

    public String getCodeMast() {
        return codeMast;
    }

    public void setCodeMast(String codeMast) {
        this.codeMast = codeMast;
    }

    public String getCodeMastNm() {
        return codeMastNm;
    }

    public void setCodeMastNm(String codeMastNm) {
        this.codeMastNm = codeMastNm;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getCodeAtr() {
        return codeAtr;
    }

    public void setCodeAtr(String codeAtr) {
        this.codeAtr = codeAtr;
    }

    public int getSortOdr() {
        return sortOdr;
    }

    public void setSortOdr(int sortOdr) {
        this.sortOdr = sortOdr;
    }

    public String getSortStr() {
        return sortStr;
    }

    public void setSortStr(String sortStr) {
        this.sortStr = sortStr;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getUpdId() {
        return updId;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public String getUpdDt() {
        return updDt;
    }

    public void setUpdDt(String updDt) {
        this.updDt = updDt;
    }

    public String getDelId() {
        return delId;
    }

    public void setDelId(String delId) {
        this.delId = delId;
    }

    public String getDelDt() {
        return delDt;
    }

    public void setDelDt(String delDt) {
        this.delDt = delDt;
    }

    public boolean isGroup() {
        return group;
    }

    public void setGroup(boolean group) {
        this.group = group;
    }
}
