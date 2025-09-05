package com.example.oca42.service;

import com.example.oca42.entity.UserAccount;
import com.example.oca42.exception.AlreadyExistException;
import com.example.oca42.model.AddressResponseDto;
import com.example.oca42.model.ContactResponseDto;
import com.example.oca42.model.UserCreateRequestDto;
import com.example.oca42.model.UserResponseDto;
import com.example.oca42.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public List<UserResponseDto> getAllUsers() {
        List<UserAccount> all = userRepository.findAll();
        return all.stream()
                .map(u -> modelMapper.map(u, UserResponseDto.class))
                .toList();
    }

    //Optional<T>
    //getbyid de virtual bir connection var database ile bu sessiondu,
    //hemin session bu method bitene qeder acig qalir ona goe bizz hansi userin infosunu getirdiyimizi
    //bilirik, open in view false ele bagla
    public UserResponseDto getById(Long id) {
    //n + 1 problem
        Optional<UserAccount> byId1 = userRepository.getUserAccountById(id);
        UserAccount userAccount = byId1.orElseThrow(() -> new RuntimeException("User not found"));


        //eslinde bu 3 line auto olur basha dusmeye yaziriq
//        System.out.println("user already gotten");
//        ContactResponseDto contactResponseDto = new ContactResponseDto();
//        System.out.println("getting user contact");
//        contactResponseDto.setId(userAccount.getContact().getId());
//        contactResponseDto.setPhoneNumber(userAccount.getContact().getPhoneNumber());
//
//        System.out.println("getting user address");
//        List<AddressResponseDto> addresses = userAccount.getAddresses().stream().map(a->{
//            AddressResponseDto addressResponseDto = new AddressResponseDto();
//            addressResponseDto.setId(a.getId());
//            addressResponseDto.setApartment(a.getApartment());
//            return addressResponseDto;
//        }).toList();
//
//        //map process
//        UserResponseDto userResponseDto = new UserResponseDto();
//        userResponseDto.setId(userAccount.getId());
//        userResponseDto.setUsername(userAccount.getUsername());
//        userResponseDto.setContact(contactResponseDto);
//        userResponseDto.setAddresses(addresses);
//        return userResponseDto;

        return modelMapper.map(userAccount, UserResponseDto.class);
    }


    public void create(UserCreateRequestDto requestDto) {
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new AlreadyExistException("Username already exists");
        }
        UserAccount map = modelMapper.map(requestDto, UserAccount.class);

        userRepository.save(map);
    }


}
