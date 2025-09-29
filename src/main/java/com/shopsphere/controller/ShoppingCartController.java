package com.shopsphere.controller;
import com.shopsphere.entity.ShoppingCart;
import com.shopsphere.entity.User;
import com.shopsphere.service.ShoppingCartService;
import com.shopsphere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @GetMapping
    public String viewCart(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        ShoppingCart cart = shoppingCartService.getCartForUser(user);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable("productId") Long productId, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        shoppingCartService.addProductToCart(user, productId);
        return "redirect:/products";
    }
}