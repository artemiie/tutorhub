package com.tutorhub.service.impl;

import com.tutorhub.exception.ResourceNotFoundException;
import com.tutorhub.model.course.Module;
import com.tutorhub.repository.ModuleRepository;
import com.tutorhub.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static com.tutorhub.testfactory.CourseTestFactory.getCourseTest;
import static com.tutorhub.testfactory.ModuleTestFactory.getModuleTest;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ModuleServiceImplTest {
  private static final Long MODULE_ID = 1L;
  private static final Long COURSE_ID = 1L;
  @Mock
  private ModuleRepository moduleRepository;
  @Mock
  private CourseService courseService;
  @InjectMocks
  private ModuleServiceImpl moduleService;

  @Test
  void find() {
    var expectedResult = getModuleTest(MODULE_ID);

    doReturn(Optional.of(expectedResult))
        .when(moduleRepository)
        .findByCourseIdAndId(COURSE_ID, MODULE_ID);

    var actualResult = moduleService.find(COURSE_ID, MODULE_ID);

    assertEquals(expectedResult, actualResult);

    verify(moduleRepository).findByCourseIdAndId(COURSE_ID, MODULE_ID);
  }

  @Test
  void findByCourseIdAndId_withNotExistingId() {
    doReturn(Optional.empty())
        .when(moduleRepository)
        .findByCourseIdAndId(COURSE_ID, MODULE_ID);

    assertThrows(
        ResourceNotFoundException.class,
        () -> moduleService.find(COURSE_ID, MODULE_ID));

    verify(moduleRepository).findByCourseIdAndId(COURSE_ID, MODULE_ID);
  }

  @Test
  void findAllPaged() {
    var expectedResult = new PageImpl<>(List.of(getModuleTest(MODULE_ID)));

    var page =
        PageRequest.of(1, 10, Sort.by("name"));

    doReturn(expectedResult)
        .when(moduleRepository)
        .findAllByCourseId(COURSE_ID, page);

    var actualResult = moduleService.findAllPaged(COURSE_ID, page);

    assertAll(
        () -> assertEquals(1, actualResult.get().toList().size()),
        () ->
            assertEquals(expectedResult.get().toList().getFirst(),
                actualResult.get().toList().getFirst()));

    verify(moduleRepository).findAllByCourseId(COURSE_ID, page);
  }

  @Test
  void create() {
    var expectedResult = getModuleTest(null);

    var course = getCourseTest(1L);
    doReturn(course).when(courseService).getById(any());

    doAnswer(
        invocationOnMock -> {
          Module module = invocationOnMock.getArgument(0);
          module.setId(MODULE_ID);
          return module;
        })
        .when(moduleRepository)
        .save(expectedResult);

    var actualResult = moduleService.create(COURSE_ID, expectedResult);

    assertAll(
        () -> assertNotNull(actualResult),
        () -> assertEquals(MODULE_ID, actualResult.getId()),
        () -> assertEquals(course, actualResult.getCourse())
    );

    verify(moduleRepository).save(expectedResult);
  }

  @Test
  void update() {
    var moduleOnDb = getModuleTest(MODULE_ID);
    moduleOnDb.setName("Module");
    moduleOnDb.setCourse(getCourseTest(COURSE_ID));

    doReturn(Optional.of(moduleOnDb))
        .when(moduleRepository)
        .findByCourseIdAndId(COURSE_ID, MODULE_ID);

    when(moduleRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

    var module = getModuleTest(MODULE_ID);
    module.setName("New Module");

    var actualResult = moduleService.update(COURSE_ID, module);

    assertAll(
        () -> assertEquals("New Module", actualResult.getName()),
        () -> assertEquals(moduleOnDb.getCourse(), actualResult.getCourse()));

    verify(moduleRepository).save(actualResult);
    verify(moduleRepository).findByCourseIdAndId(COURSE_ID, MODULE_ID);
  }

  @Test
  void deleteByCourseIdAndModuleId() {
    moduleService.delete(COURSE_ID, MODULE_ID);

    verify(moduleRepository).deleteByCourseIdAndModuleId(COURSE_ID, MODULE_ID);
  }
}
