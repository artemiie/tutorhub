package com.tutorhub.service.impl;

import com.tutorhub.model.course.Progress;
import com.tutorhub.repository.ProgressRepository;
import com.tutorhub.service.CourseInfoService;
import com.tutorhub.service.SubmoduleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.tutorhub.testfactory.CourseInfoTestFactory.getCourseInfoTest;
import static com.tutorhub.testfactory.ModuleTestFactory.getModuleTest;
import static com.tutorhub.testfactory.ProgressTestFactory.getProgressTest;
import static com.tutorhub.testfactory.SubmoduleTestFactory.getSubmoduleTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProgressServiceImplTest {
  private static final Long USER_ID = 1L;
  private static final Long MODULE_ID = 1L;
  private static final Long COURSE_ID = 1L;
  private static final Long SUBMODULE_ID = 1L;
  private static final Long COURSE_INFO_ID = 1L;

  @Mock
  private ModuleServiceImpl moduleService;
  @Mock
  private SubmoduleService submoduleService;
  @Mock
  private CourseInfoService courseInfoService;
  @Mock
  private ProgressRepository progressRepository;

  @InjectMocks
  private ProgressServiceImpl progressService;


  @Test
  void findByCourseInfo() {
    var module = getModuleTest(MODULE_ID);
    var submodule = getSubmoduleTest(SUBMODULE_ID);
    var courseInfo = getCourseInfoTest(COURSE_INFO_ID);

    var progress = getProgressTest(module, submodule, courseInfo);

    var expectedResult = List.of(progress);

    doReturn(expectedResult).when(progressRepository).findByCourseInfo(any());

    var actualResult = progressService.findByCourseInfo(any());

    assertAll(
        () -> assertEquals(expectedResult.size(), actualResult.size()),
        () -> assertEquals(expectedResult.getFirst(), actualResult.getFirst())
    );
  }


  @Test
  void create() {
    var module = getModuleTest(MODULE_ID);
    doReturn(module).when(moduleService).find(COURSE_ID, MODULE_ID);

    var submodule = getSubmoduleTest(SUBMODULE_ID);
    doReturn(submodule)
        .when(submoduleService).find(COURSE_ID, MODULE_ID, SUBMODULE_ID);

    var courseInfo = getCourseInfoTest(COURSE_INFO_ID);
    doReturn(courseInfo)
        .when(courseInfoService).findByUserIdAndCourseId(USER_ID, COURSE_ID);

    progressService.create(USER_ID, COURSE_ID, MODULE_ID, SUBMODULE_ID);

    final ArgumentCaptor<Progress> captor =
        ArgumentCaptor.forClass(Progress.class);

    verify(progressRepository).save(captor.capture());

    final Progress savedProgress = captor.getValue();

    assertAll(
        () -> assertNotNull(savedProgress),
        () -> assertEquals(savedProgress.getModule(), module),
        () -> assertEquals(savedProgress.getSubmodule(), submodule),
        () -> assertEquals(savedProgress.getCourseInfo(), courseInfo));
  }
}
