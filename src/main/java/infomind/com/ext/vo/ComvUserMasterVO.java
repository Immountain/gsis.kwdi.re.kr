package infomind.com.ext.vo;


import lombok.Data;

@Data
public class ComvUserMasterVO extends CmsSearchVO {
    private String esntlId;
    private String userId;
    private String Password;
    private String userNm;
    private String userZip;
    private String userAdres;
    private String userEmail;
    private String groupId;
    private String userSe;
    private String orgnztId;
}
