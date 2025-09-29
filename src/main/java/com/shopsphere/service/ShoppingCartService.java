package com.shopsphere.service;
import com.shopsphere.entity.*;
import com.shopsphere.repository.ShoppingCartRepository;
import com.shopsphere.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public ShoppingCart getCartForUser(User user) {
        return shoppingCartRepository.findByUser(user).orElseGet(() -> {
            ShoppingCart newCart = new ShoppingCart();
            newCart.setUser(user);
            return shoppingCartRepository.save(newCart);
        });
    }

    @Transactional
    public void addProductToCart(User user, Long productId) {
        ShoppingCart cart = getCartForUser(user);
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + 1);
                shoppingCartRepository.save(cart);
                return;
            }
        }
        CartItem newItem = new CartItem();
        newItem.setProduct(product);
        newItem.setQuantity(1);
        cart.getItems().add(newItem);
        shoppingCartRepository.save(cart);
    }
}