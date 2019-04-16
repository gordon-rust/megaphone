package com.megaphonedemo.demo.controller;

import com.megaphonedemo.demo.domain.MPInfo;
import com.megaphonedemo.demo.service.MPMergeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

@RestController
@RequestMapping("/megaphone")
public class MPMergeController {
    private static Logger log = LoggerFactory.getLogger(MPMergeController.class);
    private MPMergeService mpMergeService;

    @Autowired
    public MPMergeController(MPMergeService mpMergeService){
        this.mpMergeService = mpMergeService;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping(path = "/mp3")
    public void mergeMPFiles(@RequestBody MPInfo mpData, HttpServletResponse response){


        Vector<InputStream> filesToMerge = this.mpMergeService.downloadDataToMerge(mpData.getUrls());
        Enumeration<InputStream> enu = filesToMerge.elements();
        SequenceInputStream sequenceInputStream = new SequenceInputStream(enu);

        response.addHeader("X-Megaphone-Payload",mpData.getHeaders().getHeaderPayloadOne());
        response.addHeader("X-Megaphone-Payload-2", mpData.getHeaders().getHeaderPayloadTwo());
        response.addHeader("Content-disposition", "attachment;filename=MergedPodCast.mp3");

        try {
            IOUtils.copy(sequenceInputStream,response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
