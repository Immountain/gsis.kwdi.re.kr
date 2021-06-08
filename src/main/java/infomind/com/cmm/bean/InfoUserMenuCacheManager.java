package infomind.com.cmm.bean;

import infomind.com.cmm.InfoConstants;
import infomind.com.cms.info.site.service.InfoSiteMenuService;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.utils.MatchesUtils;
import infomind.com.utils.StreamUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class InfoUserMenuCacheManager implements InitializingBean {

    @Resource(name = "InfoSiteMenuService")
    private InfoSiteMenuService infoSiteMenuService;

    private Map<String, List<InfoSiteMenuVO>> menuListMap = new HashMap<>();

    private Map<String, List<InfoSiteMenuVO>> menuNodeListMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        if (InfoConstants.SERVER_MODE.equals(InfoConstants.SERVER_MODE_SERVICE)) {
            loadMenu();
        }
    }

    public void loadMenu() throws Exception {
        menuListMap.clear();
        menuNodeListMap.clear();

        List<InfoSiteMenuVO> list = new ArrayList<>();
        InfoSiteMenuVO infoSiteMenuVO = new InfoSiteMenuVO();

        list = infoSiteMenuService.getSiteAllMenuList(infoSiteMenuVO);

        Map<String, List<InfoSiteMenuVO>> groupList = list.stream().collect(Collectors.groupingBy(InfoSiteMenuVO::getMenuGroupId));

        //오리지날
        //menuListMap.put("USER", list);

        menuListMap.putAll(groupList);

        menuListMap.forEach((key, value) -> {
            menuNodeListMap.put(key, getMenuByNodeList(key));
        });
    }

    public List<InfoSiteMenuVO> getMenuList() {
        return menuListMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<InfoSiteMenuVO> getMenuListByCode(String groupId) {
        return menuListMap.get(groupId);
    }


    public List<InfoSiteMenuVO> getMenuByALlList(String groupId) {
        List<InfoSiteMenuVO> list = menuNodeListMap.get(groupId);
        return list;
    }

    /**
     * 노드로 변경 해줌
     *
     * @param code
     * @return
     */
    private List<InfoSiteMenuVO> getMenuByNodeList(String groupId) {

        List<InfoSiteMenuVO> list = getMenuListByCode(groupId);

        Map<String, InfoSiteMenuVO> menuMaps = list.stream().collect(toMap(InfoSiteMenuVO::getSiteMemuId, Function.identity()));

        Consumer<InfoSiteMenuVO> setChildMenu = m -> {
            InfoSiteMenuVO parentMenu = menuMaps.get(m.getParentId());
            if (parentMenu != null)
                parentMenu.addChildMenu(m);
        };

        list.forEach(setChildMenu::accept);

        List<InfoSiteMenuVO> parentMenus = list.stream()
                .filter(m -> "root".equals(m.getParentId()))
                .sorted(Comparator.comparingInt(InfoSiteMenuVO::getOrd))
                .collect(toList());
        return parentMenus;

    }


    public InfoSiteMenuVO getMenuBySlug(String code) {
        List<InfoSiteMenuVO> list = getMenuList();

        InfoSiteMenuVO parentMenus = new InfoSiteMenuVO();
        for (InfoSiteMenuVO vo : list) {
            if (code.equals(vo.getSlug())) {
                parentMenus = vo;
            }
        }
        return parentMenus;
    }

    public InfoSiteMenuVO getSiteMenuBySlug(InfoSiteVO infoSite, String slug) {
        List<InfoSiteMenuVO> list = getMenuList();

        return list.stream()
                .filter(v -> infoSite.getSiteId().equals(v.getInfoSite().getSiteId()) && slug.equals(v.getSlug()))
                .collect(StreamUtils.singletonCollector());
    }


    public InfoSiteMenuVO getMenuByUrl(InfoSiteVO infoSite, String url) {
        List<InfoSiteMenuVO> list = getMenuList();

        return list.stream().filter(v -> {
            return infoSite.getSiteId().equals(v.getInfoSite().getSiteId()) && MatchesUtils.checkAntPattern(v.getUrl(), url) && "common".equals(v.getMenuType());
        }).collect(StreamUtils.singletonCollector());
    }


    public InfoSiteMenuVO getMenuPathUrl(String Path) {
        List<InfoSiteMenuVO> list = getMenuList();
        InfoSiteMenuVO parentMenus = new InfoSiteMenuVO();
        for (InfoSiteMenuVO vo : list) {

            String[] path = vo.getUrl().split("/");

            if (path.length > 0) {

                String pathUrl = "";
                for (int i = 1; i < path.length - 1; i++) {
                    pathUrl = pathUrl + "/" + path[i];
                }
                if (Path.equals(pathUrl)) {

                    parentMenus = vo;
                }
            }

        }
        return parentMenus;
    }


    public InfoSiteMenuVO getMenuByCode(String code) {
        List<InfoSiteMenuVO> list = getMenuList();
        InfoSiteMenuVO parentMenus = new InfoSiteMenuVO();
        for (InfoSiteMenuVO vo : list) {
            if (code.equals(vo.getSiteMemuId())) {
                parentMenus = vo;
            }
        }
        return parentMenus;
    }


    public List<InfoSiteMenuVO> getMenuByParentId(String parentId, String memuId) {
        List<InfoSiteMenuVO> list = getMenuList();

        List<InfoSiteMenuVO> parentMenus = new ArrayList<>();


        for (InfoSiteMenuVO vo : list) {
            if (parentId.equals(vo.getParentId())) {
                if (vo.getSiteMemuId().equals(memuId)) {
                    vo.setCheckYn("Y");
                } else {
                    vo.setCheckYn("N");
                }
                parentMenus.add(vo);
            }
        }
        return parentMenus;
    }


}
