package com.tutorhub.web.controller.swagger.constants;

public class ModuleApiConstants {
  public static final String NAME = "ModuleController";
  public static final String DESCRIPTION = "REST API for ModuleController";

  public static class Find {

    public static final String SUMMARY = "Find module by ID";
    public static final String DESCRIPTION =
        "Returns the module details for the specified course and module ID.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION =
          "Successfully retrieved module details";

    }

    public static class ResponseCode404 {
      public static final String CODE = "404";
      public static final String DESCRIPTION =
          "Module not found for the specified IDs";

    }

    public static class ResponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";

    }
  }

  public static class FindAllPaged {

    public static final String SUMMARY =
        "Find all modules for a course (paged)";
    public static final String DESCRIPTION =
        "Returns a paginated list of modules for the specified course.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION = "Successfully retrieved modules";

    }

    public static class ResponseCode404 {
      public static final String CODE = "404";
      public static final String DESCRIPTION =
          "Course not found or no modules available";

    }

    public static class ResponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";

    }
  }

  public static class Create {

    public static final String SUMMARY = "Create a new module for a course";
    public static final String DESCRIPTION =
        "Creates a new module for the specified course.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION = "Module created successfully";

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

    public static final String SUMMARY = "Update a module for a course";
    public static final String DESCRIPTION =
        "Updates an existing module for the specified course.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION = "Module updated successfully";

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
          "Course or module not found";

    }

    public static class ResponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";

    }
  }

  public static class Delete {

    public static final String SUMMARY = "Delete a module for a course";
    public static final String DESCRIPTION =
        "Deletes an existing module for the specified course.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION = "Module deleted successfully";

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
          "Course or module not found";

    }

    public static class ResponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";

    }
  }
}
