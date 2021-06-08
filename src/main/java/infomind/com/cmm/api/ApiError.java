package infomind.com.cmm.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString
public class ApiError {

    @JsonProperty("message")
    @NonNull
    private String message;
    @JsonProperty("requiredKey")
    private String requiredKey;


    public void setMessage(@NonNull String message) {
        if (message == null) {
            throw new NullPointerException("message");
        } else {
            this.message = message;
        }
    }

    public void setRequiredKey(String requiredKey) {
        this.requiredKey = requiredKey;
    }

    @NonNull
    public String getMessage() {
        return this.message;
    }

    public String getRequiredKey() {
        return this.requiredKey;
    }

    public ApiError() {
    }

    private ApiError(@NonNull String message) {
        if (message == null) {
            throw new NullPointerException("message");
        } else {
            this.message = message;
        }
    }

    public static ApiError of(@NonNull String message) {
        return new ApiError(message);
    }



}
