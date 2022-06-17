package com.shop.blog;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

public class BlogDto {
    
    @NotBlank(message = "리뷰제목은 필수 입력 값입니다.")
    private String title;
    @NotBlank(message = "리뷰내용은 필수 입력 값입니다.")
    private String content;

    //private String imgUrl1; //블로그 이미지1 조회 경로
    //private String imgUrl2; //블로그 이미지2 조회 경로
}
