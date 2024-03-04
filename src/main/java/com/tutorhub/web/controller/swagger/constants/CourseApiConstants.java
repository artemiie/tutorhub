package com.tutorhub.web.controller.swagger.constants;

public class CourseApiConstants {
  public static final String NAME = "CourseController";
  public static final String DESCRIPTION = "REST API for CourseController";

  public static class Find {

    public static final String SUMMARY = "Find a course by ID";
    public static final String DESCRIPTION =
        "Returns the course details for the specified course ID.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION =
          "Successfully retrieved course details";

    }

    public static class ResponseCode404 {
      public static final String CODE = "404";
      public static final String DESCRIPTION =
          "Course not found for the specified ID";

    }

    public static class ResponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";

    }
  }

  public static class FindAllByUserPaged {

    public static final String SUMMARY = "Find all courses for a user (paged)";
    public static final String DESCRIPTION =
        "Returns a paginated list of courses for the specified user.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION = "Successfully retrieved courses";

    }

    public static class ResponseCode404 {
      public static final String CODE = "404";
      public static final String DESCRIPTION =
          "User not found or no courses available";

    }

    public static class ResponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";

    }
  }

  public static class Create {

    public static final String SUMMARY = "Create a new course";
    public static final String DESCRIPTION =
        "Creates a new course for the current logged-in user.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION = "Course created successfully";

    }

    public static class ResponseCode400 {
      public static final String CODE = "400";
      public static final String DESCRIPTION =
          "Invalid input or validation error";

    }

    public static class ResponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";

    }
  }

  public static class Update {

    public static final String SUMMARY = "Update a course";
    public static final String DESCRIPTION =
        "Updates an existing course.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION = "Course updated successfully";

    }

    public static class ResponseCode400 {
      public static final String CODE = "400";
      public static final String DESCRIPTION =
          "Invalid input or validation error";

    }

    public static class ResponseCode403 {
      public static final String CODE = "403";
      public static final String DESCRIPTION =
          "Access denied";

    }

    public static class ResponseCode404 {
      public static final String CODE = "404";
      public static final String DESCRIPTION =
          "Course not found";

    }

    public static class ResponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";

    }
  }

  public static class Delete {

    public static final String SUMMARY = "Delete a course";
    public static final String DESCRIPTION =
        "Deletes an existing course.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION = "Course deleted successfully";

    }

    public static class ResponseCode400 {
      public static final String CODE = "400";
      public static final String DESCRIPTION =
          "Invalid input or validation error";

    }

    public static class ResponseCode403 {
      public static final String CODE = "403";
      public static final String DESCRIPTION =
          "Access denied";

    }

    public static class ResponseCode404 {
      public static final String CODE = "404";
      public static final String DESCRIPTION =
          "Course not found";

    }

    public static class ResponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";

    }
  }

  public static class EnrollUser {

    public static final String SUMMARY = "Enroll user in a course";
    public static final String DESCRIPTION =
        "Enrolls the current user in the specified course.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION =
          "User enrolled in the course successfully";

    }

    public static class ResponseCode400 {
      public static final String CODE = "400";
      public static final String DESCRIPTION =
          "Invalid input or validation error";

    }

    public static class ResponseCode403 {
      public static final String CODE = "403";
      public static final String DESCRIPTION =
          "Access denied";

    }

    public static class ResponseCode404 {
      public static final String CODE = "404";
      public static final String DESCRIPTION =
          "Course not found";

    }

    public static class ResponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";

    }
  }
}
