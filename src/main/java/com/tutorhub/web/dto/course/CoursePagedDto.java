package com.tutorhub.web.dto.course;

public record CoursePagedDto(Long userId,
                             Integer pageNumber,
                             Integer pageSize,
                             String sortBy
) {
}
