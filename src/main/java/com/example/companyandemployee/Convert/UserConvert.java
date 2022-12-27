package com.example.companyandemployee.Convert;

import com.example.companyandemployee.DTO.UsersDTO;
import com.example.companyandemployee.Entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserConvert {
    public UsersDTO fromUserToDTO(Users u)
    {
        return new UsersDTO(u.getUserName(),u.getEmail(),u.getPassword());
    }

    public Users fromUsersDTOToUsers(UsersDTO uDTO)
    {
        return new Users(uDTO.getUserName(),uDTO.getEmail(),uDTO.getPassword());
    }
}
