create table if not exists `sample` (
    `id` int unsigned auto_increment,
    `name_id` int,
    `price_id` int,
    `service_id` int,
    `other_id` int,
    `user_id` int,
    `status` int not null,
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists `sample_name` (
    `id` int unsigned auto_increment,
    `label_id` int,
    `name` varchar(30) not null,
    `description` varchar(500),
    `cover_imgs` LONGTEXT not null,
    `detail_imgs` LONGTEXT not null,
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists `sample_price` (
    `id` int unsigned auto_increment,
    `price` int not null,
    `earnest` int not null,
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists `sample_service` (
    `id` int unsigned auto_increment,
    `provide_models` boolean default false,
    `model_count` varchar(30),
    `model_count_custom` varchar(30),
    `film_count` varchar(30) not null,
    `film_all_send` boolean default false,
    `shoot_duration` varchar(30) not null,
    `shoot_duration_custom` varchar(30) not null,
    `finishing_quantity` varchar(30) not null,
    `shoot_scene_indoor_count` int default 0,
    `shoot_scene_outdoor_count` int default 0,
    `custom_service_detail` Longtext,
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists `sample_service_template` (
    `id` int unsigned auto_increment,
    `user_id` int,
    `provide_models` boolean default false,
    `model_count` varchar(30),
    `model_count_custom` varchar(30),
    `film_count` varchar(30) not null,
    `film_all_send` boolean default false,
    `shoot_duration` varchar(30) not null,
    `shoot_duration_custom` varchar(30) not null,
    `finishing_quantity` varchar(30) not null,
    `shoot_scene_indoor_count` int default 0,
    `shoot_scene_outdoor_count` int default 0,
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists `sample_other` (
    `id` int unsigned auto_increment,
    `is_public` boolean default false,
    `tip` longtext,
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table if not exists `sys_sample_label` (
    `parent_id` int,
    `label_id` int
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists `sample_label` (
    `id` int unsigned auto_increment,
    `parent_id` int,
    `name` varchar(10) not null unique,
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;