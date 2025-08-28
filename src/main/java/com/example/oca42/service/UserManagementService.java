package com.example.oca42.service;

import com.example.oca42.entity.UserAccount;
import com.example.oca42.model.UserResponseDto;
import com.example.oca42.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserManagementService implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    //ModelMapper/ MapStruct/Remondis-remap/

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<UserAccount> all = userRepository.findAll();
        return all.stream()
                .map(u -> modelMapper.map(u, UserResponseDto.class))
                .toList();
    }

    //Optional<T>
    @Override
    public UserResponseDto getById(Long id) {
//        UserAccount byId = userRepository.findById(id);

        Optional<UserAccount> byId1 = userRepository.findById(id);

        UserAccount userAccount = byId1.orElse(new UserAccount(null, "test", "password", 99));
        return modelMapper.map(userAccount, UserResponseDto.class);

//        if (byId1.isPresent()) {
//            return modelMapper.map(byId1, UserResponseDto.class);
//        } else {
//            System.out.println("No such user");
//            return null;
//        }


//        if(byId!= null){
//            return modelMapper.map(byId, UserResponseDto.class);
//        }else {
//            System.out.println("No such user");
//            return null;
//        }
    }

//    public static void main(String[] args) {
////         System.out.println(getOptional(true).get());
////        getOptional(false).orElse("Null value optional");
//        String nullValueOptional = getOptional(true)
//                .orElseThrow(() -> new RuntimeException("No such user"));
//
//        System.out.println(nullValueOptional);
//    }

//    public static Optional<String> getOptional(boolean hasString) {
//        String s = null;
//        if (hasString) {
//            s = "new string";
//        }
//        return Optional.ofNullable(s);
//
//        //null olmadigini bilsek bele optional istifae edirikse of methodundan istifade edirik
//    }
}


