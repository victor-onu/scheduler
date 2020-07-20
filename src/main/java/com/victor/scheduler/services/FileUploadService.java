package com.victor.scheduler.services;

import java.io.File;


public interface FileUploadService {

    String storeFile(File file);

    String storeFile(File file, String location);

}
