PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;

-- 1. TOURS TABLE
CREATE TABLE IF NOT EXISTS Tours(
  id char(4) PRIMARY KEY,
  name varchar(30),
  type varchar(30),
  day date,
  startTime char(8),
  duration int,
  region varchar(30),
  leavesFrom varchar(30),
  price int,
  imgUrl varchar(255),
  capacity int,
  minNrTravelers int,
  nrBookings int,
  full boolean DEFAULT 0,
  cancelled boolean DEFAULT 0
);

INSERT OR REPLACE INTO Tours VALUES('DT01', 'Northern lights Tour', 'Northern Lights', '2026-04-23', '22:00:00', 210, 'Capital', 'BSÍ', 8999, 'url', 40, 15, 25, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT02', 'Northern lights Tour', 'Northern Lights', '2026-04-24', '22:00:00', 210, 'Capital', 'BSÍ', 8999, 'url', 40, 15, 40, 1, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT03', 'Northern lights Tour', 'Northern Lights', '2026-04-25', '22:00:00', 210, 'Capital', 'BSÍ', 8999, 'url', 40, 15, 10, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT04', 'Northern lights Tour', 'Northern Lights', '2026-04-26', '22:00:00', 210, 'Capital', 'BSÍ', 8999, 'url', 40, 15, 10, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT05','Northern lights Small Group Tour', 'Northern Lights', '2026-04-23', '22:00:00', 210, 'Capital', 'BSÍ', 14499, 'url', 20, 10, 20, 1, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT06','Northern lights Small Group Tour', 'Northern Lights', '2026-04-24', '22:00:00', 210, 'Capital', 'BSÍ', 14499, 'url', 20, 10, 18, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT07','Northern lights Small Group Tour', 'Northern Lights', '2026-04-25', '22:00:00', 210, 'Capital', 'BSÍ', 14499, 'url', 20, 10, 15, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT08','Northern lights Small Group Tour', 'Northern Lights', '2026-04-26', '22:00:00', 210, 'Capital', 'BSÍ', 14499, 'url', 20, 10, 9, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT09','Northern lights Tour', 'Northern Lights', '2026-04-25', '22:00:00', 240, 'South', 'Selfoss', 8999, 'url', 40, 15, 40, 1, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT10','Northern lights Tour', 'Northern Lights', '2026-04-26', '22:00:00', 240, 'South', 'Selfoss', 8999, 'url', 40, 15, 15, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT11','Northern lights Tour', 'Northern Lights', '2026-04-27', '22:00:00', 240, 'South', 'Selfoss', 8999, 'url', 40, 15, 5, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT12','Northern lights Tour', 'Northern Lights', '2026-04-28', '22:00:00', 240, 'South', 'Selfoss', 8999, 'url', 40, 15, 25, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT13','Golden Circle Tour', 'Golden Circle', '2026-04-30', '09:00:00', 360, 'South', 'BSÍ', 10599, 'url', 50, 20, 10, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT14','Golden Circle Tour', 'Golden Circle', '2026-04-30', '11:00:00', 360, 'South', 'BSÍ', 10599, 'url', 50, 20, 25, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT15','Golden Circle Tour', 'Golden Circle', '2026-05-01', '09:00:00', 360, 'South', 'BSÍ', 10599, 'url', 50, 20, 49, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT16','Golden Circle Tour', 'Golden Circle', '2026-05-01', '11:00:00', 360, 'South', 'BSÍ', 10599, 'url', 50, 20, 27, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT17','Golden Circle Evening Tour', 'Golden Circle', '2026-04-30', '17:00:00', 360, 'South', 'BSÍ', 10599, 'url', 40, 15, 38, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT18','Golden Circle Evening Tour', 'Golden Circle', '2026-05-01', '17:00:00', 360, 'South', 'BSÍ', 10599, 'url', 40, 15, 40, 1, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT19','Golden Circle Evening Tour', 'Golden Circle', '2026-05-02', '17:00:00', 360, 'South', 'BSÍ', 10599, 'url', 40, 15, 17, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT20','Golden Circle Evening Tour', 'Golden Circle', '2026-05-03', '17:00:00', 360, 'South', 'BSÍ', 10599, 'url', 40, 15, 14, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT21','Landmannalaugar Super Jeep Tour', 'Jeep Tour', '2026-05-10', '09:00:00', 480, 'Highlands', 'Hella Bus Stop', 38740, 'url', 30, 15, 25, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT22','Landmannalaugar Super Jeep Tour', 'Jeep Tour', '2026-05-10', '11:00:00', 480, 'Highlands', 'Hella Bus Stop', 38740, 'url', 30, 15, 30, 1, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT23','Landmannalaugar Super Jeep Tour', 'Jeep Tour', '2026-05-11', '09:00:00', 480, 'Highlands', 'Hella Bus Stop', 38740, 'url', 30, 15, 20, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT24','Landmannalaugar Super Jeep Tour', 'Jeep Tour', '2026-05-11', '11:00:00', 480, 'Highlands', 'Hella Bus Stop', 38740, 'url', 30, 15, 15, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT25','Þórsmörk Super Jeep Tour', 'Jeep Tour', '2026-04-23', '10:00:00', 360, 'Highlands', 'Brú Base Camp', 35835, 'url', 30, 15, 30, 1, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT26','Þórsmörk Super Jeep Tour', 'Jeep Tour', '2026-04-23', '11:00:00', 360, 'Highlands', 'Brú Base Camp', 35835, 'url', 30, 15, 28, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT27','Þórsmörk Super Jeep Tour', 'Jeep Tour', '2026-04-24', '10:00:00', 360, 'Highlands', 'Brú Base Camp', 35835, 'url', 30, 15, 17, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT28','Þórsmörk Super Jeep Tour', 'Jeep Tour', '2026-04-24', '11:00:00', 360, 'Highlands', 'Brú Base Camp', 35835, 'url', 30, 15, 23, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT29','Þórsmörk Hiking', 'Hiking', '2026-06-23', '08:30:00', 360, 'Highlands', 'Brú Base Camp', 29000, 'url', 30, 15, 30, 1, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT30','Þórsmörk Hiking', 'Hiking', '2026-06-25', '08:30:00', 360, 'Highlands', 'Brú Base Camp', 29000, 'url', 30, 15, 24, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT31','Þórsmörk Hiking', 'Hiking', '2026-06-27', '08:30:00', 360, 'Highlands', 'Brú Base Camp', 29000, 'url', 30, 15, 24, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT32','Þórsmörk Hiking', 'Hiking', '2026-06-30', '08:30:00', 360, 'Highlands', 'Brú Base Camp', 29000, 'url', 30, 15, 21, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT33','Grænihryggur Hiking', 'Hiking Tour', '2026-06-23', '07:00:00', 840, 'Highlands', 'BSÍ', 28999, 'url', 20, 10, 17, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT34','Grænihryggur Hiking', 'Hiking Tour', '2026-06-25', '07:00:00', 840, 'Highlands', 'BSÍ', 28999, 'url', 20, 10, 20, 1, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT35','Grænihryggur Hiking', 'Hiking Tour', '2026-06-27', '07:00:00', 840, 'Highlands', 'BSÍ', 28999, 'url', 20, 10, 5, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT36','Grænihryggur Hiking', 'Hiking Tour', '2026-06-30', '07:00:00', 840, 'Highlands', 'BSÍ', 28999, 'url', 20, 10, 16, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT37','Whale Watching from Reykjavík', 'Whale Watching', '2026-04-25', '10:00:00', 180, 'Capital', 'Reykjavík Harbour', 13990, 'url', 40, 15, 40, 1, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT38','Whale Watching from Reykjavík', 'Whale Watching', '2026-04-25', '13:00:00', 180, 'Capital', 'Reykjavík Harbour', 13990, 'url', 40, 15, 36, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT39','Whale Watching from Reykjavík', 'Whale Watching', '2026-04-27', '10:00:00', 180, 'Capital', 'Reykjavík Harbour', 13990, 'url', 40, 15, 25, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT40','Whale Watching from Reykjavík', 'Whale Watching', '2026-04-27', '13:00:00', 180, 'Capital', 'Reykjavík Harbour', 13990, 'url', 40, 15, 23, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT41','Whale Watching in Skjálfandi', 'Whale Watching', '2026-04-25', '14:00:00', 180, 'North', 'Húsavík Harbour', 12990, 'url', 40, 15, 36, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT42','Whale Watching in Skjálfandi', 'Whale Watching', '2026-04-26', '11:00:00', 180, 'North', 'Húsavík Harbour', 12990, 'url', 40, 15, 40, 1, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT43','Whale Watching in Skjálfandi', 'Whale Watching', '2026-04-26', '14:00:00', 180, 'North', 'Húsavík Harbour', 12990, 'url', 40, 15, 33, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT44','Whale Watching in Skjálfandi', 'Whale Watching', '2026-04-27', '14:00:00', 180, 'North', 'Húsavík Harbour', 12990, 'url', 40, 15, 37, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT45','Akureyri Horse Riding', 'Horse Tour', '2026-04-24', '13:00:00', 180, 'North', 'Hótel KEA', 27900, 'url', 20, 12, 17, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT46','Akureyri Horse Riding', 'Horse Tour', '2026-05-01', '13:00:00', 180, 'North', 'Hótel KEA', 27900, 'url', 20, 12, 20, 1, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT47','Akureyri Horse Riding', 'Horse Tour', '2026-05-08', '13:00:00', 180, 'North', 'Hótel KEA', 27900, 'url', 20, 12, 13, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT48','Akureyri Horse Riding', 'Horse Tour', '2026-05-15', '13:00:00', 180, 'North', 'Hótel KEA', 27900, 'url', 20, 12, 5, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT49','Relax and Ride', 'Horse Tour', '2026-05-01', '13:00:00', 90, 'East', 'Finnsstaðir', 23900, 'url', 5, 2, 5, 1, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT50','Relax and Ride', 'Horse Tour', '2026-05-01', '15:00:00', 90, 'East', 'Finnsstaðir', 23900, 'url', 5, 2, 5, 1, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT51','Relax and Ride', 'Horse Tour', '2026-05-05', '13:00:00', 90, 'East', 'Finnsstaðir', 23900, 'url', 5, 2, 3, 0, 0 );
INSERT OR REPLACE INTO Tours VALUES('DT52','Relax and Ride', 'Horse Tour', '2026-05-05', '15:00:00', 90, 'East', 'Finnsstaðir', 23900, 'url', 5, 2, 5, 1, 0 );


