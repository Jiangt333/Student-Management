use stums;
CREATE TABLE `user` (
  `SID` varchar(30) NOT NULL,
  `SPassword` varchar(30) NOT NULL,
  `SName` varchar(20) DEFAULT NULL,
  `SCategory` int DEFAULT NULL,
  `SGender` int DEFAULT NULL,
  `SNation` int DEFAULT NULL,
  `SIDNum` varchar(20) DEFAULT NULL,
  `SYear` datetime DEFAULT NULL,
  `SClass` int DEFAULT NULL,
  `SLevel` int DEFAULT NULL,
  `SOutlook` int DEFAULT NULL,
  `SDorm` varchar(45) DEFAULT NULL,
  `SPlace` varchar(45) DEFAULT NULL,
  `SPhone` varchar(20) DEFAULT NULL,
  `SHome` varchar(45) DEFAULT NULL,
  `ContactName` varchar(20) DEFAULT NULL,
  `ContactPhone` varchar(20) DEFAULT NULL,
  `SWechat` varchar(20) DEFAULT NULL,
  `SMail` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`SID`)
) ;

INSERT INTO `user` VALUES ('21310000','123456','张三峰',1,1,1,'3501252098124034593','2001-01-30 00:00:00',2,2,1,'珠海荔园18号666室','福建省厦门市','18878902234','思明区XX大道XX栋','张二峰','14536778908','ZhangSanFeng2','29876478928@qq.com');
