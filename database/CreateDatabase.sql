-- Users table
CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- UserRoles table
CREATE TABLE UserRoles (
    role_id INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

-- News table
CREATE TABLE News (
    news_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    author_id INT,
    published_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES Users(user_id)
);

-- Comments table
CREATE TABLE Comments (
    comment_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    news_id INT,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (news_id) REFERENCES News(news_id)
);

-- Statistics table
CREATE TABLE Statistics (
    stat_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    news_id INT,
    view_count INT DEFAULT 0,
    like_count INT DEFAULT 0,
    comment_count INT DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (news_id) REFERENCES News(news_id)
);

-- NewsFilters table
CREATE TABLE NewsFilters (
    filter_id INT PRIMARY KEY AUTO_INCREMENT,
    filter_name VARCHAR(100) NOT NULL,
    filter_value VARCHAR(100) NOT NULL
);

-- UserProfiles table
CREATE TABLE UserProfiles (
    profile_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    bio TEXT,
    avatar_url VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Relationships and additional constraints
ALTER TABLE Users ADD CONSTRAINT FK_Role FOREIGN KEY (role_id) REFERENCES UserRoles(role_id);
