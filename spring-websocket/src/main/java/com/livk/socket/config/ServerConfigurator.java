package com.livk.socket.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.ServerEndpointConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * <p>
 * ServerConfigurator
 * </p>
 *
 * @author livk
 */
@Slf4j
public class ServerConfigurator extends ServerEndpointConfig.Configurator {

    @Override
    public boolean checkOrigin(String originHeaderValue) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(servletRequestAttributes, "servletRequestAttributes not be null!");
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if ("livk123".equals(token)) {
            return super.checkOrigin(originHeaderValue);
        } else {
            log.info("缺少参数!");
            return false;
        }
    }

}
