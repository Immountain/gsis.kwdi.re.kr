package infomind.com.cms.info.site.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cmm.bean.InfoUserMenuCacheManager;
import infomind.com.cms.info.site.dao.InfoSiteMenuDAO;
import infomind.com.cms.info.site.service.InfoSiteMenuService;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;


@Service("InfoSiteMenuService")
public class InfoSiteMenuServiceImpl extends EgovAbstractServiceImpl implements InfoSiteMenuService {

    @Resource(name="InfoSiteMenuDAO")
    private InfoSiteMenuDAO infoSiteMenuDAO;

    @Resource(name = "infoUserMenuCacheManager")
    private InfoUserMenuCacheManager menuCacheManager;

    @Override
    public InfoSiteMenuVO selectSiteMenuInfo(InfoSiteMenuVO vo) throws Exception {
        return infoSiteMenuDAO.selectSiteMenuInfo(vo);
    }


    @Override
    public void insertSiteMenu(InfoSiteMenuVO vo) throws Exception {
        infoSiteMenuDAO.insertSiteMenu(vo);
    }

    @Override
    public void updateSiteMenu(InfoSiteMenuVO vo) throws Exception {
        infoSiteMenuDAO.updateSiteMenu(vo);

    }

    @Override
    public Integer selectSiteMemuIdCount(InfoSiteMenuVO vo) throws Exception {
        return infoSiteMenuDAO.selectSiteMemuIdCount(vo);
    }

    @Override
    public Integer selectSiteSlugCount(InfoSiteMenuVO vo) throws Exception {
        return infoSiteMenuDAO.selectSiteSlugCount(vo);
    }

    @Override
    public List<?> selectSiteParentMenuList(InfoSiteMenuVO searchVO) throws Exception {
        return infoSiteMenuDAO.selectSiteParentMenuList(searchVO);
    }

    @Override
    public List<InfoSiteMenuVO> selectSiteAllMenuList(InfoSiteMenuVO searchVO) throws Exception {

        List<InfoSiteMenuVO> list = infoSiteMenuDAO.selectSiteAllMenuList(searchVO);


        Map<String, InfoSiteMenuVO> menuMaps = list.stream()
                .collect(toMap(InfoSiteMenuVO::getSiteMemuId, Function.identity()));

        Consumer<InfoSiteMenuVO> setChildMenu = m -> {
            InfoSiteMenuVO parentMenu = menuMaps.get(m.getParentId());
            if (parentMenu != null)
                parentMenu.addChildMenu(m);
        };

        list.forEach(setChildMenu::accept);

        List<InfoSiteMenuVO> parentMenus = list.stream()
                .filter(m ->"root".equals(m.getParentId()) )
                .sorted(Comparator.comparingInt(InfoSiteMenuVO::getOrd))
                .collect(toList());

        return parentMenus;
    }

    @Override
    public List<InfoSiteMenuVO> getSiteAllMenuList(InfoSiteMenuVO searchVO) throws Exception {
          return  infoSiteMenuDAO.selectSiteAllMenuList(searchVO);
    }

    @Override
    public void updateList(List<InfoSiteMenuVO> list, String pId) throws Exception {
        for(InfoSiteMenuVO vo : list) {
            vo.setParentId(pId);
            InfoSiteMenuVO infoSiteMenuVO = menuCacheManager.getMenuByCode(vo.getSiteMemuId());
            if(StringUtils.isEmpty(infoSiteMenuVO.getSiteMemuId())) {
                infoSiteMenuDAO.insertSiteMenu(vo);
            }else{
                infoSiteMenuDAO.updateSiteMenu(vo);
            }
            updateList(vo.getChildMenu(), vo.getSiteMemuId());
        }
    }

    @Override
    public String saveSiteMenu(InfoSiteMenuVO vo) throws Exception {

        if(vo.is__created__()) {
            if(infoSiteMenuDAO.selectSiteMemuIdCount(vo) > 0) {
                return "DUPLICATE_ID";
            }else if(StringUtils.isNotEmpty(vo.getSlug())) {
                if(infoSiteMenuDAO.selectSiteSlugCount(vo) > 0) {
                    return "DUPLICATE_SLUG";
                }
            }
            infoSiteMenuDAO.insertSiteMenu(vo);
        }else if(vo.is__modified__()) {
            InfoSiteMenuVO searchVO = infoSiteMenuDAO.selectSiteMenuInfo(vo);
            if(searchVO == null) {
                return "NOT_FOUND";
            }else if(StringUtils.isNotEmpty(vo.getSlug())) {
                if (!vo.getSlug().equals(searchVO.getSlug()) && infoSiteMenuDAO.selectSiteSlugCount(vo) > 0) {
                    return "DUPLICATE_SLUG";
                }
            }
            infoSiteMenuDAO.updateSiteMenu(vo);
        }else {
            return "NOT_CHANGED";
        }
        return "OK";
    }
}
