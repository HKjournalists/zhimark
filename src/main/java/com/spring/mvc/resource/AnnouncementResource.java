package com.spring.mvc.resource;

import com.google.gson.Gson;
import com.spring.mvc.model.AnnouncementModel;
import com.spring.mvc.model.gson.AnnouncementGson;
import com.spring.mvc.repository.AnnouncementRepository;
import com.spring.mvc.utils.Logger;
import com.spring.mvc.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liluoqi on 15/9/20.
 */
@Component
@Resource
@Path("announcement")
public class AnnouncementResource {

    private Logger logger = Logger.getLogger(AnnouncementResource.class);
    @Autowired
    private AnnouncementRepository announcementRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String getAllAnnouncements() {
        try {
            List<AnnouncementModel> announcements = announcementRepository.getAllAnnouncements();
            if (announcements != null && announcements.size() > 0) {
                logger.info("查询到公告");
                List<AnnouncementGson> announcementGsons = new ArrayList<AnnouncementGson>();
                for (AnnouncementModel announcement : announcements) {
                    announcementGsons.add(new AnnouncementGson(announcement));
                }
                return new Gson().toJson(announcementGsons);
            }
        } catch (Exception e) {
            logger.error(String.format("查询公告异常:%s", e.getMessage()), e);
        }
        return new Gson().toJson(StringUtils.emptyString());
    }
}