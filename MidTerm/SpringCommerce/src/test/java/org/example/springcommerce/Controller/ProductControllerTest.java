package org.example.springcommerce.Controller;

import org.example.springcommerce.DTO.ProductDTO;
import org.example.springcommerce.Model.Product;
import org.example.springcommerce.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testProductManagement_ShouldReturnProductPage() throws Exception {
        Page<Product> productPage = new PageImpl<>(Collections.singletonList(new Product()));
        when(productService.getProducts(anyInt(), anyInt())).thenReturn(productPage);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("username");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(get("/product")
                        .param("page", "0")
                        .param("size", "20"))
                .andExpect(status().isOk())
                .andExpect(view().name("product"))
                .andExpect(model().attributeExists("productPage", "currentPage", "totalPages", "username"));
    }

    @Test
    void testCreateProduct_ShouldReturnRedirect() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        MockMultipartFile imageFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test".getBytes());

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("username");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(multipart("/product/add")
                        .file(imageFile)
                        .flashAttr("product", productDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product"));

        verify(productService, times(1)).addProduct(any(Product.class));
    }

    @Test
    void testDeleteProduct_ShouldReturnSuccess() throws Exception {
        long productId = 1L;

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("username");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(delete("/product/delete/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(content().string("Product deleted"));

        verify(productService, times(1)).deleteProduct(eq(productId), anyString());
    }

    @Test
    void testUpdateProduct_ShouldReturnSuccess() throws Exception {
        long productId = 1L;
        ProductDTO productDTO = new ProductDTO();
        MockMultipartFile imageFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test".getBytes());

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("username");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(multipart("/product/update/{id}", productId)
                        .file(imageFile)
                        .flashAttr("product", productDTO))
                .andExpect(status().isOk())
                .andExpect(content().string("Product updated"));

        verify(productService, times(1)).updateProduct(any(Product.class), anyString());
    }
}