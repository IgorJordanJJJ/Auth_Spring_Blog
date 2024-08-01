package ru.jordan.blog.facade.post.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import ru.jordan.blog.dto.blog.PostDto;
import ru.jordan.blog.facade.post.PostFacade;
import ru.jordan.blog.service.post.PostService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostFacadeImpl implements PostFacade {
    private final PostService postService;


    @Override
    public PostDto create(PostDto postDto) {
        return postService.create(postDto);
    }

    @Override
    public PostDto getById(Long id) {
        return postService.getById(id);
    }

    @Override
    public PostDto getByUUID(UUID uuid) {
        return postService.getByUUID(uuid);
    }

    @Override
    public PostDto update(PostDto postDto) {
        return postService.update(postDto);
    }

    @Override
    public void delete(UUID uuid) {
        postService.delete(uuid);
    }

    @Override
    public List<PostDto> getAll() {
        return postService.getAll();
    }

    @Override
    public List<PostDto> findByTitle(String title) {
        return postService.findByTitle(title);
    }

    @Override
    public Page<PostDto> getAllByPageRequest(PageRequest pageRequest) {
        return postService.getAllByPageRequest(pageRequest);
    }

    @Override
    public Slice<PostDto> getAllSlice(PageRequest pageRequest) {
        return postService.getAllSlice(pageRequest);
    }

    @Override
    public Page<PostDto> getAllByTitleJpql(String title, PageRequest pageRequest) {
        return postService.getAllByTitleJpql(title, pageRequest);
    }

    @Override
    public Page<PostDto> getAllByTitleNative(String title, PageRequest pageRequest) {
        return postService.getAllByTitleNative(title, pageRequest);
    }

}
