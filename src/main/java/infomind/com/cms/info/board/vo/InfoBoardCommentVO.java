package infomind.com.cms.info.board.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InfoBoardCommentVO {


    private String infoCommentSno ="";
    private String commentType="";
    private String itemId="";
    private String idCode="";
    private String parentId="";
    private String title="";
    private String txtContent="";
    private String password="";
    private String commentFile="";
    private String score="";
    private String secretYn="";
    private String useYn="";
    private String deleteYn="";
    private String deleteType="";
    private String regId="";
    private String regDt="";
    private String regIp="";

    private String modId="";
    private String modDt="";
    private String modIp="";


    private String  regDtYyyy ="";
    private String  regDtMm="";
    private String  regDtDd="";


    public String getInfoCommentSno() {
        return infoCommentSno;
    }

    public void setInfoCommentSno(String infoCommentSno) {
        this.infoCommentSno = infoCommentSno;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTxtContent() {
        return txtContent;
    }

    public void setTxtContent(String txtContent) {
        this.txtContent = txtContent;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCommentFile() {
        return commentFile;
    }

    public void setCommentFile(String commentFile) {
        this.commentFile = commentFile;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSecretYn() {
        return secretYn;
    }

    public void setSecretYn(String secretYn) {
        this.secretYn = secretYn;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getDeleteYn() {
        return deleteYn;
    }

    public void setDeleteYn(String deleteYn) {
        this.deleteYn = deleteYn;
    }

    public String getDeleteType() {
        return deleteType;
    }

    public void setDeleteType(String deleteType) {
        this.deleteType = deleteType;
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

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public String getModDt() {
        return modDt;
    }

    public void setModDt(String modDt) {
        this.modDt = modDt;
    }

    public String getModIp() {
        return modIp;
    }

    public void setModIp(String modIp) {
        this.modIp = modIp;
    }

    public String getRegDtYyyy() {
        return regDtYyyy;
    }

    public void setRegDtYyyy(String regDtYyyy) {
        this.regDtYyyy = regDtYyyy;
    }

    public String getRegDtMm() {
        return regDtMm;
    }

    public void setRegDtMm(String regDtMm) {
        this.regDtMm = regDtMm;
    }

    public String getRegDtDd() {
        return regDtDd;
    }

    public void setRegDtDd(String regDtDd) {
        this.regDtDd = regDtDd;
    }
}
