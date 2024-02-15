create table user
(
id
fullname
username
password
enabled
)

create table users_roles
(
user_id
role
contraint fk user_id on user.id
)

create table courses
(
id
name
)

create table users_courses
(
user_id
course_id
)

create table courses_info
(
id
course_id
user_id
progress_id
)

create table progress
(
id
module_id
constraint fk module_id on module.id
)

create table modules
(
id
name
text_content_id
video_content_id
quiz_content_id
)

create table sub_modules
(
module_id
sub_module_id
)

create table modules_content
(
module_id
content_id
)



create table text_content
(
id
text
)






create table quiz_content
(
id
question_id
)

create table questions
(
id
text
)

create table answers
(
id
text
)

create table questions_answers
(
question_id
answer_id
)




create table video_content
(
id
url
)