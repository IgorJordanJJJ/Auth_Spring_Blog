package ru.jordan.blog.service.post.impl;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jordan.blog.dto.blog.PostDto;
import ru.jordan.blog.model.blog.Post;
import ru.jordan.blog.repository.PostRepository;
import ru.jordan.blog.service.post.PostService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;


    @Override
    public PostDto create(PostDto postDto) {
        if(postRepository.existsByTitle(postDto.getTitle())){
            throw new RuntimeException("Пост с таким заголовком уже существует");
        }
        Post post = modelMapper.map(postDto, Post.class);
        postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto getById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Пост с таким id не найден"));;
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto getByUUID(UUID uuid) {
        Post post = postRepository.findByUuid(uuid).orElseThrow(() -> new RuntimeException("Пост с таким uuid не найден"));;
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto update(PostDto postDto) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {
        if(!postRepository.existsByUuid(uuid)){
            throw new RuntimeException("Пост с таким uuid не существует");
        }
        postRepository.deleteByUuid(uuid);
    }

    @Override
    public List<PostDto> getAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findByTitle(String title) {
        if(!postRepository.existsByTitle(title)){
            throw new RuntimeException("Пост с таким заголовком не существует");
        }
        List<Post> posts = postRepository.findByTitle(title);
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<PostDto> getAllByPageRequest(PageRequest pageRequest) {
        Page<Post> posts = postRepository.findAll(pageRequest);
        return posts.map(post -> modelMapper.map(post, PostDto.class));
    }

    @Override
    public Slice<PostDto> getAllSlice(PageRequest pageRequest) {
        Slice<Post> posts = postRepository.findAllSlice(pageRequest).orElseThrow(() -> new RuntimeException("Посты не найдены"));
        return posts.map(post -> modelMapper.map(post, PostDto.class));
    }

    @Override
    public Page<PostDto> getAllByTitleJpql(String title, PageRequest pageRequest) {
        Page<Post> posts = postRepository.findAllByTitleJpql(title, pageRequest).orElseThrow(() -> new RuntimeException("Посты не найдены"));
        return posts.map(post -> modelMapper.map(post, PostDto.class));
    }

    @Override
    public Page<PostDto> getAllByTitleNative(String title, PageRequest pageRequest) {
        Page<Post> posts = postRepository.findAllByTitleNative(title, pageRequest).orElseThrow(() -> new RuntimeException("Посты не найдены"));
        return posts.map(post -> modelMapper.map(post, PostDto.class));
    }


}
