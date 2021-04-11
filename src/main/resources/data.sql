truncate loans;
truncate users;

insert into users(user_id,first_name,last_name,email) values (1,'Jose','Casas','pepecasas@gmail.com'),
(2,'Carla','Gimenez','carla_2@gmail.com'),
(3,'Pedro','Lopez','peterlopez@gmail.com'),
(4,'Juan','Gil','jgil@gmail.com'),
(5,'Maria','Becerra','marbe_5@gmail.com'),
(6,'Carlos','Fata','fata6@gmail.com'),
(7,'Julieta','Vargas','juli.vargas_7@gmail.com'),
(8,'Ana','Piatti','ana.p8@gmail.com'),
(9,'Jorge','Massa','george.9@gmail.com'),
(10,'Martina','Perez','martina_10@gmail.com');

insert into loans(loan_id,total,user_id) values (1,2500.55,1),
(2,2500,7),
(3,42464.00,7),
(4,7654,2),
(5,123.25,1),
(6,2342.07,3),
(7,523.33,5),
(8,1.71,8),
(9,4456.96,6),
(10,10203.11,9),
(11,100000.00,8),
(12,12355,10),
(13,123235.29,4),
(14,224.03,4),
(15,1.61,7),
(16,234.00,1),
(17,7856,9),
(18,85632,6),
(19,30000,3),
(20,999999,1);