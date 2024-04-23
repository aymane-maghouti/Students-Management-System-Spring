package com.ensah.exceptions;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = Logger.getLogger(ExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error"); 
        modelAndView.addObject("errorMessage", "An error occurred see the log file for more details.");
        logger.error("An error caused by : ",ex);
        return modelAndView;
    }
}