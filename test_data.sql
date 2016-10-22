insert into foodtype (ftname) values ('Pasta Plates'),('Starters'),('Salads'),('Chef''s Specials'),('Fast Food'),('Seafood Specials');

insert into picture (pname, ppath)
values ('404','upload/404.jpg'),('10','images/10.jpg'),('11','images/11.jpg'),('12','images/12.jpg'),('13','images/13.jpg'),('14','images/14.jpg'),('15','images/15.jpg'),('4','images/4.jpg'),('5','images/5.jpg'),('6','images/6.jpg'),('7','images/7.jpg'),('8','images/8.jpg'),('9','images/9.jpg'),('a-1','images/a-1.jpg'),('banner1','images/banner1.jpg'),('banner2','images/banner2.jpg'),('banner3','images/banner3.jpg'),('bg-1','images/bg-1.png'),('bg-2','images/bg-2.png'),('bg-3','images/bg-3.png'),('bg-header','images/bg-header.png'),('bg','images/bg.png'),('chef-1','images/chef-1.jpg'),('chef-2','images/chef-2.jpg'),('chef-3','images/chef-3.jpg'),('chef-4','images/chef-4.jpg'),('chef','images/chef.jpg'),('fast-food','images/fast-food.jpg'),('logo','images/logo.png'),('Mail','images/Mail.png'),('menu','images/menu.jpg'),('Phone','images/Phone.png'),('preview','images/preview.jpg'),('reservation','images/reservation.jpg');

insert into food (ftid, pid, fname, fdetial, fprice) 
values ('1','4','food1','food1_detial','99'),('1','5','food2','food2_detial','99'),('1','6','food3','food3_detial','99'),('2','7','food4','food4_detial','99'),('2','8','food5','food5_detial','99'),('2','9','food6','food6_detial','99'),('3','10','food7','food7_detial','99'),('3','11','food8','food8_detial','99'),('3','12','food9','food9_detial','99'),('4','13','food10','food10_detial','99'),('4','14','food11','food11_detial','99'),('4','15','food12','food12_detial','99'),('5','4','food13','food13_detial','99'),('5','5','food14','food14_detial','99'),('5','6','food15','food15_detial','99'),('6','7','food16','food16_detial','99'),('6','8','food17','food17_detial','99'),('6','9','food18','food18_detial','99');

insert into users (uname, upassword, utype) values ('admin', 'admin', 1);
insert into users (uname, utelphone) values ('user1', '15512345678'), ('user2', '18876543210');
insert into users (uname, utype, upic)
values ('chef1', '2', 'images/chef-1.jpg'),
('chef2', '2', 'images/chef-2.jpg'),
('chef3', '2', 'images/chef-3.jpg'),
('chef4', '2', 'images/chef-4.jpg')

insert into reservation (uid, restitle, resdate, resinfo, resseat)
values (2, 'birthday part', '2016-10-1 19:00:00', 'sth...', '10'),(3, 'supper', '2017-1-1 5:18:20', 'sth...', '2');

insert into news (nauthor, ndate, ncontent, ntag)
values ('欢迎品尝新菜品', '2016-5-8', '桔子味甘酸、性温，入肺。主要治胸隔结气、呕逆少食、胃阴不足、口中干渴、肺热咳嗽及饮酒过度。具有开胃、止渴润肺的功效。桔子营养也十分丰富，一个桔子就几乎满足人体一天中所需的维生素C含量。并且桔子中含有170余种植物化合物和60余种黄酮类化合物，其中的大多数物质均是天然抗氧化剂。', '哦哦'),
('推广软文', '2016-9-27', '米饭是我们日常生活中最为常见的一种主食，人们一日三餐大概总有一顿是吃米饭的。米，可以说是五谷之首，提供给人们丰富的碳水化合物，让人有足够的精力和体力。虽然米饭是日常餐桌上的主角，但是如何把稀松平常的食物吃得丰富多彩？快跟小美一起来看看吧！', '呵呵');

insert into homepage (pic, title, contents, type) values
('upload/404.jpg','banner1','test info','banner1'),('images/banner2.jpg','banner2','test info','banner2'),('images/banner3.jpg','banner3','test info','banner3'),('','前面是我是我加的','我们的口号是没有蛀牙！！','slogan'),('images/menu.jpg','主页','test info','content1'),('images/fast-food.jpg','菜单','test info','content2'),('images/reservation.jpg','动态','test info','content3'),('images/chef.jpg','员工','test info','content4'),('images/preview.jpg','预订','test info','content5'),('images/reservation.jpg','画廊','test info','content6');
