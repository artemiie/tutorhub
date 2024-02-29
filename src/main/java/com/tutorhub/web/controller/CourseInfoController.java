package com.tutorhub.web.controller;

import com.tutorhub.model.course.CourseInfo;
import com.tutorhub.model.course.Progress;
import com.tutorhub.service.CourseInfoService;
import com.tutorhub.service.ProgressService;
import com.tutorhub.web.dto.CourseInfoDTO;
import com.tutorhub.web.dto.ProgressDTO;
import com.tutorhub.web.dto.mapper.CourseInfoMapper;
import com.tutorhub.web.dto.mapper.ProgressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course-infos")
public class CourseInfoController {
  private final CourseInfoService courseInfoService;
  private final CourseInfoMapper courseInfoMapper;
  private final ProgressService progressService;
  private final ProgressMapper progressMapper;

  @GetMapping
  public CourseInfoDTO find(
      @RequestParam final Long userId,
      @RequestParam final Long courseId) {
    CourseInfo courseInfo =
        courseInfoService.findByUserIdAndCourseId(userId, courseId);

    List<Progress> progress = progressService.findByCourseInfo(courseInfo);
    ProgressDTO progressDTO = progressMapper.fromListToSingleDto(progress);

    CourseInfoDTO courseInfoDTO = courseInfoMapper.toDto(courseInfo);
    courseInfoDTO.setProgress(progressDTO);
    return courseInfoDTO;
  }
}
