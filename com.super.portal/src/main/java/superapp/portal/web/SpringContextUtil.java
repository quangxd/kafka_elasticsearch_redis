package superapp.portal.web;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.weaving.LoadTimeWeaverAware;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext; //Spring应用上下文环境


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }


    public static Object getBean(String name, Class requiredType) throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }

    public static Map<String, Object> getDefaultI18nResourcesConfigFromYaml(String key) throws IOException {
        Yaml yaml = new Yaml();
        File file = ResourceUtils.getFile("classpath:application.yaml");
        InputStream inputStream = FileUtils.openInputStream(file);
        Map<String, Object> mp = yaml.load(inputStream);
        LinkedHashMap resultMap = (LinkedHashMap) mp.get(key);

        return resultMap;
    }


}