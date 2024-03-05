package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.course.Content;
import com.tutorhub.model.course.Module;
import com.tutorhub.model.course.Progress;
import com.tutorhub.model.course.Submodule;
import com.tutorhub.web.dto.content.ContentDTO;
import com.tutorhub.web.dto.module.ModuleReadDto;
import com.tutorhub.web.dto.progress.ProgressReadDto;
import com.tutorhub.web.dto.submodule.SubmoduleReadDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProgressMapper {

  default ProgressReadDto fromListToSingleDto(List<Progress> e) {
    ProgressReadDto progressDTO = null; //new ProgressDTO();

    Map<Module, List<Submodule>> progressByModuleId =
        e.stream()
            .collect(
                Collectors.
                    groupingBy(Progress::getModule,
                        Collectors.mapping(
                            Progress::getSubmodule, Collectors.toList())));

    progressByModuleId.forEach(
        (module, submodules) -> {
          ModuleReadDto moduleDTO = toDto(module);
          List<SubmoduleReadDto> submoduleDTOList = toDtoSubmodule(submodules);
          //moduleDTO.setSubmodules(submoduleDTOList);
          //progressDTO.getModuleDTOS().add(moduleDTO);
        }
    );

    return progressDTO;
  }

  private ModuleReadDto toDto(Module e) {
    if (e == null) {
      return null;
    } else {
      ModuleReadDto moduleDTO = null; //new ModuleDTO();
      //moduleDTO.setId(e.getId());
      //moduleDTO.setName(e.getName());
      return moduleDTO;
    }
  }

  private List<SubmoduleReadDto> toDtoSubmodule(List<Submodule> e) {
    if (e == null) {
      return null;
    } else {
      List<SubmoduleReadDto> list = new ArrayList<>(e.size());

      for (Submodule submodule : e) {
        list.add(this.toDto(submodule));
      }

      return list;
    }
  }

  private SubmoduleReadDto toDto(Submodule e) {

    /*public SubmoduleDTO toDto(Submodule e) {
      if (e == null) {
        return null;
      } else {
        Long id = null;
        String name = null;
        id = e.getId();
        name = e.getName();
        String contentUrl = null;
        SubmoduleDTO submoduleDTO = new SubmoduleDTO(id, name,
         (String)contentUrl);
        return submoduleDTO;
      }
    }*/


    if (e == null) {
      return null;
    } else {
      //SubmoduleDTO submoduleDTO = new SubmoduleDTO();
      //submoduleDTO.setId(e.getId());
      //submoduleDTO.setName(e.getName());
      //submoduleDTO.setContent(this.contentToContentDTO(e.getContent()));
      return null; //submoduleDTO;
    }
  }

  private ContentDTO contentToContentDTO(Content content) {
    if (content == null) {
      return null;
    } else {
      ContentDTO contentDTO = null; // new ContentDTO();
      //contentDTO.setId(content.getId());
      return contentDTO;
    }
  }
}

