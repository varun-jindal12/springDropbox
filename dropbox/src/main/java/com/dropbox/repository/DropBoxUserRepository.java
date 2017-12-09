package com.dropbox.repository;

import com.dropbox.document.DropBoxUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DropBoxUserRepository extends MongoRepository<DropBoxUser,String>{
    DropBoxUser save (DropBoxUser saved);

    @Override
    List<DropBoxUser> findAll();
//    List<DropBoxUser> findAllByEmailID(String emailID);
    DropBoxUser findByEmailID(String emailID);
}
