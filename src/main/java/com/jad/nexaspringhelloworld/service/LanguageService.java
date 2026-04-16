package com.jad.nexaspringhelloworld.service;

import com.jad.nexaspringhelloworld.command.CommandResult;
import com.jad.nexaspringhelloworld.command.language.*;
import com.jad.nexaspringhelloworld.dto.LanguageId;
import com.jad.nexaspringhelloworld.dto.LanguageOutput;
import com.jad.nexaspringhelloworld.mapper.LanguageMapper;
import com.jad.nexaspringhelloworld.repository.LanguageRepository;
import com.jad.nexaspringhelloworld.repository.result.SimpleStoredProcedureResult;
import com.jad.nexaspringhelloworld.repository.result.StoredProcedureResult;
import com.jad.nexaspringhelloworld.repository.result.StoredProcedureResultWithId;
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
    public List<LanguageOutput> findAll() {
        return this.languageRepository
                .findAll()
                .stream()
                .map(this.languageMapper::entityToOutput)
                .toList();
    }

    public CommandResult<LanguageOutput> executeCommand(final LanguageCommand languageCommand) {
        return switch (languageCommand) {
            case LanguageCreateCommand command -> {
                final LanguageId id = this.handleCreateCommand(command);
                yield CommandResult.withPayLoad(this.findById(id));
            }
            case LanguageUpdateCommand command -> {
                final LanguageId id = this.handleUpdateCommand(command);
                yield CommandResult.withPayLoad(this.findById(id));
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

    private LanguageId handleCreateCommand(final LanguageCreateCommand command) {
        final StoredProcedureResultWithId storedProcedureResultWithId = this.languageRepository.create(
                LanguageCommand.getName(command));
        StoredProcedureResult.throwIfFailed(storedProcedureResultWithId, ServiceOperationException::new);
        return new LanguageId(StoredProcedureResultWithId.getId(storedProcedureResultWithId));
    }

    private LanguageOutput findById(final LanguageId languageId) {
        return this.findById(LanguageId.getId(languageId));
    }

    private LanguageId handleUpdateCommand(final LanguageUpdateCommand command) {
        final SimpleStoredProcedureResult simpleStoredProcedureResult = this.languageRepository.update(
                LanguageCommand.getId(command),
                LanguageCommand.getName(command));
        StoredProcedureResult.throwIfFailed(simpleStoredProcedureResult, ServiceOperationException::new);
        return command.id();
    }

    private void handleDeleteCommand(final LanguageDeleteCommand command) {
        final SimpleStoredProcedureResult simpleStoredProcedureResult = this.languageRepository.delete(
                LanguageCommand.getId(command));
        StoredProcedureResult.throwIfFailed(simpleStoredProcedureResult, RessourceNotFoundException::new);
    }

    private void handleUndeleteCommand(final LanguageUndeleteCommand command) {
        final SimpleStoredProcedureResult simpleStoredProcedureResult = this.languageRepository.undelete(
                LanguageCommand.getId(command));
        StoredProcedureResult.throwIfFailed(simpleStoredProcedureResult, RessourceNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public LanguageOutput findById(final Integer id) {
        return this.languageRepository
                .findById(id)
                .map(this.languageMapper::entityToOutput)
                .orElseThrow(() -> new RessourceNotFoundException("Language not found: " + id));
    }
}
