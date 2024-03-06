package com.tutorhub.web.dto.submodule;

public record SubmodulePagedDto(Integer pageNumber,
                                Integer pageSize,
                                String sortBy
) {
}
