package com.salesmanagementsystem.sales_management_system.components;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UrlBuilder {
    public String replaceQueryParam(String paramName, Object paramValue) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .replaceQueryParam(paramName, paramValue)
                .toUriString();
    }

    public String replaceQueryParams(Map<String, Object> params) {
        UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
        params.forEach(builder::replaceQueryParam);
        return builder.toUriString();
    }
}
