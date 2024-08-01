package ru.jordan.blog.model.blog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum PostSort {

    ID_ASC(Sort.by(Sort.Direction.ASC, "id")),
    ID_DESC(Sort.by(Sort.Direction.DESC, "id")),
    DATE_ASC(Sort.by(Sort.Direction.ASC, "createOn"));

    private final Sort sortValue;

}
