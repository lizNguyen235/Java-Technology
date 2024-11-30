package org.example.springcommerce.Controller;

import org.example.springcommerce.DTO.CartDTO;
import org.example.springcommerce.Model.AccountPrincipal;
import org.example.springcommerce.Model.Cart;
import org.example.springcommerce.Service.CartService;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CartControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private CartService cartService;

    @Mock
    private AccountPrincipal accountPrincipal;

    @InjectMocks
    private CartController cartController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    void testAddToCart() throws Exception {
        Long productId = 1L;
        int quantity = 2;

        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId(productId);
        cartDTO.setQuantity(quantity);
        cartDTO.setTotal(200); // assuming the product price is 100

        when(accountPrincipal.getId()).thenReturn(1L);
        doNothing().when(cartService).addToCart(any(Cart.class));

        mockMvc.perform(post("/cart/add/{id}", productId)
                        .param("quantity", String.valueOf(quantity))
                        .principal(() -> accountPrincipal.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Add to cart successfully"));

        verify(cartService, times(1)).addToCart(any(Cart.class));
    }

    @Test
    void testCartManagement() throws Exception {
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setProductId(101L);
        cart.setUserId(1L);
        cart.setQuantity(2);
        cart.setTotal(2000);

        List<Cart> cartList = Arrays.asList(cart);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("username");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(accountPrincipal.getId()).thenReturn(1L);
        when(accountPrincipal.address()).thenReturn("Some Address");

        mockMvc.perform(get("/cart")
                        .param("page", "0")
                        .param("size", "20")
                        .principal(() -> accountPrincipal.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("cart"))
                .andExpect(model().attributeExists("carts", "currentPage", "totalPages", "role", "address", "username"))
                .andExpect(model().attribute("carts", cartList))
                .andExpect(model().attribute("username", "username"))
                .andExpect(model().attribute("address", "Some Address"));
    }

    @Test
    void testDeleteCart() throws Exception {
        Long cartId = 1L;

        doNothing().when(cartService).deleteCart(cartId);

        mockMvc.perform(delete("/cart/delete/{id}", cartId))
                .andExpect(status().isOk())
                .andExpect(content().string("Delete cart successfully"));

        verify(cartService, times(1)).deleteCart(cartId);
    }
}