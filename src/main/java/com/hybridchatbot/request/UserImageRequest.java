package com.hybridchatbot.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserImageRequest {
    Integer userId;
    String image;
    String imageId;
}
