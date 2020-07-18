package com.homework.loan.web.filter;

import com.homework.loan.service.request.RequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class RequestFilterTest {

    private static final String LT_COUNTRY_CODE = "lt";


    @Autowired
    private RequestFilter requestFilter;

    @MockBean
    private HttpServletRequest request;

    @MockBean
    private HttpServletResponse response;

    @MockBean
    private FilterChain filterChain;

    @MockBean
    private RequestService requestService;

    @Test
    public void underLimit() throws ServletException, IOException {
        Locale locale = new Locale(LT_COUNTRY_CODE);
        when(request.getLocale()).thenReturn(locale);
        when(requestService.requestCountUnderLimit(any())).thenReturn(true);
        requestFilter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter(any(), any());
    }

    @Test
    public void overLimitEncountered() throws ServletException, IOException {
        Locale locale = new Locale(LT_COUNTRY_CODE);
        when(request.getLocale()).thenReturn(locale);
        when(requestService.requestCountUnderLimit(any())).thenReturn(false);
        requestFilter.doFilterInternal(request, response, filterChain);
        verify(response).sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    @Test
    public void defaultLocale() throws ServletException, IOException {
        when(request.getLocale()).thenReturn(null);
        when(requestService.requestCountUnderLimit(any())).thenReturn(true);
        requestFilter.doFilterInternal(request, response, filterChain);
        verify(requestService).requestCountUnderLimit("lv");
    }
}