package com.service.impl;

import com.dao.ShetuanrenqiDao;
import com.entity.ShetuanrenqiEntity;
import com.entity.view.ShetuanrenqiView;
import com.service.ShetuanrenqiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShetuanrenqiServiceImpl implements ShetuanrenqiService {

    @Autowired
    private ShetuanrenqiDao shetuanrenqiDao;

    @Override
    public List<ShetuanrenqiView> getRenqiRankings() {
        List<ShetuanrenqiEntity> entities = shetuanrenqiDao.getAllRenqiData();
        List<ShetuanrenqiView> views = new ArrayList<>();

        for (ShetuanrenqiEntity entity : entities) {
            ShetuanrenqiView view = new ShetuanrenqiView(entity);
            Integer score = entity.getMemberCount() + entity.getEventParticipation() * 2;
            view.setPopularityScore(score);
            views.add(view);
        }

        return views;
    }
}





