package infomind.com.view;

import infomind.com.cmm.InfoConstants;
import infomind.com.cmm.support.ApplicationContextProvider;
import infomind.com.cms.info.banner.service.InfoBannerGroupService;
import infomind.com.cms.info.banner.service.InfoBannerService;
import infomind.com.cms.info.layout.service.InfoLayoutInfoService;
import infomind.com.cms.info.layout.vo.InfoLayoutInfoVO;
import infomind.com.cms.info.page.service.InfoPageInfoService;
import infomind.com.cms.info.page.vo.InfoPageInfoVO;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.utils.page.InfoPageUtils;
import org.apache.commons.io.FileUtils;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.Definition;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.evaluator.BasicAttributeEvaluatorFactory;
import org.apache.tiles.evaluator.impl.DirectAttributeEvaluator;
import org.apache.tiles.mgmt.MutableTilesContainer;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoLayoutTilesViewPreparer implements ViewPreparer {

    private HttpServletRequest request;

    @Override
    public void execute(Request tilesContext, AttributeContext attributeContext) {

        //String layoutFile = "page-layouts.jsp";
        String layoutFile = "/WEB-INF/jsp/page/layout/default/info-layouts.jsp";
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            request = ((ServletRequestAttributes) requestAttributes).getRequest();
        }

        Boolean isIndex = (Boolean) request.getAttribute("isIndex");

        if (isIndex != null && isIndex) {
            //메인 페이지 일때
        } else {
            InfoSiteMenuVO menu = (InfoSiteMenuVO) request.getAttribute("menuInfo");
            if (menu.getLayoutType() != null || !"".equals(menu.getLayoutType())) {
                if ("JSP".equals(menu.getLayoutType())) {
                    //파일 경로 찾기
                    layoutFile = "/WEB-INF/jsp/page/layout/" + menu.getLayout() + "/info/info-layouts.jsp";

                    File newFile = new File(InfoConstants.DIR_PATH() + layoutFile);
                    if (newFile.isFile()) {
                        System.out.println("레이아웃 파일 있습니다.");
                    } else {
                        InfoLayoutInfoVO lyVo = new InfoLayoutInfoVO();
                        lyVo.setLayoutId(menu.getLayout());

                        try {
                            ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
                            InfoLayoutInfoService infoLayoutInfoService = applicationContext.getBean(InfoLayoutInfoService.class);
                            lyVo = infoLayoutInfoService.getLayoutContentInfo(lyVo);
                            InfoPageUtils.createLayoutFile(lyVo.getContentInfoDecode(), menu.getLayout());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    layoutFile = "/WEB-INF/jsp/page/layout/" + menu.getLayout() + "/page-layouts.jsp";
                }
            }
        }
        // MutableTilesContainer container = (MutableTilesContainer) TilesAccess.getCurrentContainer(tilesContext);
        // Definition definition = new Definition();
        // Attribute attr = new Attribute(layoutFile);
        // definition.setTemplateAttribute(attr);
        // definition.putAttribute("content", new Attribute("abcd"));
        // container.register(definition, tilesContext);

        Attribute attr = new Attribute(layoutFile);
        attributeContext.setTemplateAttribute(attr);

        try {
            readFileTilesAttr(attributeContext, layoutFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFileTilesAttr(AttributeContext attributeContext, String path) throws IOException {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        InfoPageInfoService infoPageInfoService = applicationContext.getBean(InfoPageInfoService.class);

        String text = FileUtils.readFileToString(new File(InfoConstants.DIR_PATH() + path));

        Pattern pattern = Pattern.compile("tiles:insertAttribute name=\"(.*)\"");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            switch (matcher.group(1)) {
                case "content":
                    break;
                default:
                    try {
                        InfoPageInfoVO infoPageInfoVO = infoPageInfoService.selecPageInfoDetail(InfoPageInfoVO.builder().pageId(matcher.group(1)).build());

                        File file = InfoPageUtils.createPageFile(infoPageInfoVO.getContentInfoDecode(), infoPageInfoVO.getPageId(), infoPageInfoVO.getPageGroupId());

                        String filePath = file.getPath().replaceAll("\\\\", "/");
                        filePath = filePath.replace(InfoConstants.DIR_PATH(), "");
                        attributeContext.putAttribute(matcher.group(1), new Attribute(filePath));

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
            }
        }

    }
}
