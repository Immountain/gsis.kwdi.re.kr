package gsis.com.cms.api.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HeadersResponse {

    @JsonProperty("Accept-Language")
    private String acceptLanguage;

    @JsonProperty("Host")
    private String host;

    @JsonProperty("User-Agent")
    private String userAgent;

    @JsonProperty("Accept")
    private String accept;
}
