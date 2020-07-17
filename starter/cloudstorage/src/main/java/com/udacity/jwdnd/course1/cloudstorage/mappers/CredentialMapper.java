package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CredentialMapper {
        @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
        List<Credential> getCredentials(int userId);
        @Insert("INSERT INTO CREDENTIALS (username, salt, password, url, userId, credentialId) VALUES(#{username}, #{salt}, #{password}, #{url}, #{userid}, #{credentaialid})")
        @Options(useGeneratedKeys = true, keyProperty = "credentialId")
        int insert(Credential credential);
}
