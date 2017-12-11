package com.dropbox.service;

import com.dropbox.document.Files;
import com.dropbox.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private final FileRepository fileRepository;

    @Autowired
    FileService(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }
    public void saveFile(Files file){
        fileRepository.save(file);
    }
    public List<Files> getUserFiles(String owner){
        return fileRepository.findAllByOwnerAndIsDeleted(owner,"N");
    }
    public Files getFile(String _id,String owner){
        return fileRepository.getFilesBy_idAndOwner(_id,owner);
    }
    public List<Files> getSharedFiles(String coowner){
        return fileRepository.findFilesByCoownerContains(coowner);
    }
}