-- 2. TRAVELER TABLE
CREATE TABLE IF NOT EXISTS Travelers(
  id char(4) PRIMARY KEY,
  name varchar(50),
  phoneNR varchar(20),
  email varchar(40)
);

INSERT OR IGNORE INTO Travelers VALUES('0001','Jan Jansen', '111-1111', 'jan@gmail.com');
INSERT OR IGNORE INTO Travelers VALUES('0002','Ken Kenson', '222-222', 'kenson@gmail.com');
INSERT OR IGNORE INTO Travelers VALUES('0003','Lara Larson', '333-3333', 'lalak@gmail.com');
INSERT OR IGNORE INTO Travelers VALUES('0004','Mads Madson', '444-4444', 'mass@gmail.com');


-- 3. FAVORITES TABLE
CREATE TABLE IF NOT EXISTS Favorites(
  travelerID char(4) REFERENCES Travelers(id),
  tourID TEXT REFERENCES Tours(id),
  PRIMARY KEY(travelerID, tourID)
);

INSERT OR IGNORE INTO Favorites VALUES('0001','DT05');
INSERT OR IGNORE INTO Favorites VALUES('0001','DT06');
INSERT OR IGNORE INTO Favorites VALUES('0002','DT18');
INSERT OR IGNORE INTO Favorites VALUES('0002','DT27');
INSERT OR IGNORE INTO Favorites VALUES('0003','DT52');


-- 4. BOOKINGS TABLE
CREATE TABLE IF NOT EXISTS Bookings(
  id char(20) PRIMARY KEY,
  travelerID char(4) REFERENCES Travelers(id),
  tourID TEXT REFERENCES Tours(id),
  nrTickets int,
  totalPrice int,
  hotelPickup boolean,
  pickupLocation varchar(40),
  bookingStatus varchar(10)
);

COMMIT;