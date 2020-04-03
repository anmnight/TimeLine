package com.anxiao.timeline;


/**
 * @author: anxiao
 * @version: V1.0
 * @project: MatmMobile
 * @package: com.chinasofti.matmmobile
 * @description: repo provider
 * @date: 2019-12-31
 * @time: 15:31
 */
public class RepoInjection {

    private static RepoInjection repoInjection = new RepoInjection();

    public static RepoInjection INSTANCE() {
        return repoInjection;
    }
}
