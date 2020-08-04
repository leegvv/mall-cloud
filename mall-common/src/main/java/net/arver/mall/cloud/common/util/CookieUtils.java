package net.arver.mall.cloud.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * Cookie工具类
 */
public final class CookieUtils {

    /**
     * 日志.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CookieUtils.class);



    private static void doSetCookie(final HttpServletRequest request, final HttpServletResponse response,
                                          final String cookieName, String cookieValue,
                                          final int cookieMaxAge, final String encodeString) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            final Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxAge > 0) {
                cookie.setMaxAge(cookieMaxAge);
            }
            if (request != null) {
                cookie.setDomain(getDomainName(request));
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (final Exception e) {
            LOGGER.error("Cookie Encode Error.", e);
        }
    }

    /**
     * 得到cookie的域名
     * @param request request
     * @return 域名
     */
    private static String getDomainName(final HttpServletRequest request) {
        String domainName = null;
        String serverName = request.getRequestURL().toString();
        if (serverName == null || "".equals(serverName)) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            final int len = domains.length;
            if (len > 3) {
                // www.xxx.com.net
                domainName = domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }

        if (domainName != null && domainName.indexOf(":") > 0) {
            final String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }

        return domainName;
    }
}
