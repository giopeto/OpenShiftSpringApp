package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MyWebMvcConfig extends WebMvcConfigurationSupport {

	private static final String RESOURCES_LOCATION = "/resources/";
	private static final String RESOURCES_HANDLER = RESOURCES_LOCATION + "**";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(RESOURCES_LOCATION);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * Handles favicon.ico requests assuring no <code>404 Not Found</code> error is returned.
	 */
	@Controller
	static class FaviconController {
		@RequestMapping("favicon.ico")
		String favicon() {
			return "forward:/resources/images/favicon.ico";
		}
	}

}