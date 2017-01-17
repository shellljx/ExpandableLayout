package com.licrafter.sample.repo;

import java.util.List;

/**
 * author: shell
 * date 2017/1/17 上午9:43
 **/
public class Repo {

    public static List<ImageModel> data;

    public static void initRepo() {
        data.add(new ImageModel("http://ci.xiaohongshu.com/2cc966e2-eed0-455c-be10-e1377a99b73c@r_1280w_1280h.jpg", 1280, 1280));
        data.add(new ImageModel("http://ci.xiaohongshu.com/c09d083b-2464-4cbe-9219-5210edaff64e@r_1280w_1280h.jpg", 960, 1280));
        data.add(new ImageModel("http://ci.xiaohongshu.com/206222a6-2de4-40f4-a002-654968478e59@r_1280w_1280h.jpg", 1024, 1280));
    }
}
