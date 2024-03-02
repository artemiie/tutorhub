package com.tutorhub.service.impl;

import static com.tutorhub.testfactory.ModuleTestFactory.getModuleTest;
import static com.tutorhub.testfactory.SubmoduleTestFactory.getSubmoduleTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tutorhub.exception.ResourceNotFoundException;
import com.tutorhub.model.course.Submodule;
import com.tutorhub.repository.SubmoduleRepository;
import com.tutorhub.service.ModuleService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
  public class SubmoduleServiceImplTest {
    private static final Long COURSE_ID = 1L;
    private static final Long MODULE_ID = 1L;
    private static final Long SUBMODULE_ID = 1L;

    @Mock
    private SubmoduleRepository submoduleRepository;

    @Mock
    private ModuleService moduleService;

    @InjectMocks
    private SubmoduleServiceImpl submoduleService;

    @Test
    void find() {
      var expectedResult = getSubmoduleTest(SUBMODULE_ID);

      doReturn(Optional.of(expectedResult))
          .when(submoduleRepository)
          .findByModule_Course_IdAndModule_IdAndId(COURSE_ID, MODULE_ID, SUBMODULE_ID);

      var actualResult = submoduleService.find(COURSE_ID, MODULE_ID, SUBMODULE_ID);

      assertEquals(expectedResult, actualResult);

      verify(submoduleRepository).findByModule_Course_IdAndModule_IdAndId(COURSE_ID, MODULE_ID, SUBMODULE_ID);
    }

    @Test
    void find_whenNotFound() {
      doReturn(Optional.empty())
          .when(submoduleRepository)
          .findByModule_Course_IdAndModule_IdAndId(COURSE_ID, MODULE_ID, SUBMODULE_ID);

      assertThrows(
          ResourceNotFoundException.class,
          () -> submoduleService.find(COURSE_ID, MODULE_ID, SUBMODULE_ID));

      verify(submoduleRepository).findByModule_Course_IdAndModule_IdAndId(COURSE_ID, MODULE_ID, SUBMODULE_ID);
    }

    @Test
    void findAllPaged() {
      var expectedResult = new PageImpl<>(List.of(getSubmoduleTest(SUBMODULE_ID)));

      var page = PageRequest.of(0, 10);

      doReturn(expectedResult)
          .when(submoduleRepository)
          .findByModule_Course_IdAndModule_Id(COURSE_ID, MODULE_ID, page);

      var actualResult = submoduleService.findAllPaged(COURSE_ID, MODULE_ID, page);

      assertAll(
          () -> assertEquals(1, actualResult.get().toList().size()),
          () -> assertEquals(expectedResult.get().toList().get(0), actualResult.get().toList().get(0))
      );

      verify(submoduleRepository).findByModule_Course_IdAndModule_Id(COURSE_ID, MODULE_ID, page);
    }

    @Test
    void create() {
      var expectedResult = getSubmoduleTest(null);
      var module = getModuleTest(MODULE_ID);

      doReturn(module).when(moduleService).find(COURSE_ID, MODULE_ID);
      doAnswer(
          invocationOnMock -> {
            Submodule submodule = invocationOnMock.getArgument(0);
            submodule.setId(SUBMODULE_ID);
            return submodule;
          })
          .when(submoduleRepository)
          .save(expectedResult);

      var actualResult = submoduleService.create(COURSE_ID, MODULE_ID, expectedResult);

      assertAll(
          () -> assertNotNull(actualResult),
          () -> assertEquals(SUBMODULE_ID, actualResult.getId()),
          () -> assertEquals(module, actualResult.getModule())
      );

      verify(submoduleRepository).save(expectedResult);
    }

    @Test
    void update() {
      var submoduleOnDb = getSubmoduleTest(SUBMODULE_ID);
      submoduleOnDb.setName("Submodule");
      var module = getModuleTest(MODULE_ID);
      submoduleOnDb.setModule(module);

      doReturn(Optional.of(submoduleOnDb))
          .when(submoduleRepository)
          .findByModule_Course_IdAndModule_IdAndId(COURSE_ID, MODULE_ID, SUBMODULE_ID);

      when(submoduleRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

      var submodule = getSubmoduleTest(SUBMODULE_ID);
      submodule.setName("New Submodule");

      var actualResult = submoduleService.update(COURSE_ID, MODULE_ID, submodule);

      assertAll(
          () -> assertEquals("New Submodule", actualResult.getName()),
          () -> assertEquals(module, actualResult.getModule())
      );

      verify(submoduleRepository).save(actualResult);
      verify(submoduleRepository).findByModule_Course_IdAndModule_IdAndId(COURSE_ID, MODULE_ID, SUBMODULE_ID);
    }

    @Test
    void delete() {
      submoduleService.delete(COURSE_ID, MODULE_ID, SUBMODULE_ID);

      verify(submoduleRepository).deleteByModule_Course_IdAndModule_IdAndId(COURSE_ID, MODULE_ID, SUBMODULE_ID);
    }
}
