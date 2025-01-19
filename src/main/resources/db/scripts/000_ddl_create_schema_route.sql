CREATE TABLE route (
    id UUID PRIMARY KEY,
    data TIMESTAMP NOT NULL,
    point_a VARCHAR(255) NOT NULL,
    point_b VARCHAR(255) NOT NULL,
    travel_time TIME NOT NULL,
    UNIQUE (data, point_a, point_b)
);