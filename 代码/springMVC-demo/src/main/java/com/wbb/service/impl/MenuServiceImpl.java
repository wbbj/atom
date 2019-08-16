package com.wbb.service.impl;

import com.wbb.mapper.MenuMapper;
import com.wbb.pojo.Menu;
import com.wbb.service.MenuService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> show() {
        return menuMapper.selByPid(0);
    }



}
