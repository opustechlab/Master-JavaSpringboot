package com.oputechlab.belajarcrud3.model;
import lombok.Data;
public class Whatsapp {

    @Data
    public static class Request {
        private String sendTo;
        private String message;
        private String mediaUrl;
        private String mediaName;
        private String type;
    }

    @Data
    public static class Response {
        private String resultCode;
        private String resultMessage;
    }
}
