package com.example.companyandemployee;


import com.example.companyandemployee.Entity.Users;
import com.example.companyandemployee.Repository.UsersRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppMainActivity {
    private UsersRepository usersRepository;

    public AppMainActivity(UsersRepository usersRepository) {

        this.usersRepository = usersRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomthing(){
//        List<Users>u1 = this.usersRepository.getAll();
//        for (int i = 0; i < u1.toArray().length; i++) {
//            System.out.println(u1.get(i));
//        }
        Users users = usersRepository.getUser(1L);
        System.out.println("User is :"+users);

//       Users u = new Users("Mohammad12312 Update","Mohammad.321@gmail.com","@Mm123123");
//
//        users = usersRepository.createUser(u);
//        System.out.println(users);


//        users =   usersRepository.updateUser(2L,u);
//        System.out.println(users);
//          usersRepository.deleteUsers(11L);
    }
}
