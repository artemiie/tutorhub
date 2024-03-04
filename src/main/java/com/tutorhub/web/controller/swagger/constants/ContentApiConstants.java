package com.tutorhub.web.controller.swagger.constants;

public class ContentApiConstants {
  public static final String NAME = "ContentController";
  public static final String DESCRIPTION = "REST API for ContentController";

  public static class Find {
    public static final String SUMMARY = "Find content by file name";
    public static final String DESCRIPTION =
        "Returns the content for the specified file name.";

    public static class RescponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION = "Successfully retrieved content";
    }

    public static class RescponseCode400 {
      public static final String CODE = "400";
      public static final String DESCRIPTION =
          "Content not found for the specified file name";
    }

    public static class RescponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";
    }
  }

  public static class Upload {
    public static final String SUMMARY = "Upload a file";
    public static final String DESCRIPTION =
        "Uploads a file to the server.";

    public static class RescponseCode200 {
      public static final String CODE = "200";
      public static final String DESCRIPTION =
          "File uploaded successfully";
    }

    public static class RescponseCode400 {
      public static final String CODE = "400";
      public static final String DESCRIPTION =
          "Bad request. Please check your request.";
    }

    public static class RescponseCode500 {
      public static final String CODE = "500";
      public static final String DESCRIPTION =
          "Internal server error. Please try again later.";
    }
  }
}
