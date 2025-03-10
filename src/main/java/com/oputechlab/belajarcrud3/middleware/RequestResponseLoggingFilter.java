package com.oputechlab.belajarcrud3.middleware;

import jakarta.servlet.ServletException;
import jakarta.servlet.FilterChain;
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

@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(wrappedRequest, wrappedResponse);
        long duration = System.currentTimeMillis() - startTime;

        // Log request dengan URL endpoint
        logger.info("Request: {} {} | Headers: {} | Body: {}",
                wrappedRequest.getMethod(),
                wrappedRequest.getRequestURI(),
                wrappedRequest.getHeaderNames(),
                getRequestBody(wrappedRequest));

        // Log response dengan status kode & durasi
        logger.info("Response: {} {} | Duration: {} ms | Body: {}",
                response.getStatus(),
                response.getContentType(),
                duration,
                getResponseBody(wrappedResponse));

        wrappedResponse.copyBodyToResponse();
    }

    private String getRequestBody(ContentCachingRequestWrapper request) {
        byte[] content = request.getContentAsByteArray();
        return (content.length > 0) ? new String(content, StandardCharsets.UTF_8) : "EMPTY";
    }

    private String getResponseBody(ContentCachingResponseWrapper response) throws IOException {
        byte[] content = response.getContentAsByteArray();
        response.copyBodyToResponse();
        return (content.length > 0) ? new String(content, StandardCharsets.UTF_8) : "EMPTY";
    }
}
