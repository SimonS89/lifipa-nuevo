package com.test.lifipa.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Enumeration;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Registrar el tiempo de inicio
        LocalDateTime startTime = LocalDateTime.now();

        // Envolver request y response
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        // Continuar con la ejecución
        filterChain.doFilter(wrappedRequest, wrappedResponse);

        // Registrar el tiempo de finalización
        LocalDateTime endTime = LocalDateTime.now();

        // Calcular tiempo de respuesta
        Duration responseTime = Duration.between(startTime, endTime);

        // Log con nuevo formato
        logRequestResponse(wrappedRequest, wrappedResponse, responseTime);

        // Escribir la response en el output stream
        wrappedResponse.copyBodyToResponse();
    }

    private void logRequestResponse(ContentCachingRequestWrapper request,
                                    ContentCachingResponseWrapper response,
                                    Duration responseTime) {

        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = (request.getQueryString() != null) ? "?" + request.getQueryString() : "";
        String fullUrl = uri + queryString;

        int status = response.getStatus();
        String requestBody = getRequestBody(request);
        String responseBody = getResponseBody(response);

        logger.info("[{}] {} - Status: {} - ResponseTime: {} ms - RequestBody: {} - ResponseBody: {}",
                method, fullUrl, status, responseTime.toMillis(), requestBody, responseBody);
    }

    private String getRequestBody(ContentCachingRequestWrapper request) {
        byte[] content = request.getContentAsByteArray();
        return content.length == 0 ? "{}" : new String(content, StandardCharsets.UTF_8);
    }

    private String getResponseBody(ContentCachingResponseWrapper response) {
        byte[] content = response.getContentAsByteArray();
        return content.length == 0 ? "{}" : new String(content, StandardCharsets.UTF_8);
    }

    private String getHeaders(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            headers.append(header).append(": ").append(request.getHeader(header)).append("; ");
        }
        return headers.toString();
    }
}
