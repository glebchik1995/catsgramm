package ru.yandex.practicum.catsgram.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {

    Integer id;
    final User author; // автор
    final LocalDate creationDate; // дата создания
    String description; // описание
    String photoUrl; // url-адрес фотографии
}
