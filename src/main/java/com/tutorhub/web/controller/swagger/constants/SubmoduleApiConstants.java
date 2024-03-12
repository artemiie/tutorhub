package com.tutorhub.web.controller.swagger.constants;

public class SubmoduleApiConstants {
  public static final String NAME = "ModuleController";
  public static final String DESCRIPTION = "REST API for ModuleController";

  public static class Find {

    public static final String SUMMARY = "Find submodule by ID";
    public static final String DESCRIPTION =
        "Returns the submodule details for the specified course, module, "
            + "and submodule ID.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION = "Successfully retrieved "
          + "submodule details";

    }

    public static class ResponseCode404 {
      public static final String CODE = "404";
      public static final String DESCRIPTION =
          "Submodule not found for the specified IDs";

    }

    public static class ResponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";

    }
  }

  public static class FindAllPaged {

    public static final String SUMMARY =
        "Find all submodules for a module (paged)";
    public static final String DESCRIPTION =
        "Returns a paginated list of submodules for the specified course and "
            + "module.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION = "Successfully retrieved modules";

    }

    public static class ResponseCode404 {
      public static final String CODE = "404";
      public static final String DESCRIPTION =
          "Course or module not found or no submodules available";

    }

    public static class ResponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";

    }
  }

  public static class Create {

    public static final String SUMMARY = "Create a new submodule for a module";
    public static final String DESCRIPTION =
        "Creates a new submodule for the specified course and module.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION = "Submodule created successfully";

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

    public static final String SUMMARY = "Update a submodule for a module";
    public static final String DESCRIPTION =
        "Updates an existing submodule for the specified course and module.";

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
          "Course or module or submodule not found";

    }

    public static class ResponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";

    }
  }

  public static class Delete {

    public static final String SUMMARY = "Delete a submodule for a module";
    public static final String DESCRIPTION =
        "Deletes an existing submodule for the specified module.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION = "Submodule deleted successfully";

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
          "Course or module or submodule not found";

    }

    public static class ResponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";

    }
  }
}
