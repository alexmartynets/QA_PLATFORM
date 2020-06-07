package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.EditorDAO;
import com.javamentor.qa.platform.models.entity.Editor;
import org.springframework.stereotype.Repository;

@Repository
public class EditorDAOImpl extends ReadWriteDAOImpl<Editor, Long> implements EditorDAO {
}
