package com.javamentor.qa.platform.service.search.abstracts;

import java.util.List;
import java.util.Map;

public interface SearchPattern {
    Boolean findPattern(String searchRequest);

    List<?> result(String searchRequest, Map<String, Search> map);
}