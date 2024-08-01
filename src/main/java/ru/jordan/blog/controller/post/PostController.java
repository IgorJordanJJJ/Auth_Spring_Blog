package ru.jordan.blog.controller.post;


import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jordan.blog.dto.blog.PostDto;
import ru.jordan.blog.facade.post.impl.PostFacadeImpl;
import ru.jordan.blog.model.blog.PostSort;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin
@RequiredArgsConstructor
public class PostController {

    private final PostFacadeImpl postFacade;

    @GetMapping
    public ResponseEntity<Page<PostDto>> getAllPaginationPost(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") Integer limit
    ) {
        return new ResponseEntity<>(postFacade.getAllByPageRequest(PageRequest.of(offset, limit)), HttpStatus.OK);
    }

    @GetMapping("Slice")
    public ResponseEntity<Slice<PostDto>> getAllSlice(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") Integer limit
    ) {
        return new ResponseEntity<>(postFacade.getAllSlice(PageRequest.of(offset, limit)), HttpStatus.OK);
    }

    @GetMapping("Sort")
    public ResponseEntity<Page<PostDto>> getAllAndSort(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit,
            @RequestParam("sort") String sortField
    ) {
        return new ResponseEntity<>(postFacade.getAllByPageRequest(
                PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, sortField))
        ), HttpStatus.OK);
    }

    @GetMapping("EnumSort")
    public ResponseEntity<Page<PostDto>> getAllAndEnumSort(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit,
            @RequestParam("sort") PostSort sort
    ) {
        return new ResponseEntity<>(postFacade.getAllByPageRequest(
                PageRequest.of(offset, limit, sort.getSortValue())
        ), HttpStatus.OK);
    }

    @GetMapping("Jpql")
    public ResponseEntity<Page<PostDto>> getAllJpql(
            @RequestParam("title") String title,
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        return new ResponseEntity<>(postFacade.getAllByTitleJpql(title, PageRequest.of(offset, limit)), HttpStatus.OK);
    }

    @GetMapping("SqlNative")
    public ResponseEntity<Page<PostDto>> getAllSqlNative(
            @RequestParam("title") String title,
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        return new ResponseEntity<>(postFacade.getAllByTitleNative(title, PageRequest.of(offset, limit)), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postFacade.getAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        PostDto post = postFacade.getById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<PostDto> getPostByUuid(@PathVariable UUID uuid) {
        PostDto post = postFacade.getByUUID(uuid);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto post) {
        PostDto newPost = postFacade.create(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto post) {
        PostDto updatedPost = postFacade.update(post);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID uuid) {
        postFacade.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
