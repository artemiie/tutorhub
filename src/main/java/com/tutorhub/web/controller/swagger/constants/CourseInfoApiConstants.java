package com.tutorhub.web.controller.swagger.constants;

public class CourseInfoApiConstants {
  public static final String NAME = "CourseInfoController";
  public static final String DESCRIPTION = "REST API for CourseInfoController";

  public static class Find {

    public static final String SUMMARY = "Find course information";
    public static final String DESCRIPTION =
        "Returns course information and progress for the specified user "
            + "and course.";

    public static class ResponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION =
          "Successfully retrieved course information";

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
