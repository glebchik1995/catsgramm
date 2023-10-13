package ru.yandex.practicum.catsgram.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.Service.UserService;
import ru.yandex.practicum.catsgram.dao.PostDao;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class PostDaoImpl implements PostDao {

    private final UserService userService;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Collection<Post> findPostByUser(User user) {
        String sql = "select * from cat_post where author_id = ? order by creation_date desc";
        return jdbcTemplate.query(sql, (rs, rowNum) -> makePost(user, rs), user.getId());
    }

    private Post makePost(User user, ResultSet rs) throws SQLException {
        // используем конструктор, методы ResultSet
        // и готовое значение user
        Integer id = rs.getInt("id");
        String description = rs.getString("description");
        String photoUrl = rs.getString("photo_url");
        // Получаем дату и конвертируем её из sql.Date в time.LocalDate
        LocalDate creationDate = rs.getDate("creation_date").toLocalDate();

        return new Post(id, user, creationDate, description, photoUrl);
    }
}
