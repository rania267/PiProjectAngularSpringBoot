package com.example.pi_project.services;

import com.example.pi_project.entities.*;
import com.example.pi_project.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class PiServiceImpl implements IPiService {
;


    OrdeerRepository orderRepository;
    DonationRepository donationRepository;
    CartShoppingRepository cartShoppingRepository;

    @Override
    public Ordeer addOrder(Ordeer order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Ordeer updateOrder(Ordeer order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Ordeer> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public CartShopping addOCart(CartShopping cartShopping) {
        return cartShoppingRepository.save(cartShopping);
    }

    @Override
    public void deleteCartShopping(int id) {
        cartShoppingRepository.deleteById(id);
    }

    @Override
    public CartShopping updateCartShopping(CartShopping cartShopping) {
        return cartShoppingRepository.save(cartShopping);
    }

    @Override
    public List<CartShopping> getAllCartShopping() {
        return cartShoppingRepository.findAll();
    }

    @Override
    public Donation addODonation(Donation donation) {
        return donationRepository.save(donation);
    }

    @Override
    public void deleteDonation(int id) {
donationRepository.deleteById(id);
    }

    @Override
    public Donation updateDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    @Override
    public List<Donation> getAllDonation() {
        return donationRepository.findAll();
    }


}
