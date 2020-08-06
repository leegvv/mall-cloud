package net.arver.mall.cloud.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * cookie工具类.
 * @author arver
 */
public final class CookieUtils {

    /**
     * 日志.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CookieUtils.class);

    /**
     * 得到Cookie的值, 不编码.
     * @param request request
     * @param cookieName cookie名称
     * @return cookie值
     */
    public static String getCookieValue(final HttpServletRequest request, final String cookieName) {
        return getCookieValue(request, cookieName, false);
    }

    /**
     * 得到Cookie的值.
     * @param request request
     * @param cookieName cookie名称
     * @param isDecode 是否编码
     * @return cookie值
     */
    public static String getCookieValue(final HttpServletRequest request, final String cookieName,
                                        final boolean isDecode) {
        final Cookie[] cookies = request.getCookies();
        if (cookies == null || cookieName == null) {
            return null;
        }
        String cookieValue = null;
        try {
            for (final Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    if (isDecode) {
                        cookieValue = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } else {
                        cookieValue = cookie.getValue();
                    }
                    break;
                }
            }
        } catch (final UnsupportedEncodingException e) {
            LOGGER.error("Cookie Decode Error.", e);
        }
        return cookieValue;
    }

    /**
     * 得到Cookie的值.
     * @param request request
     * @param cookeName cookie名称
     * @param encodeString 编码
     * @return cookie值
     */
    public static String getCookieValue(final HttpServletRequest request, final String cookeName,
                                        final String encodeString) {
        final Cookie[] cookies = request.getCookies();
        if (cookies == null || cookeName == null) {
            return null;
        }
        String cookieValue = null;
        try {
            for (final Cookie cookie : cookies) {
                if (cookie.getName().equals(cookeName)) {
                    cookieValue = URLDecoder.decode(cookie.getValue(), encodeString);
                    break;
                }
            }
        } catch (final UnsupportedEncodingException e) {
            LOGGER.error("Cookie Decode Error.", e);
        }
        return cookieValue;
    }

    /**
     * 设置Cookie的值 不设置生效时间默认浏览器关闭即失效,也不编码.
     * @param request request
     * @param response response
     * @param cookieName cookie名称
     * @param cookieValue cookie值
     */
    public static void setCookie(final HttpServletRequest request, final HttpServletResponse response,
                                 final String cookieName, final String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1);
    }

    /**
     * 设置Cookie的值 在指定时间内生效,但不编码.
     * @param request request
     * @param response response
     * @param cookieName cookie名称
     * @param cookieValue cookie值
     * @param cookieMaxAge cooke生效最大时间
     */
    public static void setCookie(final HttpServletRequest request, final HttpServletResponse response,
                                 final String cookieName, final String cookieValue, final int cookieMaxAge) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxAge, false);
    }

    /**
     * 设置Cookie的值 不设置生效时间,但编码.
     * @param request request
     * @param response response
     * @param cookieName cookie名称
     * @param cookieValue cookie值
     * @param isEncode 是否编码
     */
    public static void setCookie(final HttpServletRequest request, final HttpServletResponse response,
                                 final String cookieName, final String cookieValue,
                                 final boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数.
     * @param request request
     * @param response response
     * @param cookieName cookie名称
     * @param cookieValue cookie值
     * @param cookieMaxAge cookie生效的最大秒数
     * @param isEncode 是否编码
     */
    public static void setCookie(final HttpServletRequest request, final HttpServletResponse response,
                                 final String cookieName, final String cookieValue,
                                 final int cookieMaxAge, final boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxAge, isEncode);
    }

    /**
     * 设置Cookie的值， 在指定时间内生效，编码参数（指定编码）.
     * @param request request
     * @param response response
     * @param cookieName cookie名称
     * @param cookieValue cookie值
     * @param cookieMaxAge cookie生效的最大秒数
     * @param encodeString 编码
     */
    public static void setCookie(final HttpServletRequest request, final HttpServletResponse response,
                                 final String cookieName, final String cookieValue, final int cookieMaxAge,
                                 final String encodeString) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxAge, encodeString);
    }

    /**
     * 删除Cookie带cookie域名.
     * @param request request
     * @param response response
     * @param cookieName cookie名称
     */
    public static void deleteCookie(final HttpServletRequest request, final HttpServletResponse response,
                                    final String cookieName) {
        doSetCookie(request, response, cookieName, "", -1, false);
    }

    /**
     * 设置cookie.
     * @param request request
     * @param response response
     * @param cookieName cookie名称
     * @param cookieValue cookie值
     * @param cookieMaxAge cookie生效的最大秒数
     * @param isEncode 是否编码
     */
    private static void doSetCookie(final HttpServletRequest request, final HttpServletResponse response,
                                          final String cookieName, final String cookieValue, final int cookieMaxAge,
                                          final boolean isEncode) {
        try {
            String tempValue;
            if (cookieValue == null) {
                tempValue = "";
            } else if (isEncode) {
                tempValue = URLEncoder.encode(cookieValue, "utf-8");
            } else {
                tempValue = cookieValue;
            }

            final Cookie cookie = new Cookie(cookieName, tempValue);
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
     * 设置Cookie的值，并使其在指定时间内生效.
     * @param request request
     * @param response response
     * @param cookieName cookie名称
     * @param cookieValue cookie值
     * @param cookieMaxAge cookie生效的最大秒数
     * @param encodeString 编码
     */
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
