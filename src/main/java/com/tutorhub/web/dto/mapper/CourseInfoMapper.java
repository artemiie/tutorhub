package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.course.Course;
import com.tutorhub.model.course.CourseInfo;
import com.tutorhub.model.course.Module;
import com.tutorhub.model.course.Progress;
import com.tutorhub.model.course.Submodule;
import com.tutorhub.model.user.User;
import com.tutorhub.web.dto.course.CourseReadDto;
import com.tutorhub.web.dto.courseinfo.CourseInfoReadDto;
import com.tutorhub.web.dto.module.ModuleReadDto;
import com.tutorhub.web.dto.progress.ProgressReadDto;
import com.tutorhub.web.dto.submodule.SubmoduleReadDto;
import com.tutorhub.web.dto.user.UserReadDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CourseInfoMapper {
  CourseInfoReadDto toDto(CourseInfo courseInfo);

  default CourseInfoReadDto toDto(CourseInfo courseInfo,
                                  List<Progress> progressList) {
    if (courseInfo == null) {
      return null;
    } else {
      Long id = courseInfo.getId();
      UserReadDto user = toDto(courseInfo.getUser());
      CourseReadDto course = toDto(courseInfo.getCourse());
      ProgressReadDto progress = toProgressDto(progressList);

      return new CourseInfoReadDto(id, user, course, progress);
    }
  }

  private UserReadDto toDto(User user) {
    if (user == null) {
      return null;
    } else {
      return new UserReadDto(
          user.getId(),
          user.getFullname(),
          user.getUsername()
      );
    }
  }

  private CourseReadDto toDto(Course course) {
    if (course == null) {
      return null;
    } else {
      return new CourseReadDto(
          course.getId(),
          course.getName(),
          null,
          null
      );
    }
  }

  private ProgressReadDto toProgressDto(
      List<Progress> progressList) {
    if (progressList == null || progressList.isEmpty()) {
      return null;
    } else {
      Map<Module, List<Submodule>> progressByModuleId =
          progressList.stream()
              .collect(
                  Collectors.
                      groupingBy(Progress::getModule,
                          Collectors.mapping(
                              Progress::getSubmodule, Collectors.toList())));

      List<ModuleReadDto> moduleList =
          new ArrayList<>(progressByModuleId.size());

      progressByModuleId.forEach(
          (module, submodules) -> {
            ModuleReadDto moduleDTO = toDto(module, submodules);
            moduleList.add(moduleDTO);
          }
      );

      return new ProgressReadDto(moduleList);
    }
  }

  private ModuleReadDto toDto(Module module, List<Submodule> submoduleList) {
    if (module == null) {
      return null;
    } else {
      return new ModuleReadDto(
          module.getId(),
          module.getName(),
          toSubmoduleDto(submoduleList)
      );
    }
  }

  private List<SubmoduleReadDto> toSubmoduleDto(List<Submodule> submoduleList) {
    if (submoduleList == null) {
      return null;
    } else {
      List<SubmoduleReadDto> list = new ArrayList<>(submoduleList.size());
      for (Submodule submodule : submoduleList) {
        list.add(this.toDto(submodule));
      }
      return list;
    }
  }

  private SubmoduleReadDto toDto(Submodule submodule) {
    if (submodule == null) {
      return null;
    } else {
      return new SubmoduleReadDto(
          submodule.getId(),
          submodule.getName(),
          null
      );
    }
  }
}

