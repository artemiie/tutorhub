# .env File

# MongoDB settings

- **MONGO_HOST** = MongoDB host (example, localhost)<br>
- **MONGO_PORT** = MongoDB port (example, 27017)<br>
- **MONGO_USER** = MongoDB username<br>
- **MONGO_PASSWORD** = MongoDB user password<br>
- **MONGO_DATABASE** = MongoDB database name<br>

# JWT tokens settings

- **JWT_SECRET**: Secret key used for signing format tokens, base64 encoded.

- **JWT_ACCESS_DURATION**: The duration for which the access token is valid. Use
  the `Duration` class format, e.g., "15m" for 15 minutes.

- **JWT_ACTIVATION_DURATION**: The duration for which the activation token is
  valid. Use the `Duration` class format, e.g., "1h" for 1 hour.

- **JWT_REFRESH_DURATION**: The duration for which the refresh token is valid.
  Use the `Duration` class format, e.g., "7d" for 7 days.

- **JWT_RESTORE_DURATION**: The duration for which the restore token is valid.
  Use the `Duration` class format, e.g., "30m" for 30 minutes.

# Email service settings

- **SPRING_MAIL_HOST** = SMTP server host for the Spring Mail service (example,
  smtp.gmail.com)<br>
- **SPRING_MAIL_PORT** = SMTP server port for the Spring Mail service (example,
  587)<br>
- **SPRING_MAIL_USERNAME** = Username for auth with the SMTP server (example,
  johndoe@gmail.com)<br>
- **SPRING_MAIL_PASSWORD** = Password for auth with the SMTP server
  (<a href="https://www.youtube.com/watch?v=xspiCuU_BXk" target="_blank">*
  *tutorial for gmail**</a>)<br>
