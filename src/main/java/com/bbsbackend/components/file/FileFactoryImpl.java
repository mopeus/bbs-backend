package com.bbsbackend.components.file;

import java.util.Optional;

import com.bbsbackend.components.file.common.FileId;
import com.bbsbackend.components.file.repository.FileFactoryRepository;
import com.bbsbackend.components.idGenerator.IdGenerator;

public class FileFactoryImpl implements FileFactory{
	 private final String componentName;
	    private final FileFactoryRepository repository;
	    private final IdGenerator idGenerator;

	    FileFactoryImpl(String componentName, FileFactoryRepository repository, IdGenerator idGenerator) {
	        this.componentName = componentName;
	        this.repository = repository;
	        this.idGenerator = idGenerator;
	    }

	    @Override
	    public Optional<FileId> createFile(byte[] file, String filename) {
	        FileId id = FileId.of(idGenerator.generateId());
	        if (repository.saveFile(id, file, filename)) {
	            return Optional.of(id);
	        }
	        return Optional.empty();
	    }

	    @Override
	    public Optional<String> filename(FileId id) {
	        return Optional.ofNullable(repository.getFilename(id));
	    }

	    @Override
	    public Optional<String> fileURI(FileId id) {
	        return Optional.ofNullable(repository.getURI(id));
	    }

	    @Override
	    public void Remove(FileId id) {
	        repository.delete(id);
	    }

	    @Override
	    public String getName() {
	        return this.componentName;
	    }
}
