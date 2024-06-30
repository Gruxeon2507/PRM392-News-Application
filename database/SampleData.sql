USE PRM392_News_Application;
GO

-- Insert data into UserRoles
INSERT INTO UserRoles (role_name) VALUES
('Admin'),
('Normal User');
GO

-- Insert data into Users
INSERT INTO Users (username, password_hash, email, role_id) VALUES
('admin', 'hashed_password_1', 'admin@example.com', 1),
('user1', 'hashed_password_2', 'user1@example.com', 2),
('user2', 'hashed_password_3', 'user2@example.com', 2),
('user3', 'hashed_password_4', 'user3@example.com', 2),
('user4', 'hashed_password_5', 'user4@example.com', 2),
('user5', 'hashed_password_6', 'user5@example.com', 2),
('user6', 'hashed_password_7', 'user6@example.com', 2),
('user7', 'hashed_password_8', 'user7@example.com', 2),
('user8', 'hashed_password_9', 'user8@example.com', 2),
('user9', 'hashed_password_10', 'user9@example.com', 2),
('user10', 'hashed_password_11', 'user10@example.com', 2);
GO

-- Insert data into News
INSERT INTO News (title, content, author_id, published_at) VALUES
('Breaking News: Market Crash', 'The stock market crashed today...', 2, CURRENT_TIMESTAMP),
('Tech Trends 2024', 'Here are the top tech trends...', 3, CURRENT_TIMESTAMP),
('Sports Update: Championship', 'The championship game concluded...', 4, CURRENT_TIMESTAMP),
('Political Debate Highlights', 'Key moments from the debate...', 5, CURRENT_TIMESTAMP),
('Health News: Vaccination', 'Updates on the latest vaccine...', 6, CURRENT_TIMESTAMP),
('Weather Alert', 'Severe weather warning issued...', 7, CURRENT_TIMESTAMP),
('Cultural Festivities', 'Highlights from the cultural fest...', 8, CURRENT_TIMESTAMP),
('Travel Tips 2024', 'Best travel destinations...', 9, CURRENT_TIMESTAMP),
('Economic Outlook', 'Forecast for the global economy...', 10, CURRENT_TIMESTAMP),
('Entertainment Buzz', 'Latest in entertainment...', 11, CURRENT_TIMESTAMP);
GO

-- Insert data into Comments
INSERT INTO Comments (user_id, news_id, content, created_at) VALUES
(2, 1, 'This is shocking news!', CURRENT_TIMESTAMP),
(3, 1, 'Thanks for the update.', CURRENT_TIMESTAMP),
(4, 2, 'Interesting article on tech trends.', CURRENT_TIMESTAMP),
(5, 3, 'Great game summary!', CURRENT_TIMESTAMP),
(6, 4, 'Important points made.', CURRENT_TIMESTAMP),
(7, 5, 'Good to know about the vaccine.', CURRENT_TIMESTAMP),
(8, 6, 'Stay safe everyone!', CURRENT_TIMESTAMP),
(9, 7, 'Looking forward to this event.', CURRENT_TIMESTAMP),
(10, 8, 'Adding these places to my list.', CURRENT_TIMESTAMP),
(11, 9, 'Insightful economic analysis.', CURRENT_TIMESTAMP),
(2, 10, 'Exciting entertainment news.', CURRENT_TIMESTAMP);
GO

-- Insert data into Statistics
INSERT INTO [Statistics] (user_id, news_id, view_count, like_count, comment_count) VALUES
(2, 1, 50, 10, 2),
(3, 2, 30, 8, 1),
(4, 3, 40, 12, 1),
(5, 4, 60, 20, 1),
(6, 5, 70, 25, 1),
(7, 6, 80, 30, 1),
(8, 7, 90, 35, 1),
(9, 8, 100, 40, 1),
(10, 9, 110, 45, 1),
(11, 10, 120, 50, 1),
(2, 1, 130, 55, 1);
GO

-- Insert data into NewsFilters
INSERT INTO NewsFilters (filter_name, filter_value) VALUES
('Category', 'Finance'),
('Category', 'Technology'),
('Category', 'Sports'),
('Category', 'Politics'),
('Category', 'Health'),
('Category', 'Weather'),
('Category', 'Culture'),
('Category', 'Travel'),
('Category', 'Economy'),
('Category', 'Entertainment');
GO

-- Insert data into UserProfiles
INSERT INTO UserProfiles (user_id, first_name, last_name, bio, avatar_url) VALUES
(1, 'Admin', 'User', 'System administrator', 'https://example.com/avatar1.png'),
(2, 'John', 'Doe', 'Enthusiast reader.', 'https://example.com/avatar2.png'),
(3, 'Jane', 'Smith', 'Tech lover.', 'https://example.com/avatar3.png'),
(4, 'Jim', 'Beam', 'Sports fanatic.', 'https://example.com/avatar4.png'),
(5, 'Jill', 'Valentine', 'Political analyst.', 'https://example.com/avatar5.png'),
(6, 'Jack', 'Daniels', 'Health advisor.', 'https://example.com/avatar6.png'),
(7, 'Julia', 'Roberts', 'Weather reporter.', 'https://example.com/avatar7.png'),
(8, 'Jake', 'Peralta', 'Cultural enthusiast.', 'https://example.com/avatar8.png'),
(9, 'Amy', 'Santiago', 'Traveler.', 'https://example.com/avatar9.png'),
(10, 'Terry', 'Jeffords', 'Economy expert.', 'https://example.com/avatar10.png'),
(11, 'Rosa', 'Diaz', 'Entertainment critic.', 'https://example.com/avatar11.png');
GO
