package com.example.companyandemployee.Repository;

import com.example.companyandemployee.Entity.Users;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsersRepository {
    private JdbcTemplate jdbcTemplate;

    public UsersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
//        System.out.println("AuthorRepository: Data source is + " + jdbcTemplate.getDataSource());
//        System.out.println("AuthorRepository: Data source is + " + ((HikariDataSource)jdbcTemplate.getDataSource()));
//        System.out.println("AuthorRepository: Data source DriverClassName is + " + ((HikariDataSource)jdbcTemplate.getDataSource()).getDriverClassName());
//        System.out.println("AuthorRepository: Data source url is + " + ((HikariDataSource)jdbcTemplate.getDataSource()).getJdbcUrl());
    }

    public List<Users> getAll(){
        RowMapper<Users> rowMapper = new BeanPropertyRowMapper<>(Users.class);
        try{
            List<Users> user = jdbcTemplate.query("Select * From \"Users\"",rowMapper);
            return user;
        }catch (Exception e)
        {
            System.out.println(e);
        }

        return null;
    }
    public Users getUser(Long id){
        RowMapper<Users> rowMapper = new BeanPropertyRowMapper<>(Users.class);
        try{
            Users user = jdbcTemplate.queryForObject("Select * From \"Users\" Where id=?",rowMapper,id);
            return user;
        }catch (Exception e)
        {
            System.out.println("user not found: "+e);
        }

        return null;
    }

    public Users createUser(Users users){


//        jdbcTemplate.update("INSERT INTO public.Users(\n"+ "\tuserName, password, email)\n" + "\tVALUES (?, ?, ?, ?);",users.getUserName(),users.getPassword(),users.getEmail());

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(
                        "INSERT INTO \"Users\"("+"\"user_name\", \"password\", \"email\")" +
                                "VALUES (?,?,?);",
                                new String[]{"id"});
                preparedStatement.setString(1,users.getUserName());
                preparedStatement.setString(2,users.getPassword());
                preparedStatement.setString(3,users.getEmail());
                return  preparedStatement;
            }
        },keyHolder);

        return getUser(keyHolder.getKey().longValue()) ;

    }

    public Users updateUser(Long id,Users users){
        try {
            if (this.getUser(id) != null) {
                jdbcTemplate.update(new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement preparedStatement = con.prepareStatement(
                                "Update  \"Users\" SET  user_name=?, email=?, password=? Where id=?");

                        preparedStatement.setString(1, users.getUserName());
                        preparedStatement.setString(3, users.getPassword());
                        preparedStatement.setString(2, users.getEmail());
                        preparedStatement.setLong(4, id);
                        return preparedStatement;
                    }
                });
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return getUser(id) ;

    }


    public void deleteUsers(Long id){
        try{
             Users u = this.getUser(id);
             if (u!=null)
             {
                jdbcTemplate.update("DELETE FROM public.\"Users\" WHERE id=?;",id);
                System.out.println("Done");
             }
             else {
                 System.out.println("Users not Found");
             }
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
