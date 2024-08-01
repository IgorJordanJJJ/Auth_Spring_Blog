package ru.jordan.blog.service.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import ru.jordan.blog.dto.blog.PostDto;

import java.util.List;
import java.util.UUID;

public interface PostService {
    /**
     * Создание нового поста.
     *
     * @param postDto данные для создания поста
     * @return созданный пост
     */
    PostDto create(PostDto postDto);

    /**
     * Получение поста по ID.
     *
     * @param id ID поста
     * @return postDto с найденным постом, или пустой, если пост не найден
     */
    PostDto getById(Long id);

    /**
     * Получение поста по ID.
     *
     * @param uuid UUID поста
     * @return postDto с найденным постом, или пустой, если пост не найден
     */
    PostDto getByUUID(UUID uuid);

    /**
     * Обновление существующей поста.
     *
     * @param postDto данные для обновления поста
     * @return обновленный пост
     * @throws IllegalArgumentException если пост с указанным ID не найдена
     */
    PostDto update(PostDto postDto);

    /**
     * Удаление поста по ID.
     *
     * @param uuid UUID поста для удаления
     * @throws IllegalArgumentException если пост с указанным ID не найдена
     */
    void delete(UUID uuid);

    /**
     * Получение всех постов.
     *
     * @return список всех постов
     */
    List<PostDto> getAll();

    /**
     * Поиск постов по заголовоку.
     *
     * @param title часть имени для поиска постов
     * @return список постов, найденных по имени
     */
    List<PostDto> findByTitle(String title);

    /**
     * Получение всех постов с учетом параметров пагинации.
     *
     * @param pageRequest параметры пагинации (номер страницы и размер страницы)
     * @return страница с постами
     */
    Page<PostDto> getAllByPageRequest(PageRequest pageRequest);

    /**
     * Получение всех постов в виде среза с учетом параметров пагинации.
     *
     * @param pageRequest параметры пагинации (номер страницы и размер страницы)
     * @return срез постов
     */
    Slice<PostDto> getAllSlice(PageRequest pageRequest);

    /**
     * Получение всех постов по заголовку с использованием JPQL с учетом параметров пагинации.
     *
     * @param pageRequest параметры пагинации (номер страницы и размер страницы)
     * @param title       заголовок поста
     * @return страница с постами
     */
    Page<PostDto> getAllByTitleJpql(String title, PageRequest pageRequest);

    /**
     * Получение всех постов по заголовку с использованием нативного SQL с учетом параметров пагинации.
     *
     * @param pageRequest параметры пагинации (номер страницы и размер страницы)
     * @param title       заголовок поста
     * @return страница с постами
     */
    Page<PostDto> getAllByTitleNative(String title, PageRequest pageRequest);
}
