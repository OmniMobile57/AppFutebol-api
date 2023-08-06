CREATE TABLE `person`
(
    `created_date` datetime(6) DEFAULT NULL,
    `id`           char(36) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `footy_space`
(
    `created_date` datetime(6) DEFAULT NULL,
    `id`           char(36) NOT NULL,
    `person_id`    char(36)     DEFAULT NULL,
    `name`         varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY            `FKcsgg4w0xv1jpk26rcwx0bxkff` (`person_id`),
    CONSTRAINT `FKcsgg4w0xv1jpk26rcwx0bxkff` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `players`
(
    `defeat`         int      NOT NULL,
    `draw`           int      NOT NULL,
    `goals`          int      NOT NULL,
    `score`          double       DEFAULT NULL,
    `victories`      int      NOT NULL,
    `created_date`   datetime(6) DEFAULT NULL,
    `footy_space_id` char(36)     DEFAULT NULL,
    `id`             char(36) NOT NULL,
    `person_id`      char(36)     DEFAULT NULL,
    `name`           varchar(255) DEFAULT NULL,
    `responsibility` enum('ADM','COMMON','OWNER') DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY              `FKckbnhfjy5dqb6wy9wjv1852m2` (`footy_space_id`),
    KEY              `FK5j5g0np9w36bcpjfe6lo5hmxa` (`person_id`),
    CONSTRAINT `FK5j5g0np9w36bcpjfe6lo5hmxa` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
    CONSTRAINT `FKckbnhfjy5dqb6wy9wjv1852m2` FOREIGN KEY (`footy_space_id`) REFERENCES `footy_space` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
