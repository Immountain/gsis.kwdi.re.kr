package infomind.com.cmm.bean;

import infomind.com.cmm.InfoConstants;
import infomind.com.cms.info.site.service.InfoSiteMenuService;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class InfoLoginPageCacheManager implements InitializingBean {

    private Map<String, String> LogInPageMap = new HashMap<>();


    @Override
    public void afterPropertiesSet() throws Exception {


        if (InfoConstants.SERVER_MODE.equals(InfoConstants.SERVER_MODE_SERVICE)) {
            loadLoginPage();
        }

    }

    public void loadLoginPage() throws Exception {
        LogInPageMap.clear();
        LogInPageMap.put("page","");

    }

    public String getPage() {
        String page =LogInPageMap.get("page");
        return page;

    }

    public void setPage(String page) {
        LogInPageMap.clear();
        LogInPageMap.put("page",page);
    }




}
