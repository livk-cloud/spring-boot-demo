package com.livk.commons.web.util;

import com.google.common.collect.Lists;
import com.livk.commons.jackson.JacksonUtils;
import com.livk.commons.util.StreamUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * WebUtils
 * </p>
 *
 * @author livk
 */
@UtilityClass
public class WebUtils extends org.springframework.web.util.WebUtils {

    private static final String UNKNOWN = "unknown";

    private static final String HTTP_IP_SPLIT = ",";

    public ServletRequestAttributes servletRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        Assert.notNull(servletRequestAttributes, "attributes not null!");
        return servletRequestAttributes;
    }

    public HttpServletRequest request() {
        return servletRequestAttributes().getRequest();
    }

    public HttpServletResponse response() {
        return servletRequestAttributes().getResponse();
    }

    public HttpSession session() {
        return request().getSession();
    }

    public String parameter(String name) {
        return request().getParameter(name);
    }

    public String header(String headerName) {
        return request().getHeader(headerName);
    }

    public HttpHeaders headers() {
        HttpServletRequest request = request();
        return headers(request);
    }

    public HttpHeaders headers(HttpServletRequest request) {
        MultiValueMap<String, String> headers = StreamUtils.convert(request.getHeaderNames())
                .collect(Collectors.toMap(Function.identity(),
                        s -> StreamUtils.convert(request.getHeaders(s)).toList(),
                        (list1, list2) -> Stream.concat(list1.stream(), list2.stream()).toList(),
                        LinkedMultiValueMap::new));
        return new HttpHeaders(headers);
    }

    public Map<String, String> paramMap(HttpServletRequest request, CharSequence delimiter) {
        return request.getParameterMap()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> String.join(delimiter, entry.getValue())));
    }

    public Map<String, String> paramMap(CharSequence delimiter) {
        return paramMap(request(), delimiter);
    }

    public MultiValueMap<String, String> params(HttpServletRequest request) {
        Map<String, List<String>> map = request.getParameterMap()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> Lists.newArrayList(entry.getValue())));
        return new LinkedMultiValueMap<>(map);
    }

    public MultiValueMap<String, String> params() {
        return params(request());
    }

    public String realIp() {
        return realIp(request());
    }

    public String realIp(HttpServletRequest request) {
        // 这个一般是Nginx反向代理设置的参数
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.hasText(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (!StringUtils.hasText(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!StringUtils.hasText(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!StringUtils.hasText(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 处理多IP的情况（只取第一个IP）
        return ip != null && ip.contains(HTTP_IP_SPLIT) ? ip.split(HTTP_IP_SPLIT)[0] : ip;
    }

    public void out(Object data) {
        out(response(), data);
    }

    public void out(HttpServletResponse response, Object data) {
        out(response, JacksonUtils.toJsonStr(data), MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * 根据response写入返回值
     *
     * @param response    response
     * @param message     写入的信息
     * @param contentType contentType {@link MediaType}
     */
    public void out(HttpServletResponse response, String message, String contentType) {
        response.setContentType(contentType);
        try (PrintWriter out = response.getWriter()) {
            out.print(message);
            out.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}