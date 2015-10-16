package com.spring.mvc.repository;

import com.spring.mvc.model.AnnouncementModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liluoqi on 15/9/20.
 */
@Repository
public class AnnouncementRepository extends BaseRepository<AnnouncementModel> {
    private static final String GET_ANNOUNCEMENT_BY_ID = "getAnnouncementById";
    private static final String GET_ALL_ANNOUNCEMENTS = "getAllAnnouncements";

    public List<AnnouncementModel> getAllAnnouncements() {
        return queryList(GET_ALL_ANNOUNCEMENTS);
    }
}
