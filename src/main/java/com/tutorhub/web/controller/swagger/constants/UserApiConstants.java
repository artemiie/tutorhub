package com.tutorhub.web.controller.swagger.constants;

public class UserApiConstants {
  public static final String NAME = "UserController";
  public static final String DESCRIPTION = "REST API for UserController";

  public static class Find {

    public static final String SUMMARY = "Get user by ID";
    public static final String DESCRIPTION =
        "Returns the user details for the specified user ID.";

    public static class RescponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION =
          "Successfully retrieved user details";
    }

    public static class RescponseCode404 {
      public static final String CODE = "404";
      public static final String DESCRIPTION =
          "User not found for the specified ID";
    }

    public static class RescponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";
    }
  }

  public static class Update {

    public static final String SUMMARY = "Update user";
    public static final String DESCRIPTION = "Updates an existing user.";

    public static class RescponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION = "User updated successfully";
    }

    public static class RescponseCode400 {
      public static final String CODE = "400";
      public static final String DESCRIPTION =
          "Invalid input or validation error";
    }

    public static class RescponseCode404 {
      public static final String CODE = "404";
      public static final String DESCRIPTION =
          "User not found";
    }

    public static class RescponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";
    }
  }
}
