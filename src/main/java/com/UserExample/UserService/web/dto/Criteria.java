package com.UserExample.UserService.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Criteria {
    private PageInfo pageInfo=new PageInfo(0,100);
    private int minAge = 0;
    private int maxAge = 200;
    private String name = "%";

    public Criteria(PageInfo pageInfo, int minAge, int maxAge) {
        this.pageInfo = pageInfo;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }
}

