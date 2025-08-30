package com.asifahmedsohan.drugtracker.domain.repository;

import androidx.lifecycle.LiveData;

import com.asifahmedsohan.drugtracker.domain.entity.Drug;
import com.asifahmedsohan.drugtracker.utils.Result;

import java.util.List;

public interface DrugRepository {
    LiveData<Result<List<Drug>>> fetchDrugs(String name);
}
