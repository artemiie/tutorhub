package com.tutorhub.web.controller.swagger.constants;

public class ProgressApiConstants {
  public static final String NAME = "CourseInfoController";
  public static final String DESCRIPTION = "REST API for CourseInfoController";

  public static class Create {

    public static final String SUMMARY = "Create progress";
    public static final String DESCRIPTION =
        "Creates progress for a user in a module.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION =
          "Progress created successfully";

    }

    public static class ResponseCode400 {
      public static final String CODE = "400";
      public static final String DESCRIPTION =
          "Course information not found";

    }

    public static class ResponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";

    }
  }


}
