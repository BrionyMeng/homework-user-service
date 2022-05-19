package com.UserExample.UserService.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageInfo {
    private int pageNo;
    private int pageSize;
}
