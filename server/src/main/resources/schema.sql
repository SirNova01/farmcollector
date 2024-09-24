CREATE TABLE farm (
    farm_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    farm_name VARCHAR(255),
    location VARCHAR(255)
);

CREATE TABLE crop (
    crop_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    crop_name VARCHAR(255)
);

CREATE TABLE season (
    season_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    season_name VARCHAR(255),
    start_date DATE,
    end_date DATE
);

CREATE TABLE field (
    field_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    farm_id BIGINT,
    field_name VARCHAR(255),
    area_acres DOUBLE,
    FOREIGN KEY (farm_id) REFERENCES farm(farm_id)
);

CREATE TABLE planted (
    planted_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    field_id BIGINT,
    crop_id BIGINT,
    season_id BIGINT,
    planting_area DOUBLE,
    expected_yield DOUBLE,
    FOREIGN KEY (field_id) REFERENCES field(field_id),
    FOREIGN KEY (crop_id) REFERENCES crop(crop_id),
    FOREIGN KEY (season_id) REFERENCES season(season_id)
);

CREATE TABLE harvested (
    harvest_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    field_id BIGINT,
    crop_id BIGINT,
    season_id BIGINT,
    actual_yield DOUBLE,
    FOREIGN KEY (field_id) REFERENCES field(field_id),
    FOREIGN KEY (crop_id) REFERENCES crop(crop_id),
    FOREIGN KEY (season_id) REFERENCES season(season_id)
);
