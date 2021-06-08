package infomind.com.cms.info.sample.vo;

import infomind.com.utils.poi.ss.model.annotations.Sheet;
import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;

@Sheet
@Data
public class SampleExcelConverVO {

    @SheetColumn("이름")
    private String name;

    @SheetColumn("나이")
    private Integer age;

    @SheetColumn("전화번호")
    private String cellNumber;

    @SheetColumn("임시1")
    private String temp01;

    @SheetColumn("임시2")
    private String temp02;

    @SheetColumn("임시3")
    private String temp03;

    @SheetColumn("임시4")
    private String temp04;

    @SheetColumn("임시5")
    private String temp05;

    // 미변환 항목
    private String temp11;
    private String temp12;
    private String temp13;
    private String temp14;
}
