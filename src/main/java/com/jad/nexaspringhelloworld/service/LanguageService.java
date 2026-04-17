package com.jad.nexaspringhelloworld.service;

import com.jad.nexaspringhelloworld.command.CommandResult;
import com.jad.nexaspringhelloworld.command.language.*;
import com.jad.nexaspringhelloworld.dto.LanguageId;
import com.jad.nexaspringhelloworld.dto.LanguageOutput;
import com.jad.nexaspringhelloworld.mapper.LanguageMapper;
import com.jad.nexaspringhelloworld.repository.LanguageRepository;
import com.jad.nexaspringhelloworld.repository.result.PersistenceOperationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Slf4j
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
        LanguageService.log.debug("Listing all languages.");
        return this.languageRepository
                .findAll()
                .stream()
                .map(this.languageMapper::entityToOutput)
                .toList();
    }

    public CommandResult<LanguageOutput> executeCommand(final LanguageCommand languageCommand) {
        return switch (languageCommand) {
            case LanguageCreateCommand command -> {
                LanguageService.log.debug("Language's creation : {}",
                                          LanguageCommand.getName(command));
                final LanguageId id = this.handleCreateCommand(command);
                yield CommandResult.withPayLoad(this.findById(id));
            }
            case LanguageUpdateCommand command -> {
                LanguageService.log.debug("Language's update : {} {}",
                                          LanguageCommand.getId(command),
                                          LanguageCommand.getName(command));
                final LanguageId id = this.handleUpdateCommand(command);
                yield CommandResult.withPayLoad(this.findById(id));
            }
            case LanguageDeleteCommand command -> {
                LanguageService.log.debug("Language's delete : {}",
                                          LanguageCommand.getId(command));
                this.handleDeleteCommand(command);
                yield CommandResult.noPayLoad();
            }
            case LanguageUndeleteCommand command -> {
                LanguageService.log.debug("Language's undelete : {}",
                                          LanguageCommand.getId(command));
                this.handleUndeleteCommand(command);
                yield CommandResult.noPayLoad();
            }
        };
    }

    private LanguageId handleCreateCommand(final LanguageCreateCommand command) {
        final PersistenceOperationResult persistenceOperationResult = this.languageRepository.create(
                LanguageCommand.getName(command));
        LanguageService.throwIfFailed(persistenceOperationResult, ServiceOperationException::new);
        return new LanguageId(persistenceOperationResult.realId());
    }

    private LanguageOutput findById(final LanguageId languageId) {
        return this.findById(LanguageId.getId(languageId));
    }

    private LanguageId handleUpdateCommand(final LanguageUpdateCommand command) {
        final PersistenceOperationResult persistenceOperationResult = this.languageRepository.update(
                LanguageCommand.getId(command),
                LanguageCommand.getName(command));
        LanguageService.throwIfFailed(persistenceOperationResult, ServiceOperationException::new);
        return command.id();
    }

    private void handleDeleteCommand(final LanguageDeleteCommand command) {
        final PersistenceOperationResult persistenceOperationResult = this.languageRepository.delete(
                LanguageCommand.getId(command));
        LanguageService.throwIfFailed(persistenceOperationResult, RessourceNotFoundException::new);
    }

    private void handleUndeleteCommand(final LanguageUndeleteCommand command) {
        final PersistenceOperationResult persistenceOperationResult = this.languageRepository.undelete(
                LanguageCommand.getId(command));
        LanguageService.throwIfFailed(persistenceOperationResult, RessourceNotFoundException::new);
    }

    private static void throwIfFailed(PersistenceOperationResult persistenceOperationResult,
                                      Function<String, ? extends RuntimeException> exceptionFactory) {
        if (!persistenceOperationResult.success()) {
            LanguageService.log.error(persistenceOperationResult.message());
            throw exceptionFactory.apply(persistenceOperationResult.message());
        }
    }

    @Transactional(readOnly = true)
    public LanguageOutput findById(final Integer id) {
        LanguageService.log.debug("Looking up language  with id={}", id);
        return this.languageRepository
                .findById(id)
                .map(this.languageMapper::entityToOutput)
                .orElseThrow(() -> new RessourceNotFoundException("Language not found: " + id));
    }
}
