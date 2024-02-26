package com.tutorhub.service.impl;

import static com.tutorhub.testfactory.CourseTestFactory.getCourseTest;
import static com.tutorhub.testfactory.UserTestFactory.getUserTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tutorhub.exception.ResourceNotFoundException;
import com.tutorhub.model.course.Course;
import com.tutorhub.repository.CourseRepository;
import com.tutorhub.service.CourseInfoService;
import com.tutorhub.service.ProgressService;
import com.tutorhub.service.UserService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {
  private static final Long ID = 1L;
  @Mock private CourseRepository courseRepository;
  @Mock private UserService userService;
  @Mock private CourseInfoService courseInfoService;
  @Mock private ProgressService progressService;
  @InjectMocks private CourseServiceImpl courseService;

  @Test
  void getById() {
    var expectedResult = getCourseTest(ID);

    doReturn(Optional.of(expectedResult)).when(courseRepository).findById(ID);

    var actualResult = courseService.getById(ID);

    assertEquals(expectedResult, actualResult);

    verify(courseRepository).findById(ID);
  }

  @Test
  void getById_withNotExistingId() {
    doReturn(Optional.empty()).when(courseRepository).findById(ID);

    assertThrows(ResourceNotFoundException.class, () -> courseService.getById(ID));

    verify(courseRepository).findById(ID);
  }

  @Test
  void getAll() {
    var expectedResult = new PageImpl<>(List.of(getCourseTest(ID)));

    var page = PageRequest.of(1, 10, Sort.by("name"));

    doReturn(expectedResult).when(courseRepository).findAll(page);

    var actualResult = courseService.getAll(page);

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
              course.setId(ID);
              return course;
            })
        .when(courseRepository)
        .save(expectedResult);

    var actualResult = courseService.create(expectedResult);

    assertAll(() -> assertNotNull(actualResult), () -> assertEquals(ID, actualResult.getId()));

    verify(courseRepository).save(expectedResult);
  }

  @Test
  void delete() {
    courseService.delete(ID);

    verify(courseRepository).deleteById(ID);
  }

  @Test
  void update() {
    var courseOnDb = getCourseTest(ID);
    courseOnDb.setName("Course");
    courseOnDb.setCourseOwner(getUserTest(ID));
    doReturn(Optional.of(courseOnDb)).when(courseRepository).findById(ID);

    when(courseRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

    var course = getCourseTest(ID);
    course.setName("New Course");

    var actualResult = courseService.update(course);

    assertAll(
        () -> assertEquals("New Course", actualResult.getName()),
        () -> assertEquals(courseOnDb.getCourseOwner(), actualResult.getCourseOwner()));

    verify(courseRepository).save(actualResult);
    verify(courseRepository).findById(ID);
  }

  @Test
  void findByUserId() {
    var expectedResult = List.of(getCourseTest(ID));

    doReturn(expectedResult).when(courseRepository).findByCourseOwnerId(ID);

    var actualResult = courseService.findByUserId(ID);

    assertAll(
        () -> assertNotNull(actualResult),
        () -> assertEquals(expectedResult.size(), actualResult.size()),
        () -> assertEquals(expectedResult.getFirst().getId(), actualResult.getFirst().getId()),
        () -> assertEquals(expectedResult.getFirst().getName(), actualResult.getFirst().getName()));

    verify(courseRepository).findByCourseOwnerId(ID);
  }
}
