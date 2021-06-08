package infomind.com.cmm.bean;

import infomind.com.cmm.InfoConstants;
import infomind.com.cms.info.site.service.InfoSiteService;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.utils.StreamUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InfoSiteCacheManager implements InitializingBean {

    private List<InfoSiteVO> list;

    @Resource(name = "InfoSiteService")
    private InfoSiteService infoSiteService;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(String.format("[%s] %s", "INCMS-BEAN", "afterPropertiesSet : InfoSiteCacheManager"));
        if (InfoConstants.SERVER_MODE.equals(InfoConstants.SERVER_MODE_SERVICE)) {
            this.loadData();
        }
    }

    public void loadData() throws Exception {
        InfoSiteVO v = InfoSiteVO.builder().build();
        v.setSearchAllPage();
        this.list = (List<InfoSiteVO>) infoSiteService.selectSiteList(v);

    }

    public InfoSiteVO findSiteInfo(InfoSiteVO vo) {

        if("checkRequest".equals(vo.getSubPath())) {
            vo.setSubPath("");
        }
        if(StringUtils.isNotEmpty(vo.getLangCd())) {
            return this.list.stream().filter(vo1 -> {
                String host = vo1.getHost();
                if(InfoConstants.SERVER_MODE_DEBUG.equals(InfoConstants.SYSTEM_PRODUCTION())) {
                    host = vo1.getDebugHost();
                }
                return vo.getHost().equals(host) && vo.getLangCd().equals(vo1.getLangCd());
            }).collect(StreamUtils.singletonCollector());
        }

        return this.list.stream().filter(vo1 -> {
            String host = vo1.getHost();
            if(InfoConstants.SERVER_MODE_DEBUG.equals(InfoConstants.SYSTEM_PRODUCTION())) {
                host = vo1.getDebugHost();
            }
            return vo.getHost().equals(host) && vo.getSubPath().equals(vo1.getSubPath());
        }).collect(StreamUtils.singletonCollector());
    }

    public boolean isSiteHostSubPathUsed(InfoSiteVO vo, String subPath) {
        return this.list.stream().anyMatch(vo1 -> {
            String host = vo1.getHost();
            if(InfoConstants.SERVER_MODE_DEBUG.equals(InfoConstants.SYSTEM_PRODUCTION())) {
                host = vo1.getDebugHost();
            }
            return vo.getHost().equals(host) && subPath.equals(vo1.getSubPath());
        });
    }

    public boolean isSiteHostLangPathUsed(InfoSiteVO vo, String subPath) {
        return this.list.stream().anyMatch(vo1 -> {
            String host = vo1.getHost();
            if(InfoConstants.SERVER_MODE_DEBUG.equals(InfoConstants.SYSTEM_PRODUCTION())) {
                host = vo1.getDebugHost();
            }
            return vo.getHost().equals(host) && subPath.equals(vo1.getLangCd());
        });
    }
}
