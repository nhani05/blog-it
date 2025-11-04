-- ===========================================================
-- XÓA BẢNG CŨ (theo thứ tự tránh lỗi khóa ngoại)
-- ===========================================================
DROP TABLE IF EXISTS post_tag;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS post_details;
DROP TABLE IF EXISTS post;

DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS user;

-- ===========================================================
-- 1️⃣ BẢNG USER VÀ ROLE
-- ===========================================================
CREATE TABLE user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    display_name VARCHAR(100),
    is_admin BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE role (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    code VARCHAR(255)
);

CREATE TABLE user_role (
    user_id INT,
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (role_id) REFERENCES role(role_id)
);

-- Dữ liệu mẫu
INSERT INTO role (role_id, name, code) VALUES
(1, 'USER', 'USER'),
(2, 'ADMIN', 'ADMIN');

INSERT INTO user (username, email, password_hash, display_name, is_admin) VALUES
('admin', 'admin@blog.com', '123456', 'Quản Trị Viên', TRUE),
('nguyenvanb', 'author@blog.com', '$2a$10$7bXQWivTTjT2GHAAO8uT4uyy8RPr4qwrLzN6yB4bO4QqTVXz3TkSa', 'Tác Giả Code', FALSE),
('nguyenvana', 'abc@blog.com', '$2a$10$7bXQWivTTjT2GHAAO8uT4uyy8RPr4qwrLzN6yB4bO4QqTVXz3TkSa', 'Nguyen Van A', FALSE);

INSERT INTO user_role (user_id, role_id) VALUES
(1, 2), -- admin là ADMIN
(2, 1), -- author là USER
(3, 1);

-- ===========================================================
-- 2️⃣ CATEGORY VÀ TAG
-- ===========================================================
CREATE TABLE category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    slug VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE tag (
    tag_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    slug VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO category (name, slug) VALUES
('Lập Trình Java', 'lap-trinh-java'),
('Spring Boot', 'spring-boot'),
('Cơ Sở Dữ Liệu', 'co-so-du-lieu'),
('Mẹo Vặt Công Nghệ', 'meo-vat-cong-nghe');

INSERT INTO tag (name, slug) VALUES
('JPA', 'jpa'),
('Hibernate', 'hibernate'),
('SQL', 'sql'),
('Performance', 'performance'),
('Backend', 'backend'),
('Tips', 'tips');


-- ===========================================================
-- 4️⃣ POST
-- ===========================================================
CREATE TABLE post (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    author_id INT NOT NULL,
    category_id INT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    excerpt VARCHAR(500),
    slug VARCHAR(255) UNIQUE NOT NULL,
    status ENUM('draft', 'published', 'archived') DEFAULT 'draft' NOT NULL,
    published_at DATETIME,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    view_count INT DEFAULT 0,
    FOREIGN KEY (author_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE SET NULL
);

INSERT INTO post (author_id, category_id, title, content, excerpt, slug, status, published_at, view_count)
VALUES
(1, 2, 'Hướng Dẫn Cấu Hình JPA trong Spring Boot', 
 'Nội dung đầy đủ về cách cấu hình và sử dụng JPA/Hibernate...',
 'Tìm hiểu cách kết nối ứng dụng Spring Boot của bạn với cơ sở dữ liệu bằng JPA.', 
 'huong-dan-cau-hinh-jpa-spring-boot', 
 'published', NOW() - INTERVAL 1 DAY, 125),
(2, 3, 'Tối Ưu Hóa Truy Vấn SQL với Index', 
 'Giải thích chuyên sâu về Index và cách tăng tốc độ truy vấn...', 
 'Làm thế nào để database của bạn chạy nhanh hơn?', 
 'toi-uu-hoa-truy-van-sql-index', 
 'published', NOW() - INTERVAL 5 DAY, 340),
(1, 4, 'Các Lệnh SQL Thường Dùng cho Lập Trình Viên Backend', 
 'Tổng hợp các lệnh SQL cơ bản và nâng cao...', 
 'Tóm tắt nhanh các lệnh SELECT, INSERT, UPDATE, DELETE và JOIN.', 
 'cac-lenh-sql-thuong-dung-backend', 
 'published', NOW(), 50);
 
 -- ===========================================================
-- 3️⃣ POST_DETAILS (phải tạo trước POST vì POST có foreign key tới đây)
-- ===========================================================
CREATE TABLE post_details(
    post_id INT PRIMARY KEY,
    post_introduction MEDIUMTEXT NOT NULL,
    post_content MEDIUMTEXT NOT NULL,
    post_end_content MEDIUMTEXT NOT NULL,
    post_img VARCHAR(1000) NOT NULL,
    post_link VARCHAR(1000) NOT NULL,
	FOREIGN KEY (post_id) REFERENCES post(post_id)
);

INSERT INTO post_details(post_id, post_introduction, post_content, post_end_content, post_img, post_link)
VALUES 
(1, 'Intro 1', 'Content 1', 'End 1', 'img1.jpg', 'link1'),
(2, 'Intro 2', 'Content 2', 'End 2', 'img2.jpg', 'link2'),
(3, 'Intro 3', 'Content 3', 'End 3', 'img3.jpg', 'link3');

-- ===========================================================
-- 5️⃣ POST_TAG (Bảng liên kết N-N)
-- ===========================================================
CREATE TABLE post_tag (
    post_id INT NOT NULL,
    tag_id INT NOT NULL,
    PRIMARY KEY (post_id, tag_id),
    FOREIGN KEY (post_id) REFERENCES post(post_id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tag(tag_id) ON DELETE CASCADE
);

INSERT INTO post_tag (post_id, tag_id) VALUES
(1, 1), (1, 2), (1, 5),
(2, 3), (2, 4),
(3, 3), (3, 5), (3, 6);

-- ===========================================================
-- 6️⃣ COMMENT
-- ===========================================================
CREATE TABLE comment (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT NOT NULL,
    user_id INT,
    content TEXT NOT NULL,
    parent_comment_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('approved', 'pending', 'spam') DEFAULT 'pending',
    FOREIGN KEY (post_id) REFERENCES post(post_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE SET NULL,
    FOREIGN KEY (parent_comment_id) REFERENCES comment(comment_id) ON DELETE CASCADE
);

INSERT INTO comment (post_id, user_id, content, parent_comment_id, status) VALUES
(2, 1,  'Bài viết rất hay và dễ hiểu!', NULL, 'approved'),
(2, 1,'Cảm ơn phản hồi của bạn!', 1, 'approved'),
(1, 2, 'Tôi đang gặp lỗi khi sử dụng @OneToMany trong Entity...', NULL, 'pending');
