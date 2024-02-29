package com.tutorhub.service.impl;

import com.tutorhub.exception.ResourceNotFoundException;
import com.tutorhub.model.course.Course;
import com.tutorhub.model.course.CourseInfo;
import com.tutorhub.repository.CourseRepository;
import com.tutorhub.service.CourseInfoService;
import com.tutorhub.service.ProgressService;
import com.tutorhub.service.UserService;
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
import static com.tutorhub.testfactory.UserTestFactory.getUserTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {
  private static final Long COURSE_ID = 1L;
  @Mock
  private CourseRepository courseRepository;
  @Mock
  private UserService userService;
  @Mock
  private CourseInfoService courseInfoService;
  @Mock
  private ProgressService progressService;
  @InjectMocks
  private CourseServiceImpl courseService;

  @Test
  void getById() {
    var expectedResult = getCourseTest(COURSE_ID);

    doReturn(Optional.of(expectedResult)).when(courseRepository).findById(COURSE_ID);

    var actualResult = courseService.find(COURSE_ID);

    assertEquals(expectedResult, actualResult);

    verify(courseRepository).findById(COURSE_ID);
  }

  @Test
  void getById_withNotExistingId() {
    doReturn(Optional.empty()).when(courseRepository).findById(COURSE_ID);

    assertThrows(ResourceNotFoundException.class, () -> courseService.find(COURSE_ID));

    verify(courseRepository).findById(COURSE_ID);
  }

  @Test
  void getAll() {
    var expectedResult = new PageImpl<>(List.of(getCourseTest(COURSE_ID)));

    var page = PageRequest.of(1, 10, Sort.by("name"));

    doReturn(expectedResult).when(courseRepository).findAll(page);

    var actualResult = courseService.findAll(page);

    assertAll(
        () -> assertEquals(1, actualResult.get().toList().size()),
        () ->
            assertEquals(expectedResult.get().toList().get(0), actualResult.get().toList().get(0)));

    verify(courseRepository).findAll(page);
  }

  @Test
  void create() {
    var expectedResult = getCourseTest(null);

    doAnswer(
        invocationOnMock -> {
          Course course = invocationOnMock.getArgument(0);
          course.setId(COURSE_ID);
          return course;
        })
        .when(courseRepository)
        .save(expectedResult);

    var actualResult = courseService.create(expectedResult);

    assertAll(
        () -> assertNotNull(actualResult), () -> assertEquals(COURSE_ID, actualResult.getId()));

    verify(courseRepository).save(expectedResult);
  }

  @Test
  void delete() {
    courseService.delete(COURSE_ID);

    verify(courseRepository).deleteById(COURSE_ID);
  }

  @Test
  void update() {
    var courseOnDb = getCourseTest(COURSE_ID);
    courseOnDb.setName("Course");
    courseOnDb.setCourseOwner(getUserTest(COURSE_ID));
    doReturn(Optional.of(courseOnDb)).when(courseRepository).findById(COURSE_ID);

    when(courseRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

    var course = getCourseTest(COURSE_ID);
    course.setName("New Course");

    var actualResult = courseService.update(course);

    assertAll(
        () -> assertEquals("New Course", actualResult.getName()),
        () -> assertEquals(courseOnDb.getCourseOwner(), actualResult.getCourseOwner()));

    verify(courseRepository).save(actualResult);
    verify(courseRepository).findById(COURSE_ID);
  }

  @Test
  void findByUserId() {
    var expectedResult = List.of(getCourseTest(COURSE_ID));
    long userId = 1;
    doReturn(expectedResult).when(courseRepository).findByCourseOwnerId(userId);

    var actualResult = courseService.findByUserId(COURSE_ID);

    assertAll(
        () -> assertNotNull(actualResult),
        () -> assertEquals(expectedResult.size(), actualResult.size()),
        () -> assertEquals(expectedResult.getFirst().getId(), actualResult.getFirst().getId()),
        () -> assertEquals(expectedResult.getFirst().getName(), actualResult.getFirst().getName()));

    verify(courseRepository).findByCourseOwnerId(userId);
  }

  @Test
  void assignUser() {
    var course = getCourseTest(COURSE_ID);
    doReturn(Optional.of(course)).when(courseRepository).findById(COURSE_ID);

    long userId = 1;
    var user = getUserTest(userId);
    doReturn(user).when(userService).find(userId);

    courseService.assignUser(userId, COURSE_ID);

    CourseInfo courseInfo = new CourseInfo();
    courseInfo.setUser(user);
    courseInfo.setCourse(course);

    verify(courseInfoService).create(courseInfo);
  }
}
