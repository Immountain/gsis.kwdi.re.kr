package infomind.com.ext.vo;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import infomind.com.cms.info.page.vo.InfoPageInfoVO;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;

public class BaseVO {




    public Object jsonToObject(String jsonString) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, this.getClass());
    }

//    public String getjsonToString() throws JsonProcessingException {
//        String val = new ObjectMapper().writeValueAsString(cls);
//        return  val;
//    }

}
