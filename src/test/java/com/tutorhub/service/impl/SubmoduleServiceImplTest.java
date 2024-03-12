package com.tutorhub.service.impl;

import com.tutorhub.exception.ResourceNotFoundException;
import com.tutorhub.model.course.Submodule;
import com.tutorhub.repository.ContentRepository;
import com.tutorhub.repository.ModuleRepository;
import com.tutorhub.repository.SubmoduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static com.tutorhub.testfactory.ContentTestFactory.getContentTest;
import static com.tutorhub.testfactory.ModuleTestFactory.getModuleTest;
import static com.tutorhub.testfactory.SubmoduleTestFactory.getSubmoduleTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubmoduleServiceImplTest {
  private static final Long COURSE_ID = 1L;
  private static final Long MODULE_ID = 1L;
  private static final Long SUBMODULE_ID = 1L;

  @Mock
  private SubmoduleRepository submoduleRepository;

  @Mock
  private ModuleRepository moduleRepository;

  @Mock
  private ContentRepository contentRepository;

  @InjectMocks
  private SubmoduleServiceImpl submoduleService;

  @Test
  void find() {
    var expectedResult = getSubmoduleTest(SUBMODULE_ID);

    doReturn(Optional.of(expectedResult))
        .when(submoduleRepository)
        .findByModule_Course_IdAndModule_IdAndId(
            COURSE_ID, MODULE_ID, SUBMODULE_ID
        );

    var actualResult =
        submoduleService.find(COURSE_ID, MODULE_ID, SUBMODULE_ID);

    assertEquals(expectedResult, actualResult);

    verify(submoduleRepository)
        .findByModule_Course_IdAndModule_IdAndId(
            COURSE_ID, MODULE_ID, SUBMODULE_ID
        );
  }

  @Test
  void find_whenNotFound() {
    doReturn(Optional.empty())
        .when(submoduleRepository)
        .findByModule_Course_IdAndModule_IdAndId(
            COURSE_ID, MODULE_ID, SUBMODULE_ID
        );

    assertThrows(
        ResourceNotFoundException.class,
        () -> submoduleService.find(COURSE_ID, MODULE_ID, SUBMODULE_ID));

    verify(submoduleRepository)
        .findByModule_Course_IdAndModule_IdAndId(
            COURSE_ID, MODULE_ID, SUBMODULE_ID
        );
  }

  @Test
  void findAllPaged() {
    var expectedResult =
        new PageImpl<>(List.of(getSubmoduleTest(SUBMODULE_ID)));

    var page = PageRequest.of(0, 10);

    doReturn(expectedResult)
        .when(submoduleRepository)
        .findByModule_Course_IdAndModule_Id(COURSE_ID, MODULE_ID, page);

    var actualResult =
        submoduleService.findAllPaged(COURSE_ID, MODULE_ID, page);

    assertAll(
        () -> assertEquals(1, actualResult.get().toList().size()),
        () -> assertEquals(
            expectedResult.get().toList().getFirst(),
            actualResult.get().toList().getFirst())
    );

    verify(submoduleRepository)
        .findByModule_Course_IdAndModule_Id(
            COURSE_ID, MODULE_ID, page
        );
  }

  @Test
  void create() {
    Long CONTENT_ID = 1L;
    var content = getContentTest(CONTENT_ID);

    var expectedResult = getSubmoduleTest(null);
    expectedResult.setContent(content);

    doReturn(true)
        .when(moduleRepository)
        .existsByCourseIdAndId(COURSE_ID, MODULE_ID);

    doReturn(true)
        .when(contentRepository)
        .existsById(CONTENT_ID);

    doAnswer(
        invocationOnMock -> {
          Submodule submodule = invocationOnMock.getArgument(0);
          submodule.setId(SUBMODULE_ID);
          return submodule;
        })
        .when(submoduleRepository)
        .save(expectedResult);

    var actualResult =
        submoduleService.create(COURSE_ID, MODULE_ID, expectedResult);

    assertAll(
        () -> assertNotNull(actualResult),
        () -> assertEquals(SUBMODULE_ID, actualResult.getId()),
        () -> assertEquals(MODULE_ID, actualResult.getModule().getId())
    );

    verify(submoduleRepository).save(expectedResult);
  }

  @Test
  void create_whenModuleDontExists() {
    var expectedResult = getSubmoduleTest(null);

    doReturn(false)
        .when(moduleRepository)
        .existsByCourseIdAndId(COURSE_ID, MODULE_ID);

    assertThrows(
        ResourceNotFoundException.class,
        () -> submoduleService.create(COURSE_ID, MODULE_ID, expectedResult));

    verify(moduleRepository).existsByCourseIdAndId(COURSE_ID, MODULE_ID);
  }

  @Test
  void update() {
    var submoduleOnDb = getSubmoduleTest(SUBMODULE_ID);
    submoduleOnDb.setName("Submodule");
    var module = getModuleTest(MODULE_ID);
    submoduleOnDb.setModule(module);

    doReturn(Optional.of(submoduleOnDb))
        .when(submoduleRepository)
        .findByModule_Course_IdAndModule_IdAndId(
            COURSE_ID, MODULE_ID, SUBMODULE_ID
        );

    when(submoduleRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

    var submodule = getSubmoduleTest(SUBMODULE_ID);
    submodule.setName("New Submodule");

    var actualResult = submoduleService.update(COURSE_ID, MODULE_ID, submodule);

    assertAll(
        () -> assertEquals("New Submodule", actualResult.getName()),
        () -> assertEquals(module, actualResult.getModule())
    );

    verify(submoduleRepository).save(actualResult);
    verify(submoduleRepository)
        .findByModule_Course_IdAndModule_IdAndId(
            COURSE_ID, MODULE_ID, SUBMODULE_ID
        );
  }

  @Test
  void delete() {
    submoduleService.delete(COURSE_ID, MODULE_ID, SUBMODULE_ID);

    verify(submoduleRepository)
        .deleteByModule_Course_IdAndModule_IdAndId(
            COURSE_ID, MODULE_ID, SUBMODULE_ID
        );
  }
}
