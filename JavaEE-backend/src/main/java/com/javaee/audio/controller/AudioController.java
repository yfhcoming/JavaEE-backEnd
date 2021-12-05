package com.javaee.audio.controller;


import com.javaee.audio.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
@RestController
@RequestMapping("/audio")
public class AudioController {

    @Autowired
    AudioService audioService;

    @GetMapping("/all")
    public void getAllAudios()
    {

    }


}
