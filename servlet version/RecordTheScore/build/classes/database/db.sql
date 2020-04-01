drop table if exists admin;

drop table if exists classes;

drop table if exists score;

drop table if exists student;

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   adminId              int not null auto_increment,
   adminName varchar(200),
   adminPass varchar(200),
   adminDesc text,
   primary key (adminId)
);

/*==============================================================*/
/* Table: classes                                               */
/*==============================================================*/
create table classes
(
   classesId            int not null auto_increment,
   classesName varchar(200),
   classesCreateTime dateTime,
   classesDesc text,
   adminId              int,
   primary key (classesId)
);

/*==============================================================*/
/* Table: score                                                 */
/*==============================================================*/
create table score
(
   scoreId              int not null auto_increment,
   scoreNumber int,
   classesId            int,
   studentId            int,
   primary key (scoreId)
);

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   studentId            int not null auto_increment,
   studentName varchar(100),
   studentEmail varchar(200),
   studentAddr text,
   studentPhone varchar(100),
   studentGender varchar(100),
   studentHeadImage text,
   studentBirthday date,
   studentDescribe text,
   classesId            int,
   primary key (studentId)
);

alter table classes add constraint FK_Reference_4 foreign key (adminId)
      references admin (adminId) on delete cascade on update cascade;

alter table score add constraint FK_Reference_2 foreign key (classesId)
      references classes (classesId) on delete cascade on update cascade;

alter table score add constraint FK_Reference_3 foreign key (studentId)
      references student (studentId) on delete cascade on update cascade;

alter table student add constraint FK_Reference_1 foreign key (classesId)
      references classes (classesId) on delete cascade on update cascade;
