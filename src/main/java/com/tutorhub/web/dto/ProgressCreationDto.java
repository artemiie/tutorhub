package com.tutorhub.web.dto;

public record ProgressCreationDto(Long userId,
                                  Long courseId,
                                  Long moduleId,
                                  Long submoduleId) {
}
