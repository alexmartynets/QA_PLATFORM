# Документация JM QA

## Сущности

### User

#### Поля:

- **id** - уникальный идентификационный номер пользователя
- **fullname** - полное имя пользователя
- **password** - пароль
- **persist_date** - дата регистрации
- **role_id** - идентификационный номер пользователя
- **last_redaction_date** - дата последней регистрации
- **email** - адрес электронной почты
- **about** - краткая информация о пользователе
- **city** - город пользователя
- **link_site** - ссылка на сайт
- **link_github** - ссылка на github
- **link_vk** - ссылка на страницу во Вконтакте
- **reputation_count** - счетчик репутации
- **is_enable** - отметка, что учетная запись не заблокирована
- **image** - фото пользователя
```
Пользователь может задавать вопросы, отвечать на вопросы и давать комментарии к вопросам и ответам.
Наделен ролью.Может помечать понравившиеся вопросы, отмечать вопросы которые были полезны. Заданный
вопрос может отметить, как решенный, указав на ответ, который помог решить проблему.
```

### Role

#### Поля:

- **id** - уникальный идентификационный номер роли
- **name** - имя роли
```
Определяет порядок прав действий пользователя в системе.
```

### Question

#### Поля:

- **id** - уникальный идентификационный номер вопроса
- **title** - заголовок вопроса
- **description** - описание вопроса
- **persist_date** - дата публикации вопроса
- **view_count** - количество просмотров
- **user_id** - идентификационный номер пользователя, опубликовавший вопрос
- **count_valuable** - счетчик “полезности” вопроса, т. е. сколько пользователей посчитали вопрос полезным,
отметив его
```
Сущность, которую инициализирует пользователь для публикации своего вопроса. Имеет заголовок, который кратко 
описывает суть вопроса, развернутое описание, с возможностью вставки фрагмента кода. Может быть помечен полями
“решен”, “любимый вопрос”. Отметка “решен” проставляется автором вопроса, с указанием ответа, который помог
решить возникший вопрос. Отметка “любимый вопрос” ставиться любым пользователем, который посчитал вопрос
актуальным и интересным. ”Тэг” проставляется автором вопроса, для классификации вопроса. Под вопросом может
также быть оставлен комментарий любым пользователем, включая автора вопроса.
```

### Answer

#### Поля:

- **id** - уникальный идентификационный номер ответа
- **user_id** - идентификационный номер пользователя, который опубликовал ответ
- **question_id** - идентификационный номер вопроса, к которому относиться ответ
- **body** - тело ответа
- **persist_date** - дата публикации ответа
- **is_helpful** - отметка о том, что именно этот ответ помог решить вопрос, к которому оно относиться. Эту
отметку может ставить только автор вопроса.
- **date_accept** - дата решения вопроса
- **count_valuable** - счетчик “полезности” ответа. Количество людей, которые отметили данный ответ полезным.
```
Сущность, которую инициализирует пользователь отвечая на вопрос. Привязан к сущности question. Ответ на
вопрос может оставлять любой пользователь. Может быть предложено несколько вариантов ответов на опубликованный
вопрос. Ответ может быть помечен автором вопроса, к которому был оставлен ответ, как “решение помогло”,
обозначая тем самым, что сам вопрос решён и помог прямо или косвенно данный ответ. Под ответом пользователи
могут оставлять комментарии, которые уточняют или дополняют предложенное решение. Каждый пользователь может
оставлять под вопросом только один ответ.
```

### Comment

#### Поля:

- **id** - уникальный идентификационный номер комментария/
- **user_id** - идентификационный номер пользователя, который оставил комментарий
- **text** - содержание комментария
- **persist_date** - дата публикации комментария
- **last_redaction_date** - дата последней редакции
- **comment_type** - тип комментария. Идентифицирует родительскую сущность, к которой был оставлен комментарий
(вопрос или ответ)
```
Комментарий оставляется пользователем под любым вопросом или ответом, для уточнения или дополнения к основному
посту.
```

### User_favorite_question

#### Поля:

- **id** - уникальный идентификационный номер записи об отмеченном вопросе
- **persist_date** - дата постановки отметки “понравившейся вопрос”
- **user_id** - идентификационный номер пользователя, который отметил вопрос, как понравившийся
- **question_id** - идентификационный номер вопроса, который пользователь отметил, как понравившейся
```
Отметка понравившегося вопроса проставляется пользователем, который счел вопрос интересным и/или полезным.
```

### Tag

#### Поля:

- **id** - уникальный идентификационный номер тэга
- **name** - название тэга
- **description** - описание тэга
- **persist_date** - дата создания тэга
```
Ставиться у сущности question для классификации вопроса.
```

### Related_tags

#### Поля:

- **child_tag** - идентификационный номер дочернего тэга
- **main_tag** - идентификационный номер родительского тэга
```
Категоризация тэгов. Показывает взаимосвязь общего с конкретным запросом. Например тэг “База данных” будет
иметь более широкую область запросов, в то время как тэг “Hibernate” будет более предметную область, которая
одновременно подходит под широкое использование тэга “База данных”.
```

### Tag_has_question

#### Поля

- **tag_id** - идентификационный номер тэга
- **question_id** - идентификационный номер вопроса
- **persist_date** - дата отметки вопроса тэгом
```                                                  
Сущность, которая отражает, помечен ли вопрос тэгом.
```
[Схема](https://dbdiagram.io/d/5ea77c8839d18f5553fe515d)
