package com.to;

import com.to.filters.AdminAuthFilter;
import com.to.filters.UserAuthFilter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import static org.junit.jupiter.api.Assertions.*;

class MedicareApiApplicationTest {

    @Test
    void adminFilterRegistrationBean_shouldRegisterAdminAuthFilterWithCorrectUrls() {
        MedicareApiApplication app = new MedicareApiApplication();
        FilterRegistrationBean<AdminAuthFilter> bean = app.adminFilterRegistrationBean();
        assertNotNull(bean);
        assertTrue(bean.getFilter() instanceof AdminAuthFilter);
        assertTrue(bean.getUrlPatterns().contains("/api/admin/category/*"));
        assertTrue(bean.getUrlPatterns().contains("/api/admin/products/*"));
        assertTrue(bean.getUrlPatterns().contains("/api/admin/userOrders/*"));
    }

    @Test
    void userFilterRegistrationBean_shouldRegisterUserAuthFilterWithCorrectUrls() {
        MedicareApiApplication app = new MedicareApiApplication();
        FilterRegistrationBean<UserAuthFilter> bean = app.userFilterRegistrationBean();
        assertNotNull(bean);
        assertTrue(bean.getFilter() instanceof UserAuthFilter);
        assertTrue(bean.getUrlPatterns().contains("/api/users/change/*"));
        assertTrue(bean.getUrlPatterns().contains("/api/users/orders/*"));
        assertTrue(bean.getUrlPatterns().contains("/api/users/category/*"));
        assertTrue(bean.getUrlPatterns().contains("/api/users/products/*"));
        assertTrue(bean.getUrlPatterns().contains("/api/users/cart/*"));
    }
}
