package ru.yandex.practicum.catsgram.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedParams {

    String sort;
    Integer size;
    List<String> friendsEmails;

}