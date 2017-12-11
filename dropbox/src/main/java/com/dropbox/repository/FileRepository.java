package com.dropbox.repository;

import com.dropbox.document.Files;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FileRepository extends MongoRepository<Files,String> {
    Files save(Files saved);
    Files getFilesBy_idAndOwner(String _id, String owner);
    List<Files> findAllByOwnerAndIsDeleted(String owner, String isDeleted);
    List<Files> findFilesByCoownerContains(String coowner);
//    void deleteBy_idAndOwner(String _id, String Owner);
}
