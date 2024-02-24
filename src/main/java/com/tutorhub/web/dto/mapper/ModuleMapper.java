package com.tutorhub.web.dto.mapper;

import com.tutorhub.model.course.Module;
import com.tutorhub.web.dto.ModuleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModuleMapper extends Mappable<Module, ModuleDTO> {}
