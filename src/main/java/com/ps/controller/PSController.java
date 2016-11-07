package com.ps.controller;


import com.ps.domain.PS;
import com.ps.service.PSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ps")
public class PSController {

    @Autowired
    private PSService psService;

    @RequestMapping(
            method = RequestMethod.POST,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public PS createEmptyPS(@RequestBody PS ps) {
        return psService.createEmptyPS(ps.getUserId());
    }


	/*@RequestMapping(
			value="{userId}",
			method = RequestMethod.POST,
			headers="Accept=application/json",
			produces="application/json"
	)
	public PS createEmptyPS(@PathVariable Long userId) {

		logger.info("PSController create Empty PS obj: " + userId);

		return psService.createEmptyPS(userId);
	}*/


    @RequestMapping(
            method = RequestMethod.GET,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public List get() {
        return psService.get();
    }

    @RequestMapping(
            value = "{id}",
            method = RequestMethod.GET,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public PS getById(@PathVariable String id) {
        return psService.getById(id);
    }

    @RequestMapping(
            value = "{id}",
            method = RequestMethod.PUT,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public PS update(@PathVariable String id, @RequestBody PS ps) {
        psService.save(ps);
        return ps;
    }


}
