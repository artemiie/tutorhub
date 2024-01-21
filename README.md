# .env File

# MongoDB settings

**MONGO_HOST** = MongoDB host (example, localhost)<br>
**MONGO_PORT** = MongoDB port (example, 27017)<br>
**MONGO_USER** = MongoDB username<br>
**MONGO_PASSWORD** = MongoDB user password<br>
**MONGO_DATABASE** = MongoDB database name<br>

# JWT tokens settings

**JWT_SECRET** = Secret key for signing format tokens base64<br>
**JWT_ACCESS_DURATION** = Access token duration (in seconds)<br>
**JWT_ACTIVATION_DURATION** = Activation token duration (in seconds)<br>
**JWT_REFRESH_DURATION** = Refresh token duration (in seconds)<br>
**JWT_RESTORE_DURATION** = Restore token duration (in seconds)<br>

# Email service settings

**SPRING_MAIL_HOST** = SMTP server host for the Spring Mail service (example, smtp.gmail.com)<br>
**SPRING_MAIL_PORT** = SMTP server port for the Spring Mail service (example, 587)<br>
**SPRING_MAIL_USERNAME** = Username for auth with the SMTP server (example, johndoe@gmail.com)<br>
**SPRING_MAIL_PASSWORD** = Password for auth with the SMTP server
(<a href="https://www.youtube.com/watch?v=xspiCuU_BXk" target="_blank">**tutorial for gmail**</a>)<br>