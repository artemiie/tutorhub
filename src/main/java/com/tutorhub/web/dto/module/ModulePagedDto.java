package com.tutorhub.web.dto.module;

public record ModulePagedDto(Integer pageNumber,
                             Integer pageSize,
                             String sortBy
) {
}
