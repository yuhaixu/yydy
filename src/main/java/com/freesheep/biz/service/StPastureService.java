package com.freesheep.biz.service;

import com.freesheep.biz.model.StPastureBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StPastureService {

    Page<StPastureBO> getPastureList(Pageable pageable);

}