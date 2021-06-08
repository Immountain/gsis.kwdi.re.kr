package infomind.com.cms.info.board.vo;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class InfoBoardValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return InfoBoardVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        InfoBoardVO infoBoardVO = (InfoBoardVO) target;

        String boardId = infoBoardVO.getBoardId();
        if(boardId == null || boardId.trim().isEmpty()) {
            errors.rejectValue("boardId","","게시판 아이디 입력하세요.");
        }
        String boardNm = infoBoardVO.getBoardNm();
        if(boardNm == null || boardNm.trim().isEmpty()) {
            errors.rejectValue("boardNm","","게시판 명칭 입력하세요.");
        }

        String boardTxt = infoBoardVO.getBoardTxt();
        if(boardTxt == null || boardTxt.trim().isEmpty()) {
            errors.rejectValue("boardTxt","","게시판 내용 입력하세요.");
        }
    }


}
