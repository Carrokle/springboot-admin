package com.lb.config;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Date: 2019-3-1
 * Description: 第三方登录配置
 */
public class ResourcesConfig {
    /** 第三方登录配置 */
    public static final ResourceBundle THIRDPARTY = ResourceBundle.getBundle("config/thirdParty");
    /** 国际化信息 */
    private static final Map<String, ResourceBundle> MESSAGES = new HashMap<String, ResourceBundle>();

    /** 国际化信息 */
    public static String getMessage(String key, Object... params) {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle message = MESSAGES.get(locale.getLanguage());
        if (message == null) {
            synchronized (MESSAGES) {
                message = MESSAGES.get(locale.getLanguage());
                if (message == null) {
                    message = ResourceBundle.getBundle("i18n/messages", locale);
                    MESSAGES.put(locale.getLanguage(), message);
                }
            }
        }
        if (params != null && params.length > 0) {
            return String.format(message.getString(key), params);
        }
        return message.getString(key);
    }

    /** 清除国际化信息 */
    public static void flushMessage() {
        MESSAGES.clear();
    }
}
