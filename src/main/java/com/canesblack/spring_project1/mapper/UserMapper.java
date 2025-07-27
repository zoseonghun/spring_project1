package com.canesblack.spring_project1.mapper;

import com.canesblack.spring_project1.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

// 자동으로 @Component 기능 비슷하게 스프링컨테이너에 등록된다
// 자바언어와 mysql언어를 통역해주는 역할
@Mapper
public interface UserMapper {

    // CRUD의 CREATE에 해당하는 기능중하나
    @Insert("INSERT INTO backend_spring_project.user(username, password, writer, role) " +
            "VALUES (#{username}, #{password}, #{writer}, #{role})")
    // void -> 우리가 데이터베이스에서 백엔드영역으로 데이터를 가져오는게 없기 때문에 void로 가져오는게 없다고 작성
    void insert(User user);
    // CRUD의 READ에 해당하는 기능중하나
    @Select("SELECT username,password,wriiter,role FROM backend_spring_project.user WHERE username=#{username}")
    User findByUsername(String username);

    @Select("SELECT writer FROM backend_spring_project.user WHERE username=#{username}")
    String findWriter(String username);
    // CRUD의 DELETE에 대항하는 기능중하나
    // CRUD의 UPDATE에 대항하는 기능중하나

}
