package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
        @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
        List<Credential> getCredentials(int userId);
        @Insert("INSERT INTO CREDENTIALS (username, key, password, url, userid, credentialid) VALUES(#{username}, #{key}, #{password}, #{url}, #{userId}, #{credentialId})")
        @Options(useGeneratedKeys = true, keyProperty = "credentialId")
        int insert(Credential credential);
        @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
        int delete(int credentialId);
}
