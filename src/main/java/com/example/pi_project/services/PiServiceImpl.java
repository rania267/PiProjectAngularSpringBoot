package com.example.pi_project.services;

import com.example.pi_project.entities.*;
import com.example.pi_project.repositories.*;
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
public class PiServiceImpl implements IPiService , UserDetailsService {
    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    ContractRepository contractRepository;
    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }
    @Override
    public Contract getContractById(int id) {
        return contractRepository.findById(id).orElse(null);
    }

    @Override
    public Delivery getDeliveryById(int id) {
        return deliveryRepository.findById(id).orElse(null);
    }


    @Override
    public Delivery addDelivery(Delivery delivery) {
       return  deliveryRepository.save(delivery);

    }

    @Override
    public Contract addContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public void deleteDelivery(int id)  {
        deliveryRepository.deleteById(id);
    }

    @Override
    public Contract updateContract(Contract contract) {
        return contractRepository.save(contract);
    }



    @Override
    public void deleteContract(int id) {
        contractRepository.deleteById(id);
    }
    @Override
    public Delivery updateDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role r) {
        return roleRepository.save(r);
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public User addUser(User u) {
        return userRepository.save(u);
    }
    @Override
    public User updateUser(User u) {
        return userRepository.save(u);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getUser().getRole().getType().name()));
        return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), authorities);
    }
    @Autowired
    OrdeerRepository orderRepository;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    CartShoppingRepository cartShoppingRepository;
    @Autowired
    DonationRepository donationRepository;

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
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    OfferRepository offerRepository;
    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }
    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public Request addRequest(Request request) {
        return  requestRepository.save(request);

    }

    @Override
    public Offer addOffer(Offer contract) {
        return offerRepository.save(contract);
    }
    @Override
    public Offer updateOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public void deleteOffer(int id) {
        offerRepository.deleteById(id);
    }
    @Override
    public Request updateRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public void deleteRequest(int id) {
        requestRepository.deleteById(id);
    }


    @Override
    public Provider updateProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public void deleteProvider(int id) {
        providerRepository.deleteById(id);
    }
    @Override
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    @Override
    public Provider addProvider(Provider provider) {
        return  providerRepository.save(provider);

    }



}
