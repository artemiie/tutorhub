package com.tutorhub.web.dto.progress;

public record ProgressCreationDto(Long userId,
                                  Long courseId,
                                  Long moduleId,
                                  Long submoduleId) {
}
