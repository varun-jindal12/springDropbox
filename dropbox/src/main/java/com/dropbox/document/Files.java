package com.dropbox.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document//(collection = "files")
public class Files {
    @Id
    private String _id ;
    private String owner;
    private ArrayList<String> coowner;
    private String fileName;
    private String path;
    private String uploadDate;
    private String isDeleted;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
    @Override
    public String toString(){
        return getFileName()+" "+getOwner()+" "+getPath();
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public ArrayList<String> getCoowner() {
        return coowner;
    }

    public void setCoowner(ArrayList<String> coowner) {
        this.coowner = coowner;
    }
}
