CREATE TABLE app_user
(
    id         BIGSERIAL PRIMARY KEY,
    name       varchar(50) NOT NULL,
    age        int         NOT NULL,
    created_at timestamp   NOT NULL,
    updated_at timestamp   NOT NULL
);
