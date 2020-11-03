
INSERT INTO item (`item_name`, `item_des`, `item_price`, `item_image`) VALUES ('Choco Lover', 'The best chocolate loving experience of your life!', '12', 'CupCake_1');
INSERT INTO item (`item_name`, `item_des`, `item_price`, `item_image`) VALUES ('Sour Bomb', 'The sour dream you hate to love!', '13', 'CupCake_2');
INSERT INTO item (`item_name`, `item_des`, `item_price`, `item_image`) VALUES ('Night & Day', 'The best from both worlds. Sweet and dark :)', '13', 'CupCake_3');
INSERT INTO item (`item_name`, `item_des`, `item_price`, `item_image`) VALUES ('Tutti Frutti', 'Fruits have never been so healthy and tasty in a candy ;)', '14', 'CupCake_4');


-- Husk at update jeres database version.
UPDATE properties
SET value = '3'
WHERE name = 'version';
