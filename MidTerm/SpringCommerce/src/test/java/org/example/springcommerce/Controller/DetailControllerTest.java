package org.example.springcommerce.Controller;

import org.example.springcommerce.Model.AccountPrincipal;
import org.example.springcommerce.Model.Product;
import org.example.springcommerce.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DetailControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private AccountPrincipal accountPrincipal;

    @InjectMocks
    private DetailController detailController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(detailController).build();
    }

    @Test
    void testAccessDetailPage() throws Exception {
        // Mock data for productService
        Long productId = 1L;
        Product mockProduct = new Product();
        mockProduct.setId(productId);
        mockProduct.setName("Product 1");
        mockProduct.setPrice(100);

        when(productService.findById(productId)).thenReturn(mockProduct);

        // Simulate Authentication and AccountPrincipal
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("username");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(accountPrincipal.address()).thenReturn("Some Address");

        mockMvc.perform(get("/detail/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(view().name("detail"))
                .andExpect(model().attributeExists("product", "role", "username", "address"))
                .andExpect(model().attribute("product", mockProduct))
                .andExpect(model().attribute("role", "ROLE_USER"))
                .andExpect(model().attribute("username", "username"))
                .andExpect(model().attribute("address", "Some Address"));

        // Verify that the service method was called
        verify(productService, times(1)).findById(productId);
    }
}