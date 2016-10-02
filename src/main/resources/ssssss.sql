CREATE TABLE banners
(
    b_id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    b_url VARCHAR(255) NOT NULL,
    b_show INT(11) DEFAULT '0' NOT NULL,
    b_click INT(11) DEFAULT '0' NOT NULL,
    b_text VARCHAR(255),
    b_pic VARCHAR(255)
);
CREATE TABLE m2m_banners_pages
(
    b_id INT(11) NOT NULL,
    p_id INT(11) NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (b_id, p_id)
);
CREATE TABLE news
(
    n_id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    n_category INT(11) NOT NULL,
    n_header VARCHAR(255) NOT NULL,
    n_text TEXT NOT NULL,
    n_dt DATETIME NOT NULL
);
CREATE TABLE news_categories
(
    nc_id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nc_name VARCHAR(255) NOT NULL
);
CREATE TABLE pages
(
    p_id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    p_parent INT(11),
    p_name VARCHAR(255) NOT NULL
);
CREATE TABLE reviews
(
    r_id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    r_category INT(11) NOT NULL,
    r_header VARCHAR(255) NOT NULL,
    r_text TEXT NOT NULL,
    r_dt DATETIME NOT NULL
);
CREATE TABLE reviews_categories
(
    rc_id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    rc_name VARCHAR(255) NOT NULL
);
ALTER TABLE m2m_banners_pages ADD FOREIGN KEY (b_id) REFERENCES banners (b_id);
ALTER TABLE m2m_banners_pages ADD FOREIGN KEY (p_id) REFERENCES pages (p_id);
CREATE INDEX p_id ON m2m_banners_pages (p_id);
ALTER TABLE news ADD FOREIGN KEY (n_category) REFERENCES news_categories (nc_id);
CREATE INDEX n_category ON news (n_category);
ALTER TABLE pages ADD FOREIGN KEY (p_parent) REFERENCES pages (p_id);
CREATE INDEX p_parent ON pages (p_parent);
ALTER TABLE reviews ADD FOREIGN KEY (r_category) REFERENCES reviews_categories (rc_id);
CREATE INDEX r_category ON reviews (r_category);