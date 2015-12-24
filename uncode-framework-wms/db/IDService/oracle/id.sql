CREATE SEQUENCE  SEQ_ID_GENERATOR
 		INCREMENT BY 1
                maxvalue 9999999999
 		START WITH 1000;
 		
CREATE table ID_TMP (
	GEN_ID         NUMBER(32)
);

CREATE or replace PROCEDURE SP_ID_GENERATOR
(
	number_of_ids NUMBER
) 
AS 
 i number(10);

BEGIN
	DELETE FROM ID_TMP WHERE GEN_ID > 0;
	i := 0;
	while i < number_of_ids loop
	insert into ID_TMP values(SEQ_ID_GENERATOR.nextval);
	i := i+1;
	end loop;
END;
/
/* */

