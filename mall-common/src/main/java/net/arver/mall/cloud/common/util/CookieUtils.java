package net.arver.mall.cloud.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public final class CookieUtils {

    /**
     * 日志.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CookieUtils.class);



    private static void doSetCookie(final HttpServletRequest request, final HttpServletResponse response,
                                    final String cookieName, final String cookieValue,
                                    final int cookieMaxAge, final String encodeString) {
        try {
            String tempCookieValue = cookieValue;
            if (cookieValue == null) {
                tempCookieValue = "";
            } else {
                tempCookieValue = URLEncoder.encode(tempCookieValue, encodeString);
            }
            final Cookie cookie = new Cookie(cookieName, tempCookieValue);
            if (cookieMaxAge > 0) {
                cookie.setMaxAge(cookieMaxAge);
            }
            if (request != null) {
                cookie.setDomain(getDomainName(request));
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (final UnsupportedEncodingException e) {
            LOGGER.error("Cookie Encode Error.", e);
        }
    }

    /**
     * 得到cookie的域名.
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
            // http://
            final int httpProtocolHeaderLength = 7;
            serverName = serverName.substring(httpProtocolHeaderLength);
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
