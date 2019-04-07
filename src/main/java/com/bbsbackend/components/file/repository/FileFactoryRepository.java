package com.bbsbackend.components.file.repository;

import com.bbsbackend.components.file.common.FileId;

public interface FileFactoryRepository {
    boolean saveFile(FileId fileId, byte[] file, String filename);

    String getFilename(FileId fileId);

    String getURI(FileId fileId);

    void delete(FileId id);

    String getRepositoryName();
}
