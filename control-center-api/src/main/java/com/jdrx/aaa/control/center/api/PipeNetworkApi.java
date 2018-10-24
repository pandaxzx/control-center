package com.jdrx.aaa.control.center.api;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jdrx.aaa.control.center.dto.PointQueryDto;
import com.jdrx.aaa.control.center.service.PipeNetworkService;
import com.jdrx.platform.commons.rest.beans.vo.ResposeVO;
import com.jdrx.platform.commons.rest.factory.ResponseFactory;

@RestController
@RequestMapping(value = "/api/0/pipeNetwork")
@Api(value = "管网")
public class PipeNetworkApi {
	@Autowired
	private PipeNetworkService pipeNetworkService;
	
	// 模拟点：花园水厂、枣山泵站、中天国际泵房、15个监测点
	@ApiOperation(value = "监测点列表及其属性", notes = "监测点列表及其属性")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResposeVO list(@ApiParam(required = false, name = "dto", value = "监测点查询条件dto") @RequestBody @Valid PointQueryDto dto) {
		return ResponseFactory.ok(pipeNetworkService.list(dto.getType()));
	}
	
	@ApiOperation(value = "水厂、泵站监控", notes = "水厂、泵站监控")
    @RequestMapping(value = "/monitor", method = RequestMethod.POST)
	public ResposeVO monitor() {
		return ResponseFactory.ok(pipeNetworkService.monitor());
	}
}