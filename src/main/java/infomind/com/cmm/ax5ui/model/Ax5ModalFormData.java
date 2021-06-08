package infomind.com.cmm.ax5ui.model;

import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import lombok.Data;
import org.apache.commons.lang.StringEscapeUtils;

import java.lang.reflect.Type;


@Data
public class Ax5ModalFormData<T> {

    private String modalId;
    private String data;

    public T getData() {
        GsonBuilder gson = new GsonBuilder();
        Type type = new TypeToken<T>() {}.getType();
        return gson.create().fromJson(data, type);
    }

    public void setData(String data) {
        this.data = StringEscapeUtils.unescapeHtml(data);
    }
}
