package ru.yandex.practicum.catsgram.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.dao.FollowDao;
import ru.yandex.practicum.catsgram.dao.PostDao;
import ru.yandex.practicum.catsgram.dao.UserDao;
import ru.yandex.practicum.catsgram.model.Follow;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FollowDaoImpl implements FollowDao {

    private final JdbcTemplate jdbcTemplate;

    private final UserDao userDao;

    private final PostDao postDao;

    @Override
    public List<Post> getFollowFeed(String userId, Integer size) {
        // получаем все подписки пользователя
        String sql = "SELECT * FROM cat_follow WHERE FOLLOW_ID = ?";
        List<Follow> follows = jdbcTemplate.query(sql, (rs, rowNum) -> makeFollow(rs), userId);

        // выгружаем авторов, на которых подписан пользователь
        Set<User> authors = follows
                .stream()
                .map(Follow::getAuthor)
                .map(userDao::findUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        if (authors.isEmpty()) {
            return Collections.emptyList();
        } else
            // выгрузите и отсортируйте посты полученных выше авторов
            // не забудьте предусмотреть ограничение выдачи
            return authors.stream()
                    .map(postDao::findPostByUser)
                    .flatMap(Collection::stream)
                    .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                    .limit(size)
                    .collect(Collectors.toList());
    }

    private Follow makeFollow(ResultSet rs) throws SQLException {
        return new Follow(rs.getString("author_id"), rs.getString("follower_id"));
    }
}
