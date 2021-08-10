package infomind.com.ext.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AxGridPageVO {
    int currentPage;
    int pageSize;
    int totalElements;
    int totalPages;
}