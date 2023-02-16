package com.example.pi_project.services;

import com.example.pi_project.entities.*;

import java.util.List;

public interface IPiService {

    public Ordeer addOrder(Ordeer order);
    public void deleteOrder(int id);
    public Ordeer updateOrder(Ordeer order);
    public List<Ordeer> getAllOrder();


    public CartShopping addOCart(CartShopping cartShopping);
    public void deleteCartShopping(int id);
    public CartShopping updateCartShopping(CartShopping cartShopping);
    public List<CartShopping> getAllCartShopping();

    public Donation addODonation (Donation  donation);
    public void deleteDonation (int id);
    public Donation  updateDonation (Donation  donation);
    public List<Donation > getAllDonation ();





}
