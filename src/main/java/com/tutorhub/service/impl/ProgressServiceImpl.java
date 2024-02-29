package com.tutorhub.service.impl;

import com.tutorhub.model.course.CourseInfo;
import com.tutorhub.model.course.Module;
import com.tutorhub.model.course.Progress;
import com.tutorhub.model.course.Submodule;
import com.tutorhub.repository.ProgressRepository;
import com.tutorhub.service.CourseInfoService;
import com.tutorhub.service.ModuleService;
import com.tutorhub.service.ProgressService;
import com.tutorhub.service.SubmoduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements ProgressService {
  private final SubmoduleService submoduleService;
  private final ModuleService moduleService;
  private final CourseInfoService courseInfoService;
  private final ProgressRepository progressRepository;

  public void create(final Long userId,
                     final Long courseId,
                     final Long moduleId,
                     final Long submoduleId) {

    Module module = moduleService.find(courseId, moduleId);

    Submodule submodule =
        submoduleService.find(courseId, moduleId, submoduleId);

    CourseInfo courseInfo =
        courseInfoService.findByUserIdAndCourseId(userId, courseId);

    Progress progress = new Progress();

    progress.setCourseInfo(courseInfo);
    progress.setCourseInfoID(courseInfo.getId());

    progress.setSubmodule(submodule);
    progress.setSubmoduleID(submodule.getId());

    progress.setModule(module);
    progress.setModuleID(module.getId());

    progressRepository.save(progress);
  }

  @Override
  public List<Progress> findByCourseInfo(final CourseInfo courseEntity) {
    return progressRepository.findByCourseInfo(courseEntity);
  }
}
