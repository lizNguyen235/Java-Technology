package org.example.springcommerce.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class LoginControllerTest {

    @Mock
    private Authentication authentication;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new LoginController()).build();
    }

    @Test
    void testLogin_WhenAnonymousUser_ShouldReturnIndex() throws Exception {
        // Simulate an anonymous user
        when(authentication.getName()).thenReturn("anonymousUser");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())  // Check HTTP status
                .andExpect(view().name("index"));  // Check view name
    }

    @Test
    void testLogin_WhenAuthenticatedUser_ShouldRedirectToHome() throws Exception {
        // Simulate an authenticated user
        when(authentication.getName()).thenReturn("user");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())  // Check redirection
                .andExpect(redirectedUrl("/home"));  // Check redirect URL
    }
}