CREATE TABLE `person`
(
    `id`           BINARY(16) NOT NULL,
    `created_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;;

CREATE TABLE `footy_space`
(
    `id`           BINARY(16) NOT NULL,
    `created_date` datetime(6) DEFAULT NULL,
    `person_id`    BINARY(16)     DEFAULT NULL,
    `name`         varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKcsgg4w0xv1jpk26rcwx0bxkff` (`person_id`)
) ENGINE=InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;;

CREATE TABLE `players`
(
    `id`             BINARY(16) NOT NULL,
    `defeat`         int      NOT NULL,
    `draw`           int      NOT NULL,
    `goals`          int      NOT NULL,
    `score`          double       DEFAULT NULL,
    `victories`      int      NOT NULL,
    `created_date`   datetime(6) DEFAULT NULL,
    `footy_space_id` BINARY(16)     DEFAULT NULL,
    `person_id`      BINARY(16)     DEFAULT NULL,
    `name`           varchar(255) DEFAULT NULL,
    `responsibility` enum('ADM','COMMON','OWNER') DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKckbnhfjy5dqb6wy9wjv1852m2` (`footy_space_id`),
    KEY `FK5j5g0np9w36bcpjfe6lo5hmxa` (`person_id`)
) ENGINE=InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;;
