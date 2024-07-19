CREATE TABLE "user"
(
    id             BIGSERIAL PRIMARY KEY,
    user_name      VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password  VARCHAR(255),
    email  VARCHAR(255),
    account_non_expired BOOLEAN,
    account_non_locked BOOLEAN,
    credentials_non_expired BOOLEAN,
    enabled BOOLEAN
);

COMMENT ON TABLE "user" IS 'Таблица пользователей';
COMMENT ON COLUMN "user".id IS 'Уникальный идентификатор пользователя';
COMMENT ON COLUMN "user".user_name IS 'Имя пользователя, должно быть уникальным';
COMMENT ON COLUMN "user".first_name IS 'Имя пользователя';
COMMENT ON COLUMN "user".last_name IS 'Фамилия пользователя';
COMMENT ON COLUMN "user".password IS 'Пароль пользователя';
COMMENT ON COLUMN "user".email IS 'Электронная почта пользователя';
COMMENT ON COLUMN "user".account_non_expired IS 'Признак того, что учетная запись не просрочена';
COMMENT ON COLUMN "user".account_non_locked IS 'Признак того, что учетная запись не заблокирована';
COMMENT ON COLUMN "user".credentials_non_expired IS 'Признак того, что учетные данные не просрочены';
COMMENT ON COLUMN "user".enabled IS 'Признак того, что учетная запись включена';

CREATE TABLE Role
(
    id              SERIAL PRIMARY KEY,
    name        VARCHAR(255) UNIQUE NOT NULL,
    role_description VARCHAR(255)
);

COMMENT ON TABLE Role IS 'Таблица ролей';
COMMENT ON COLUMN Role.id IS 'Уникальный идентификатор роли';
COMMENT ON COLUMN Role.name IS 'Имя роли, должно быть уникальным';
COMMENT ON COLUMN Role.role_description IS 'Описание роли';

CREATE TABLE USER_ROLE
(
    user_id BIGINT,
    role_id INTEGER,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES Role (id) ON DELETE CASCADE
);

COMMENT ON TABLE USER_ROLE IS 'Таблица связи пользователей и ролей';
COMMENT ON COLUMN USER_ROLE.user_id IS 'Внешний ключ на таблицу User';
COMMENT ON COLUMN USER_ROLE.role_id IS 'Внешний ключ на таблицу Role';
