package com.jad.nexaspringhelloworld.service;

import com.jad.nexaspringhelloworld.command.CommandResult;
import com.jad.nexaspringhelloworld.command.language.LanguageCreateCommand;
import com.jad.nexaspringhelloworld.dto.LanguageData;
import com.jad.nexaspringhelloworld.dto.LanguageId;
import com.jad.nexaspringhelloworld.dto.LanguageOutput;
import com.jad.nexaspringhelloworld.entity.LanguageEntity;
import com.jad.nexaspringhelloworld.mapper.LanguageMapper;
import com.jad.nexaspringhelloworld.repository.LanguageRepository;
import com.jad.nexaspringhelloworld.repository.result.StoredProcedureResultWithId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LanguageServiceTest {
    @Mock
    private LanguageRepository languageRepository;

    @Mock
    private LanguageMapper languageMapper;

    @InjectMocks
    private LanguageService languageService;

    @Test
    void findAll_returnEmptyListWhenNoEntityExists() {
        when(this.languageRepository.findAll()).thenReturn(List.of());
        final List<LanguageOutput> result = this.languageService.findAll();
        assertTrue(result.isEmpty(), "Expected empty list when no entities exist");
        verify(this.languageRepository).findAll();
    }


    @Test
    void findAll_returnAllEntitiesInList() {
        LanguageEntity languageEntity1 = mock(LanguageEntity.class);
        LanguageEntity languageEntity2 = mock(LanguageEntity.class);
        LanguageEntity languageEntity3 = mock(LanguageEntity.class);
        LanguageOutput languageOutputExpected1 = new LanguageOutput(new LanguageId(1), new LanguageData("name 1"));
        LanguageOutput languageOutputExpected2 = new LanguageOutput(new LanguageId(2), new LanguageData("name 2"));
        LanguageOutput languageOutputExpected3 = new LanguageOutput(new LanguageId(3), new LanguageData("name 3"));

        when(this.languageRepository.findAll()).thenReturn(List.of(languageEntity1, languageEntity2, languageEntity3));
        when(this.languageMapper.entityToOutput(languageEntity1)).thenReturn(languageOutputExpected1);
        when(this.languageMapper.entityToOutput(languageEntity2)).thenReturn(languageOutputExpected2);
        when(this.languageMapper.entityToOutput(languageEntity3)).thenReturn(languageOutputExpected3);

        final List<LanguageOutput> result = this.languageService.findAll();

        assertEquals(3, result.size(), "Expected list size to match number of entities returned by repository");
        assertEquals(languageOutputExpected1, result.getFirst(),
                     "Expected first output to match mapping of first entity");
        assertEquals(languageOutputExpected2, result.get(1),
                     "Expected second output to match mapping of second entity");
        assertEquals(languageOutputExpected3, result.get(2), "Expected third output to match mapping of third entity");

        verify(this.languageRepository).findAll();
        verify(this.languageMapper).entityToOutput(languageEntity1);
        verify(this.languageMapper).entityToOutput(languageEntity2);
        verify(this.languageMapper).entityToOutput(languageEntity3);
    }

    @Test
    void findById_returnsOutputWhenEntityExists() {
        LanguageEntity languageEntity = mock(LanguageEntity.class);
        LanguageOutput languageOutputExpected = new LanguageOutput(new LanguageId(1), new LanguageData("name"));

        when(this.languageRepository.findById(1)).thenReturn(Optional.ofNullable(languageEntity));
        when(this.languageMapper.entityToOutput(languageEntity)).thenReturn(languageOutputExpected);

        final LanguageOutput result = this.languageService.findById(1);

        assertEquals(languageOutputExpected, result, "Expected output to match mapping of found entity");
        verify(this.languageRepository).findById(1);
        verify(this.languageMapper).entityToOutput(languageEntity);
    }


    @Test
    void findById_throwsWhenEntityNotExists() {
        when(this.languageRepository.findById(1)).thenReturn(Optional.empty());
        RessourceNotFoundException exception = assertThrows(RessourceNotFoundException.class,
                                                            () -> this.languageService.findById(1));
        assertEquals("Language not found: 1", exception.getMessage());
        verify(this.languageRepository).findById(1);
    }

    @Test
    void executeCommandCreate_createsEntityWhenStoredProcedureResultIsSuccess() {
        LanguageData languageData = new LanguageData("Language name");
        LanguageCreateCommand command = new LanguageCreateCommand(languageData);

        StoredProcedureResultWithId storedProcedureResultWithId = mock(StoredProcedureResultWithId.class);
        LanguageEntity languageEntity = mock(LanguageEntity.class);
        LanguageOutput languageOutputExpected = new LanguageOutput(new LanguageId(1), languageData);

        when(this.languageRepository.create("Language name")).thenReturn(storedProcedureResultWithId);
        when(this.languageRepository.findById(1)).thenReturn(Optional.ofNullable(languageEntity));
        when(this.languageMapper.entityToOutput(languageEntity)).thenReturn(languageOutputExpected);

        when(storedProcedureResultWithId.success()).thenReturn(true);
        when(storedProcedureResultWithId.id()).thenReturn(1);

        CommandResult<LanguageOutput> result = this.languageService.executeCommand(command);
        assertEquals(languageOutputExpected, result.payload(), "Expected payload to match mapping of created entity");

        verify(this.languageRepository).create("Language name");
        verify(this.languageRepository).findById(1);
        verify(this.languageMapper).entityToOutput(languageEntity);
        verify(storedProcedureResultWithId).success();
        verify(storedProcedureResultWithId).id();
    }

    @Test
    void executeCommandCreate_throwsEntityWhenStoredProcedureResultFailed() {
    }

    @Test
    void executeCommandUpdate_updatesEntityWhenStoredProcedureResultIsSuccess() {
    }

    @Test
    void executeCommandUpdate_throwsEntityWhenStoredProcedureResultFailed() {
    }

    @Test
    void executeCommandDelete_deletesEntityWhenStoredProcedureResultIsSuccess() {
    }

    @Test
    void executeCommandDelete_throwsEntityWhenStoredProcedureResultFailed() {
    }

    @Test
    void executeCommandUndelete_deletesEntityWhenStoredProcedureResultIsSuccess() {
    }

    @Test
    void executeCommandUndelete_throwsEntityWhenStoredProcedureResultFailed() {
    }

}