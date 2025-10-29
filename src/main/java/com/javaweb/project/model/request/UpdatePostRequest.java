package com.javaweb.project.model.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePostRequest extends PostRequest {
    private Long id;
}