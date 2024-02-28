package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.course.Submodule;
import com.tutorhub.web.dto.SubmoduleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubmoduleMapper extends Mappable<Submodule, SubmoduleDTO> {}
