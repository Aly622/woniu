package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.dto.PermIdsDTO;
import com.woniu.entity.UcPermGroupRelation;
import com.woniu.response.UCResponseCode;
import com.esmartwave.niumeng.diap.response.WebResponse;
import com.woniu.service.UcPermGroupRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.esmartwave.niumeng.diap.controller.SuperController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单组权限关系表 前端控制器
 * </p>
 *
 * @author Admin
 * @since 2022-03-10
 */
@Slf4j
@Api(tags = "权限组与权限关系相关接口")
@RestController
@RequestMapping("/${spring.application.name}/v1/permGroupRelation")
public class UcPermGroupRelationController extends SuperController {

    @Autowired
    private UcPermGroupRelationService service;

    @ApiOperation(value = "根据分组id获取权限id集合")
    @GetMapping("/getPermIds/{groupId}")
    public WebResponse<PermIdsDTO> getPermIds(@PathVariable("groupId")Long groupId){
        QueryWrapper<UcPermGroupRelation> qry = new QueryWrapper<UcPermGroupRelation>().eq("group_id", groupId);
        List<UcPermGroupRelation> list = service.list(qry);
        List<Long> permIds = list.stream().map(UcPermGroupRelation::getPermId).collect(Collectors.toList());
        PermIdsDTO permIdsDTO = new PermIdsDTO();
        permIdsDTO.setPermIds(permIds);
        return new WebResponse<PermIdsDTO>(UCResponseCode.SUCCESS,permIdsDTO);

    }
}

