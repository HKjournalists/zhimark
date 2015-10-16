package com.spring.mvc.resource;

import com.spring.mvc.utils.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by liluoqi on 15/5/17.
 */
@Component
@Path("execute")
public class ExecuteShellForWindowsResource {
    private static final String SHELL_NAME = "/var/www/html/changeO2OFileMode.sh";
    private Logger logger = Logger.getLogger(ExecuteShellForWindowsResource.class);

    @GET
    @Path("changeO2OMode")
    public String changeMode() {
        try {
            Process process = Runtime.getRuntime().exec(SHELL_NAME);
            int exitValue = process.waitFor();
            if (0 != exitValue) {
                logger.error("call shell failed. error code is :" + exitValue);
                return "修改文件权限失败s";
            }
        } catch (Throwable e) {
            logger.error("call shell failed. " + e);
        }
        return "修改文件权限成功";
    }
}
