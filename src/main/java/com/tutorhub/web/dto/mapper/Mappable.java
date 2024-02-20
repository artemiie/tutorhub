package com.tutorhub.web.dto.mapper;

import java.util.List;

public interface Mappable<E, D> {

  E fromDto(D d);

  List<E> fromDto(List<D> d);

  D toDto(E e);

  List<D> toDto(List<E> e);

  /*default Content map(final ContentDTO d) {
    return Mappers.getMapper(ContentMapper.class).fromDto(d);
  }

  default ContentDTO map(final Content c) {
    return Mappers.getMapper(ContentMapper.class).toDto(c);
  }*/

}
