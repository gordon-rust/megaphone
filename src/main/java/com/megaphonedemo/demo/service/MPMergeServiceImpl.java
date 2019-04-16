package com.megaphonedemo.demo.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.Vector;
import java.util.concurrent.*;

@Service
public class MPMergeServiceImpl implements MPMergeService {

//    @Override
//    public ArrayList<String> downloadDataToMerge(Collection<String> urls) {
//        ArrayList<String> fileLocations = new ArrayList<>();
//
//        urls.stream().forEach(url -> {
//            File downloadedFile = new File("/tmp" + url.substring(url.lastIndexOf("/")));
//            fileLocations.add(downloadedFile.getPath());
//
//            try {
//                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//                    try {
//                        URLConnection connection = new URL(url).openConnection();
//                        InputStream is = connection.getInputStream();
//
//                        OutputStream os = new FileOutputStream(downloadedFile);
//                        byte[] buffer = new byte[4096];
//                        int len;
//                        while ((len = is.read(buffer)) > 0){
//                            os.write(buffer, 0, len);
//                        }
//                        os.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//                future.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        });
//
//        return fileLocations;
//    }

    @Override
    public Vector<InputStream> downloadDataToMerge(Collection<String> urls) {
        Vector<InputStream> fileStreams = new Vector<>();

        urls.stream().forEach(url -> {
            try {
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    try {
                        URLConnection connection = new URL(url).openConnection();
                        fileStreams.add(connection.getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        return fileStreams;
    }

}
