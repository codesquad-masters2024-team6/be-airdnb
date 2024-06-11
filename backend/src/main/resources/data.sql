INSERT INTO MEMBER(LOGIN_ID, LOGIN_PASSWORD, NICKNAME) VALUES ("test", "test", "test");
INSERT INTO MEMBER(LOGIN_ID, LOGIN_PASSWORD, NICKNAME) VALUES ("test2", "test2", "test2");
INSERT INTO AMENITY(NAME) VALUES ("Air Conditioner");
INSERT INTO AMENITY(NAME) VALUES ("BBQ");
INSERT INTO AMENITY(NAME) VALUES ("CHULBONG");
INSERT INTO AMENITY(NAME) VALUES ("DARAKBANG");
INSERT INTO AMENITY(NAME) VALUES ("ENERGY DRINK");

-- MEMBER table
INSERT INTO `MEMBER` (`LOGIN_ID`, `LOGIN_PASSWORD`, `NICKNAME`) VALUES
('user1', 'password1', 'nickname1'),
('user2', 'password2', 'nickname2');

-- ACCOMMODATION table
INSERT INTO `ACCOMMODATION` (`HOST_ID`, `TITLE`, `PLACE_CATEGORY`, `BASE_PRICE_PER_NIGHT`, `DESCRIPTION`, `MAX_GUEST_COUNT`, `MAX_INFANT_COUNT`, `BEDROOM_COUNT`, `BED_COUNT`, `BATHROOM_COUNT`, `CHECK_IN_TIME`, `CHECK_OUT_TIME`, `COUNTRY`, `PROVINCE`, `CITY`, `DISTRICT`, `STREET_ADDRESS`, `STREET_ADDRESS_DETAIL`, `POSTAL_CODE`, `COORDINATE`) VALUES
(1, 'Cozy Cottage', 'Cottage', 100, 'A cozy cottage in the countryside.', 4, 1, 2, 3, 1, '15:00:00', '10:00:00', 'US', 'California', 'San Francisco', 'District 1', '123 Main St', 'Apt 4', '94101', POINT(37.7749, -122.4194)),
(2, 'Modern Apartment', 'Apartment', 200, 'A modern apartment in the city center.', 2, 0, 1, 1, 1, '16:00:00', '11:00:00', 'US', 'New York', 'New York', 'District 2', '456 Broadway', 'Suite 5B', '10012', POINT(40.7128, -74.0060));

-- AMENITY table
INSERT INTO `AMENITY` (`NAME`) VALUES
('Wi-Fi'),
('Air Conditioning');

-- ACCO_AMEN table
INSERT INTO `ACCO_AMEN` (`ACCO_ID`, `AMEN_ID`) VALUES
(1, 1),
(1, 2),
(2, 1);

-- ACCO_IMAGE table
INSERT INTO `ACCO_IMAGE` (`ACCO_ID`, `URL`) VALUES
(1, 'http://example.com/images/cottage1.jpg'),
(1, 'http://example.com/images/cottage2.jpg'),
(2, 'http://example.com/images/apartment1.jpg');

-- RESERVATION table
INSERT INTO `RESERVATION` (`MEMBER_ID`, `CREATED_AT`, `ADULT_COUNT`, `CHILD_COUNT`, `INFANT_COUNT`, `CHECK_IN_DATE`, `CHECK_OUT_DATE`, `FINAL_PRICE`, `STATUS`) VALUES
(1, NOW(), 2, 1, 1, '2024-07-01', '2024-07-07', 700, 'Confirmed'),
(2, NOW(), 1, 0, 0, '2024-08-01', '2024-08-05', 800, 'Pending');

-- ACCO_PRODUCT table
INSERT INTO `ACCO_PRODUCT` (`ACCO_ID`, `RESERVE_DATE`, `PRICE`, `IS_RESERVED`, `CREATED_AT`, `MODIFIED_AT`) VALUES
(1, '2024-07-01', 100, true, NOW(), NULL),
(1, '2024-07-02', 100, true, NOW(), NULL),
(2, '2024-08-01', 200, false, NOW(), NULL);

-- RESERVATION_PRODUCT table
INSERT INTO `RESERVATION_PRODUCT` (`RESERVE_ID`, `PRODUCT_ID`) VALUES
(1, 1),
(1, 2),
(2, 3);