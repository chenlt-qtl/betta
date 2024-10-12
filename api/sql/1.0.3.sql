ALTER TABLE eng_word CHANGE ph_am ph_us varchar(200) NULL COMMENT '美式音标';
ALTER TABLE eng_word CHANGE ph_an_mp3 ph_us_mp3 varchar(1000) NULL COMMENT '美式音频位置';

alter table eng_word add column ph_uk varchar(200)  NULL COMMENT '英式音标' after ph_us;
ALTER TABLE eng_word add column ph_uk_mp3 varchar(1000)  NULL COMMENT '英式音频位置' after ph_uk;

--已执行至此--
ALTER TABLE eng_word CHANGE ph_us_mp3 ph_mp3 varchar(1000) NULL COMMENT '音频位置';
ALTER TABLE eng_word CHANGE ph_us phonetics varchar(200) NULL COMMENT '美式音标';


alter table eng_word drop column ph_uk_mp3;
alter table eng_word drop column ph_uk;