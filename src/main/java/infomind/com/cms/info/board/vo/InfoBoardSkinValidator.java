package infomind.com.cms.info.board.vo;
import infomind.com.cms.info.popup.vo.InfoPopupManageVO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class InfoBoardSkinValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return InfoBoardSkinVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        InfoBoardSkinVO infoBoardSkinVO = (InfoBoardSkinVO) target;

        String boardSkinId = infoBoardSkinVO.getBoardSkinId();
        if(boardSkinId == null || boardSkinId.trim().isEmpty()) {
            errors.rejectValue("boardSkinId","","게시판 스킨 아이디 입력하세요.");
        }
        String boardSkinNm = infoBoardSkinVO.getBoardSkinNm();
        if(boardSkinNm == null || boardSkinNm.trim().isEmpty()) {
            errors.rejectValue("boardSkinNm","","게시판 스킨 명칭 입력하세요.");
        }
    }


}
