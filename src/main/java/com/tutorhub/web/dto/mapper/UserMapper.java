package com.tutorhub.web.dto.mapper;

// import com.tutorhub.model.Student;
// import com.tutorhub.model.Tutor;
import com.tutorhub.model.User;
import com.tutorhub.web.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDTO> {

  /* default User fromDto(final UserDTO d) {
    if (d == null) {
      return null;
    } else if (d instanceof TutorDTO) {
      return Mappers.getMapper(TutorMapper.class).fromDto((TutorDTO) d);
    } else if (d instanceof StudentDTO) {
      return Mappers.getMapper(StudentMapper.class).fromDto((StudentDTO) d);
    } else {
      return null;
    }
  }

  default UserDTO toDto(final User e) {
    if (e == null) {
      return null;
    } else if (e instanceof Student) {
      return Mappers.getMapper(StudentMapper.class).toDto((Student) e);
    } else if (e instanceof Tutor) {
      return Mappers.getMapper(TutorMapper.class).toDto((Tutor) e);
    } else {
      return null;
    }
  }*/

}
