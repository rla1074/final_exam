package com.shop.blog;


import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.repository.ItemRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;


public interface BlogRepository extends JpaRepository<Blog, Long>,
        QuerydslPredicateExecutor<Blog>{

    List<Blog> findAll();
    Blog findByMemberId(Long memberId);


}
