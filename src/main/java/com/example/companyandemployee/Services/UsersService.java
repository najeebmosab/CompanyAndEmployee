package com.example.companyandemployee.Services;

import com.example.companyandemployee.Convert.UserConvert;
import com.example.companyandemployee.DTO.UsersDTO;
import com.example.companyandemployee.Entity.Users;
import com.example.companyandemployee.Repository.UsersRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UsersService {
    private UsersRepository usersRepository;
    private UserConvert userConvert;

    public UsersService(UsersRepository usersRepository, UserConvert userConvert) {
        this.usersRepository = usersRepository;
        this.userConvert = userConvert;
    }


   public List<UsersDTO> getAll(){
        return  this.usersRepository.getAll().stream().map(U1-> userConvert.fromUserToDTO(U1)).toList();
    }

    public UsersDTO getUser(Long id)
    {
         Users u =this.usersRepository.getUser(id);
         return userConvert.fromUserToDTO(u);
    }

    public UsersDTO updateUsers(Long id,UsersDTO user)
    {
        try{
            Users u =   this.usersRepository.updateUser(id,userConvert.fromUsersDTOToUsers(user));
            return userConvert.fromUserToDTO(u);
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public UsersDTO createUsers(UsersDTO usersDTO){
        try{
             Users u =   usersRepository.createUser(userConvert.fromUsersDTOToUsers(usersDTO));
            return userConvert.fromUserToDTO(u);
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public void deleteUsers(Long id)
    {
        try{
            this.usersRepository.deleteUsers(id);
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
