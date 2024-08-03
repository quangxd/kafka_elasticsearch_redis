package superapp.portal.web.multilanguages;

import org.springframework.context.MessageSource;
import superapp.portal.web.SpringContextUtil;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import static org.apache.logging.log4j.util.Strings.isBlank;

public final class I18n {
    public I18n() {
    }

    public static String msg(String key) throws Exception {
        return msg(getLocale(""), key, (Object[]) null);
    }

    public static String msg(String key, Object... args) throws Exception {
        return msg(getLocale(""), key, args);
    }

    public static String msg(String key, String lan) throws Exception {
        return msg(getLocale(lan), key, (Object[]) null);
    }

    public static String msg(Locale locale, String key, Object... args) {
        MessageSource messageSource = SpringContextUtil.getApplicationContext().getBean(MessageSource.class);
        return messageSource.getMessage(key, args, locale);

    }

    public static Locale getLocale(String lan) throws Exception {
        if (!isBlank(lan) && isSupported(new Locale(lan))) {
            return new Locale(lan);
        }

        Map<String, Object> map = SpringContextUtil.getDefaultI18nResourcesConfigFromYaml("global-i18n");

        if (null == map) {
            //set default Local in Vietnamese
            //Vi -> Vietnamese
            //zh -> Chinese
            //en -> English
            return new Locale("Vi", "");

        } else {
            Object language = map.get("locale_language");
            Object country = map.get("locale_country");
            if (null == language) {
                throw new Exception("global-i18n key in yaml file is empty.");
            } else if (null == country) {
                throw new Exception("global-i18n key in yaml file is empty.");
            } else {
                return new Locale(language.toString().trim(), country.toString().trim());
            }
        }
    }

    private static boolean isSupported(Locale l) {
        Locale[] availableLocales = Locale.getAvailableLocales();
        return Arrays.asList(availableLocales).contains(l);
    }

}

