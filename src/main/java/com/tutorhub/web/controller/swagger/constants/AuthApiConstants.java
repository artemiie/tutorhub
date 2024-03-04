package com.tutorhub.web.controller.swagger.constants;

public class AuthApiConstants {
  public static final String NAME = "AuthController";
  public static final String DESCRIPTION = "REST API for AuthController";

  public static class Register {

    public static final String SUMMARY = "Create a new user";
    public static final String DESCRIPTION =
        "Create a new user with the provided user data.";

    public static class RescponseCode201 {
      public static final String CODE = "201";
      public static final String DESCRIPTION = "User registered successfully";

    }

    public static class RescponseCode400 {
      public static final String CODE = "400";
      public static final String DESCRIPTION =
          "Bad Request. Invalid user data.";

    }
  }

  public static class Login {

    public static final String SUMMARY = "Authenticate and login a user";
    public static final String DESCRIPTION =
        "Authenticates a user using the provided credentials and returns an "
            + "authentication response.";

    public static class RescponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION =
          "Successful login. Returns authentication response.";

    }

    public static class RescponseCode401 {
      public static final String CODE = "401";
      public static final String DESCRIPTION =
          "Unauthorized. Invalid credentials.";

    }
  }

  public static class Restore {
    public static final String SUMMARY = "Restore user account password";
    public static final String DESCRIPTION =
        "Initiates the process to restore a user account password using the "
            + "provided request.";

    public static class RescponseCode204 {
      public static final String CODE = "204";
      public static final String DESCRIPTION =
          "Successful request. No content returned.";
    }

    public static class RescponseCode404 {
      public static final String CODE = "404";
      public static final String DESCRIPTION =
          "Not Found. User account not found or unable to restore.";
    }
  }

  public static class Reset {
    public static final String SUMMARY = "Reset user account password";
    public static final String DESCRIPTION =
        "Initiates the process to reset a user account password using the "
            + "provided request.";

    public static class RescponseCode204 {
      public static final String CODE = "204";
      public static final String DESCRIPTION =
          "Successful request. No content returned.";
    }

    public static class RescponseCode403 {
      public static final String CODE = "403";
      public static final String DESCRIPTION = "Forbidden";
    }
  }

  public static class Confirm {
    public static final String SUMMARY = "Confirm user email";
    public static final String DESCRIPTION =
        "Confirms the user's email by verifying the provided token.";

    public static class RescponseCode204 {
      public static final String CODE = "204";
      public static final String DESCRIPTION =
          "Email confirmed successfully. No content returned.";
    }

    public static class RescponseCode400 {
      public static final String CODE = "400";
      public static final String DESCRIPTION =
          "Bad Request. Invalid or expired token.";
    }
  }
}
