package com.dropbox.service;

import com.dropbox.document.DropBoxUser;
import com.dropbox.repository.DropBoxUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DropBoxUserService {
    private final DropBoxUserRepository dropBoxUserRepository;

    @Autowired
    DropBoxUserService(DropBoxUserRepository dropBoxUserRepository){
        this.dropBoxUserRepository = dropBoxUserRepository;
    }
    public void userSignUp(DropBoxUser user){
        dropBoxUserRepository.save(user);
    }
    public DropBoxUser getDropBoxUser(String emailID){
        return dropBoxUserRepository.findByEmailID(emailID);
    }

}
