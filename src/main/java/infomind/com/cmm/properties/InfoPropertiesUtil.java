package infomind.com.cmm.properties;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

public class InfoPropertiesUtil {




    public static ConfigurableEnvironment createProperties(String path)  {


        try {

            GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();

            // 환경변수 관리 객체 생성
            ConfigurableEnvironment env = ctx.getEnvironment();

            // 프로퍼티 관리 객체 생성
            MutablePropertySources prop = env.getPropertySources();

            prop.addLast (new ResourcePropertySource("classpath:"+path));
            return env;


        }catch (Exception e){



        }

        return null;

    }






}
