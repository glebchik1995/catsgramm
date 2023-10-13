package ru.yandex.practicum.catsgram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.service.FeedService;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.List;

@RestController
@RequestMapping(value = "/feed")
@RequiredArgsConstructor
public class PostFeedController {

    private final FeedService feedService;

    @GetMapping
    List<Post> getFriendsFeed(
            @RequestParam("userId") String userId,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return feedService.getFeedFor(userId, size);
    }
}