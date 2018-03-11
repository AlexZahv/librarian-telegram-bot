package com.alexzahv.business.persistance.repositories;

import com.alexzahv.business.persistance.domain.SearchLog;
import org.springframework.data.repository.CrudRepository;

public interface SearchLogRepository extends CrudRepository<SearchLog, Long> {
}
