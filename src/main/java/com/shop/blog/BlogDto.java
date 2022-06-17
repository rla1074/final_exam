package com.shop.blog;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemImgDto;
import com.shop.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class BlogDto {

    private Long id;

    @NotBlank(message = "리뷰제목은 필수 입력 값입니다.")
    private String title;

    @NotNull(message = "리뷰내용은 필수 입력 값입니다.")
    private Integer content;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();
    // DTO <==> Entity

    public Blog createItem(){

        return modelMapper.map(this, Blog.class);
    }

    public static BlogDto of(Blog blog){
        return modelMapper.map(blog,BlogDto.class);
    }

    //private String imgUrl1; //블로그 이미지1 조회 경로
    //private String imgUrl2; //블로그 이미지2 조회 경로
}
