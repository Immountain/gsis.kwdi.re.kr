package infomind.com.cmm.ax5ui.model;

import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import lombok.Data;
import org.apache.commons.lang.StringEscapeUtils;

import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


@Data
public class Ax5ModalFormList<T> {

    private String modalId;
    private String list;

    public List<T> getList() {
        GsonBuilder gson = new GsonBuilder();
        Type type = new TypeToken<ArrayList<T>>() {}.getType();
        return gson.create().fromJson(list, type);
    }

    public void setList(String list) {
        this.list = StringEscapeUtils.unescapeHtml(list);
    }
}

