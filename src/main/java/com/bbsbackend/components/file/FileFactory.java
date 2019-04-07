package com.bbsbackend.components.file;

import java.util.Optional;

import com.bbsbackend.components.file.common.FileId;
import com.bbsbackend.components.file.repository.FileFactoryRepository;
import com.bbsbackend.components.idGenerator.IdGenerator;

public interface FileFactory {
    Optional<FileId> createFile(byte[] file, String filename);

    Optional<String> filename(FileId id);

    Optional<String> fileURI(FileId id);

    void Remove(FileId id);

    String getName();

    static FileFactory defau1t(String componentName, FileFactoryRepository repository, IdGenerator idGenerator) {
        return new FileFactoryImpl(componentName, repository, idGenerator);
    }
}
