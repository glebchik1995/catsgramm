package ru.yandex.practicum.catsgram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.dao.FollowDao;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FollowDao feedDao;
    public List<Post> getFeedFor(String userId, Integer size) {
        return feedDao.getFollowFeed(userId, size);
    }
}
