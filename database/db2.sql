DROP TABLE IF EXISTS post_tag;
DROP TABLE IF EXISTS tag;

DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS user;

CREATE TABLE user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    display_name VARCHAR(100),
    is_admin BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

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

CREATE TABLE comment (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT NOT NULL,
    user_id INT, -- Có thể NULL nếu bình luận là của Khách (Guest)
    author_name VARCHAR(100),
    author_email VARCHAR(100),
    content TEXT NOT NULL,
    parent_comment_id INT, -- Dùng cho các comment trả lời (Replies)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('approved', 'pending', 'spam') DEFAULT 'pending',

    FOREIGN KEY (post_id) REFERENCES post(post_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE SET NULL,
    FOREIGN KEY (parent_comment_id) REFERENCES comment(comment_id) ON DELETE CASCADE
);
CREATE TABLE post_tag (
    post_id INT NOT NULL,
    tag_id INT NOT NULL,
    
    PRIMARY KEY (post_id, tag_id),
    
    FOREIGN KEY (post_id) REFERENCES post(post_id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tag(tag_id) ON DELETE CASCADE
);


INSERT INTO user (username, email, password_hash, display_name, is_admin) VALUES
('admin', 'admin@blog.com', '123456', 'Quản Trị Viên', TRUE),
('author', 'author@blog.com', '$2a$10$w4C3sD9a.tY2mX1zQ7vF8e0h.aB6cD5fE4gH3iJ2kL1mN0oPqR7', 'Tác Giả Code', FALSE);


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

INSERT INTO post (author_id, category_id, title, content, excerpt, slug, status, published_at, view_count) VALUES
(
    1, -- admin
    2, -- Spring Boot
    'Hướng Dẫn Cấu Hình JPA trong Spring Boot', 
    'Nội dung đầy đủ về cách cấu hình và sử dụng JPA/Hibernate trong dự án Spring Boot, bao gồm cả các thuộc tính DDL-auto.', 
    'Tìm hiểu cách kết nối ứng dụng Spring Boot của bạn với cơ sở dữ liệu bằng JPA.', 
    'huong-dan-cau-hinh-jpa-spring-boot', 
    'published', 
    NOW() - INTERVAL 1 DAY, -- Xuất bản 1 ngày trước
    125
),
(
    2, -- author
    3, -- Cơ Sở Dữ Liệu
    'Tối Ưu Hóa Truy Vấn SQL với Index', 
    'Giải thích chuyên sâu về tầm quan trọng của Index và cách thiết lập Index hiệu quả để tăng tốc độ truy vấn trên các bảng lớn.', 
    'Làm thế nào để database của bạn chạy nhanh hơn? Câu trả lời nằm ở Index!', 
    'toi-uu-hoa-truy-van-sql-index', 
    'published', 
    NOW() - INTERVAL 5 DAY, -- Xuất bản 5 ngày trước
    340
),
(
    1, -- admin
    4, -- Mẹo Vặt Công Nghệ
    'Các Lệnh SQL Thường Dùng cho Lập Trình Viên Backend', 
    'Tổng hợp các lệnh SQL cơ bản và nâng cao mà mọi lập trình viên backend nên biết để thao tác với dữ liệu.', 
    'Tóm tắt nhanh các lệnh SELECT, INSERT, UPDATE, DELETE và JOIN.', 
    'cac-lenh-sql-thuong-dung-backend', 
    'published', 
    NOW(), -- Xuất bản ngay bây giờ
    50
);

INSERT INTO post_tag (post_id, tag_id) VALUES
(1, 1), -- Bài 1: JPA
(1, 2), -- Bài 1: Hibernate
(1, 5), -- Bài 1: Backend
(2, 3), -- Bài 2: SQL
(2, 4), -- Bài 2: Performance
(3, 3), -- Bài 3: SQL
(3, 5), -- Bài 3: Backend
(3, 6); -- Bài 3: Tips

INSERT INTO comment (post_id, user_id, author_name, author_email, content, parent_comment_id, status) VALUES
(
    2, -- Bài Tối Ưu SQL
    NULL, -- Bình luận của Khách
    'Nguyễn Văn A', 
    'a@gmail.com', 
    'Bài viết rất hay và dễ hiểu. Tôi đã áp dụng index và tốc độ truy vấn cải thiện rõ rệt!', 
    NULL, 
    'approved'
),
(
    2, -- Bài Tối Ưu SQL
    1, -- Bình luận của Admin
    'Quản Trị Viên', 
    'admin@blog.com', 
    'Cảm ơn phản hồi của bạn! Nếu có câu hỏi sâu hơn, cứ tự nhiên nhé.', 
    1, -- Trả lời bình luận 1
    'approved'
),
(
    1, -- Bài Cấu Hình JPA
    NULL, -- Bình luận của Khách
    'Lê Thị B', 
    'b@dev.vn', 
    'Tôi đang gặp lỗi khi sử dụng @OneToMany trong Entity. Tác giả có thể chia sẻ thêm về cascade types không?', 
    NULL, 
    'pending' -- Bình luận chờ duyệt
);

CREATE TABLE role (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE user_role (
    user_id INT,
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (role_id) REFERENCES role(role_id)
);
