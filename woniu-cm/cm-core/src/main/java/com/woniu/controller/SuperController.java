package com.woniu.controller;

/**
 * @ClassName SuperController
 * @Desc TODO
 * @Author Oliver.Liu
 * @Date 2019/6/8 22:52
 * @Version 1.0
 **/
public abstract class SuperController {
/*  通过@ControllerAdvice解耦
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURI());
        mav.setViewName("error");
        return mav;
    }*/
}
