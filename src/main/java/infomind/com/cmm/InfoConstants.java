package infomind.com.cmm;


import infomind.com.cmm.properties.InfoPropertiesUtil;
import infomind.com.cms.info.board.vo.InfoBoardSkinVO;
import infomind.com.cms.info.layout.vo.InfoLayoutInfoVO;
import infomind.com.file.vo.InfoImageSize;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class InfoConstants {

    public static final String REFERER_SITE_INFO_NAME = "REFERER_SITE_INFO";

    public static final String SESSION_SITE_INFO_NAME = "SITEINFO";

    public static final String SERVER_MODE_DEBUG = "DEBUG";

    public static final String SERVER_MODE_SERVICE = "SERVICE";

    public static final String SERVER_MODE = SERVER_MODE_SERVICE;

    public static final String SERVICE_MODE_ALL = "ALL";
    public static final String SERVICE_MODE_CMS = "CMS";
    public static final String SERVICE_MODE_SITE = "SITE";

    public static final  ConfigurableEnvironment Properties = InfoPropertiesUtil.createProperties("infomind/infoProps/info.properties");


    private static boolean createThumbs = true;


    public static String SERVICE_MODE() {
        return Properties.getProperty("info.service.mode");
    }

    public static String DIR_PATH() {
        return Properties.getProperty("info.dir.path");
    }

    public static String PAGE_DIR() {
        return Properties.getProperty("info.page.dir");
    }

    public static String PAGE_DIR_LAYOUT() {
        return Properties.getProperty("info.page.layout.dir");
    }


    public static String BOARD_SKIN_DIR() {
        return Properties.getProperty("info.board.skin.dir");
    }


    public static String IMAGE_DIR() {
        return Properties.getProperty("info.org.fileStorePath")+"image/";
    }

    public static String IMAGE_TEMP_DIR() {
        return Properties.getProperty("info.temp.fileStorePath")+"image/";
    }

    public static String FILE_DIR() {
        return Properties.getProperty("info.org.fileStorePath")+"file/";
    }

    public static String FILE_TEMP_DIR() {
        return Properties.getProperty("info.temp.fileStorePath")+"image/";
    }

    public static String SYSTEM_PRODUCTION() {
        return Properties.getProperty("info.production.mode", "PRODUCTION");
    }


    private static List<String> _FILE_THUMBS() {

        String temp =Properties.getProperty("info.file.thumbs");
        List<String> list = Arrays.asList(temp.split(","));
        return list;
    }



    public static List<InfoImageSize> FILE_THUMBS() {
        List<InfoImageSize> result = new ArrayList<>();
        List<String> strings = _FILE_THUMBS();

        for (String str : strings) {
            try {
                String[] wh = str.split("x");
                result.add(new InfoImageSize(Integer.valueOf(wh[0]), Integer.valueOf(wh[1])));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static List<InfoLayoutInfoVO> PAGE_LAYOUT_LIST() {


        String isDir = DIR_PATH()+PAGE_DIR_LAYOUT()+"/";
        List<InfoLayoutInfoVO> layoutList = new ArrayList<>();

        File[] checkDir =new File(isDir).listFiles();

        if(checkDir ==null){

            return null;

        }

        // 하위 디렉토리
        for (File info : new File(isDir).listFiles()) {
            if (info.isDirectory()) {

                InfoLayoutInfoVO lVo = new InfoLayoutInfoVO();

                lVo.setLayoutId(info.getName());
                lVo.setLayoutNm(info.getName());
                layoutList.add(lVo);
                System.out.println(info.getName());
            }
        }
        return layoutList;
    }



    public static List<InfoBoardSkinVO> BOARD_SKIN_LIST() {


        String isDir = DIR_PATH()+BOARD_SKIN_DIR()+"/";

        File[] checkDir =new File(isDir).listFiles();

        if(checkDir ==null){

            return null;

        }


        List<InfoBoardSkinVO> skinList = new ArrayList<>();
        // 하위 디렉토리
        for (File info : new File(isDir).listFiles()) {
            if (info.isDirectory()) {

                InfoBoardSkinVO lVo = new InfoBoardSkinVO();

                lVo.setBoardSkinId(info.getName());
                lVo.setBoardSkinNm(info.getName());
                skinList.add(lVo);
                System.out.println(info.getName());
            }
        }
        return skinList;
    }




}
