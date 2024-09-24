-- Insert prepopulated data for farms
INSERT INTO farm (farm_id, farm_name, location) VALUES (1, 'MyFarm', 'Test Location');

-- Insert prepopulated data for crops
INSERT INTO crop (crop_id, crop_name) VALUES (1, 'Corn');
INSERT INTO crop (crop_id, crop_name) VALUES (2, 'Potato');

-- Insert prepopulated data for seasons
INSERT INTO season (season_id, season_name, start_date, end_date) VALUES (1, 'Spring 2024', '2024-03-01', '2024-06-01');

-- Insert prepopulated data for fields
INSERT INTO field (field_id, farm_id, field_name, area_acres) VALUES (1, 1, 'Field 1', 5.0);

-- Insert prepopulated data for planted crops
INSERT INTO planted (planted_id, field_id, crop_id, season_id, planting_area, expected_yield)
VALUES (1, 1, 1, 1, 5.0, 10.0);  -- Corn

-- Insert prepopulated data for harvested crops
INSERT INTO harvested (harvest_id, field_id, crop_id, season_id, actual_yield)
VALUES (1, 1, 1, 1, 9.0);  -- Corn, actual yield
