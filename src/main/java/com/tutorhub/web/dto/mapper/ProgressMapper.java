package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.course.Content;
import com.tutorhub.model.course.Module;
import com.tutorhub.model.course.Progress;
import com.tutorhub.model.course.Submodule;
import com.tutorhub.web.dto.ContentDTO;
import com.tutorhub.web.dto.ModuleDTO;
import com.tutorhub.web.dto.ProgressDTO;
import com.tutorhub.web.dto.SubmoduleDTO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProgressMapper extends Mappable<Progress, ProgressDTO> {

  default ProgressDTO fromListToSingleDto(List<Progress> e) {
    ProgressDTO progressDTO = new ProgressDTO();

    Map<Module, List<Submodule>> progressByModuleId =
        e.stream()
            .collect(
                Collectors.
                    groupingBy(Progress::getModule,
                        Collectors.mapping(
                            Progress::getSubmodule, Collectors.toList())));

    progressByModuleId.forEach(
        (module, submodules) -> {
          ModuleDTO moduleDTO = toDto(module);
          List<SubmoduleDTO> submoduleDTOList = toDtoSubmodule(submodules);
          moduleDTO.setSubmodules(submoduleDTOList);
          progressDTO.getModuleDTOS().add(moduleDTO);
        }
    );

    return progressDTO;
  }

  private ModuleDTO toDto(Module e) {
    if (e == null) {
      return null;
    } else {
      ModuleDTO moduleDTO = new ModuleDTO();
      moduleDTO.setId(e.getId());
      moduleDTO.setName(e.getName());
      return moduleDTO;
    }
  }

  private List<SubmoduleDTO> toDtoSubmodule(List<Submodule> e) {
    if (e == null) {
      return null;
    } else {
      List<SubmoduleDTO> list = new ArrayList<>(e.size());

      for (Submodule submodule : e) {
        list.add(this.toDto(submodule));
      }

      return list;
    }
  }

  private SubmoduleDTO toDto(Submodule e) {
    if (e == null) {
      return null;
    } else {
      SubmoduleDTO submoduleDTO = new SubmoduleDTO();
      submoduleDTO.setId(e.getId());
      submoduleDTO.setName(e.getName());
      submoduleDTO.setContent(this.contentToContentDTO(e.getContent()));
      return submoduleDTO;
    }
  }

  private ContentDTO contentToContentDTO(Content content) {
    if (content == null) {
      return null;
    } else {
      ContentDTO contentDTO = new ContentDTO();
      contentDTO.setId(content.getId());
      return contentDTO;
    }
  }
}

