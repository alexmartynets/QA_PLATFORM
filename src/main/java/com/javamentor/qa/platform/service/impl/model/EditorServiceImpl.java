package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.EditorDAO;
import com.javamentor.qa.platform.models.entity.user.Editor;
import com.javamentor.qa.platform.service.abstracts.model.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorServiceImpl extends ReadWriteServiceImpl<Editor, Long> implements EditorService {

    private final EditorDAO editorDAO;

    @Autowired
    public EditorServiceImpl(EditorDAO editorDAO) {
        super(editorDAO);
        this.editorDAO = editorDAO;
    }
}
