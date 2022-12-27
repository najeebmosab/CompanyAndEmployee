package com.example.companyandemployee.Controller;

import com.example.companyandemployee.DTO.UsersDTO;
import com.example.companyandemployee.Services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.processing.Generated;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/Users") //all mapping methods will have a Users as parent path
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<UsersDTO> geAll()
    {
        return usersService.getAll();
    }
    @RequestMapping(method = RequestMethod.GET,path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsersDTO getUsers(@PathVariable Long id)
{
    return usersService.getUser(id);
}
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public UsersDTO addUsers(@RequestBody UsersDTO u)
    {
        return usersService.createUsers(u);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT,path = "/{id}")
    public UsersDTO updateUsers(@RequestBody UsersDTO u,@PathVariable Long id)
    {
        return usersService.updateUsers(id,u);
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsers(@PathVariable Long id)
    {
        usersService.deleteUsers(id);
    }

}
