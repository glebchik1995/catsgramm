package ru.yandex.practicum.catsgram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.service.PostService;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.Collection;

@RestController
@RequestMapping(value = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public Collection<Post> findAll(@RequestParam("userId") String userId) {
        return postService.findPostsByUser(userId);
    }


//    @GetMapping
//    public List<Post> findAll(
//            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
//            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
//            @RequestParam(value = "sort", defaultValue = DESCENDING_ORDER, required = false) String sort
//    ) {
//        if (!SORTS.contains(sort)) {
//            throw new IncorrectParameterException("sort");
//        }
//        if (page < 0) {
//            throw new IncorrectParameterException("page");
//        }
//        if (size <= 0) {
//            throw new IncorrectParameterException("size");
//        }
//
//        Integer from = page * size;
//        return postService.findAllPosts(size, from, sort);
//    }

//    @GetMapping(value = "/{postId}")
//    public Post findPost(@PathVariable(value = "postId") Long postId) {
//        return postService.findPostById(postId);
//    }

//    @PostMapping(value = "/post")
//    public Post create(@RequestBody Post post) {
//        return postService.create(post);
//    }

}

