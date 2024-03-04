insert into country(id, taxationRule, taxAmount, taxRate) values (1, 'GENERAL_RATE', null, '0.01');

INSERT INTO trader(country_id) values (1);