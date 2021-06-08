package infomind.com.utils.web;

import com.google.gson.JsonParser;
import infomind.com.utils.http.InfoHttpUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;


public class InfoReadableRequestWrapper extends HttpServletRequestWrapper {

    private static final Logger LOG = LoggerFactory.getLogger(InfoReadableRequestWrapper.class);
    private Map<String, String[]> params = new HashMap<String, String[]>();


    public InfoReadableRequestWrapper(HttpServletRequest request) {
        super(request);
        this.params.putAll(request.getParameterMap()); // Body String을 가져옴


        System.out.println("InfoReadableRequestWrapper 읽기");

        Map<String, String[]> params = request.getParameterMap();


//        if(params.size()>0){
//
//            for (String key : params.keySet()) {
//                String[] values = params.get(key);
//                for (int i = 0; i < values.length; i++) {
//
//                    System.out.println("values--->"+values[i]);
//                    System.out.println("key--->"+key);
//
//                    setParameter(key, values[i]);
//
//                }
//            }
//        }
//




        String jsonBody = InfoHttpUtils.getBodyDataByRequest(request);

        if( jsonBody == null) {

            System.out.println("jsonBody==null");
            return;
        }
        try {
            JSONParser parser = new JSONParser();
            Object obj =  parser.parse(jsonBody);
            JSONObject jsonObj = (JSONObject) obj;

            Set<String> keys = jsonObj.keySet();


            for (String key : keys) {

//                System.out.println("jsonObj key--->"+key);
//                System.out.println("jsonObj values--->"+jsonObj.get(key).toString());

                setParameter(key, jsonObj.get(key).toString());
            }

        } catch (Exception e) {
            LOG.debug("InfoReadableRequestWrapper e==>"+e);

         }
    }


    @Override
    public String getParameter(String name) {
        String[] paramArr = getParameterValues(name);
        if( paramArr != null && paramArr.length > 0) {
            return paramArr[0];
            } else {
            return null;
        }
    }

    @Override public Map<String, String[]> getParameterMap() {
        return Collections.unmodifiableMap( params);
    }

    @Override public Enumeration<String> getParameterNames() { return Collections.enumeration( params.keySet()); }

    @Override public String[] getParameterValues(String name) {
        String[] result = null; String[] value = params.get(name);
        if( value != null) { result = new String[ value.length];
            System.arraycopy(value, 0, result, 0, value.length);
        }
        return result;
    }
    public void setParameter(
            String name, String value) {
            String[] param = {value}; params.put(name, param);
    }

}