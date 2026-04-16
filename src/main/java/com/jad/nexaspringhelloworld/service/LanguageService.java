package com.jad.nexaspringhelloworld.service;

import com.jad.nexaspringhelloworld.command.CommandResult;
import com.jad.nexaspringhelloworld.command.language.*;
import com.jad.nexaspringhelloworld.dto.LanguageDto;
import com.jad.nexaspringhelloworld.dto.LanguageOutput;
import com.jad.nexaspringhelloworld.mapper.LanguageMapper;
import com.jad.nexaspringhelloworld.repository.LanguageRepository;
import com.jad.nexaspringhelloworld.repository.result.OperationResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    public LanguageService(final LanguageRepository languageRepository, final LanguageMapper languageMapper) {
        this.languageRepository = languageRepository;
        this.languageMapper = languageMapper;
    }

    @Transactional(readOnly = true)
    public List<LanguageDto> findAll() {
        return this.languageRepository
                .findAll()
                .stream()
                .map(this.languageMapper::entityToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public LanguageDto findById(final Integer id) {
        return this.languageRepository
                .findById(id)
                .map(this.languageMapper::entityToDto)
                .orElseThrow(() -> new RessourceNotFoundException("Language not found: " + id));
    }

    @Transactional
    public void create(final String name) {
        final OperationResult operationResult = this.languageRepository.create(name);
        if (!operationResult.success()) throw new ServiceOperationException(operationResult.message());
    }

    @Transactional
    public void update(final Integer id, final String name) {
        final OperationResult operationResult = this.languageRepository.update(id, name);
        if (!operationResult.success()) throw new ServiceOperationException(operationResult.message());
    }

    @Transactional
    public void delete(final Integer id) {
        final OperationResult operationResult = this.languageRepository.delete(id);
        if (!operationResult.success()) throw new RessourceNotFoundException(operationResult.message());
    }

    @Transactional
    public void undelete(final Integer id) {
        final OperationResult operationResult = this.languageRepository.undelete(id);
        if (!operationResult.success()) throw new RessourceNotFoundException(operationResult.message());
    }

    public CommandResult<LanguageOutput> executeCommand(final LanguageCommand languageCommand) {
        return switch (languageCommand) {
            case LanguageCreateCommand command -> {
                this.handleCreateCommand(command);
                yield CommandResult.noPayLoad();
            }
            case LanguageUpdateCommand command -> {
                this.handleUpdateCommand(command);
                yield CommandResult.noPayLoad();
            }
            case LanguageDeleteCommand command -> {
                this.handleDeleteCommand(command);
                yield CommandResult.noPayLoad();
            }
            case LanguageUndeleteCommand command -> {
                this.handleUndeleteCommand(command);
                yield CommandResult.noPayLoad();
            }
        };
    }

    private void handleCreateCommand(final LanguageCreateCommand command) {
        final OperationResult operationResult = this.languageRepository.create(LanguageCommand.getName(command));
        OperationResult.throwIfFailed(operationResult, ServiceOperationException::new);
    }

    private void handleUpdateCommand(final LanguageUpdateCommand command) {
        final OperationResult operationResult = this.languageRepository.update(LanguageCommand.getId(command),
                                                                               LanguageCommand.getName(command));
        OperationResult.throwIfFailed(operationResult, ServiceOperationException::new);
    }

    private void handleDeleteCommand(final LanguageDeleteCommand command) {
        final OperationResult operationResult = this.languageRepository.delete(LanguageCommand.getId(command));
        OperationResult.throwIfFailed(operationResult, RessourceNotFoundException::new);
    }

    private void handleUndeleteCommand(final LanguageUndeleteCommand command) {
        final OperationResult operationResult = this.languageRepository.undelete(LanguageCommand.getId(command));
        OperationResult.throwIfFailed(operationResult, RessourceNotFoundException::new);
    }
}
