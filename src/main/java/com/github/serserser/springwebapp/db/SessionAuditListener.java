package com.github.serserser.springwebapp.db;

import org.apache.commons.collections.CollectionUtils;
import org.eclipse.persistence.internal.sessions.RepeatableWriteUnitOfWork;
import org.eclipse.persistence.queries.DatabaseQuery;
import org.eclipse.persistence.queries.InsertObjectQuery;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.SessionEvent;
import org.eclipse.persistence.sessions.SessionEventAdapter;
import org.eclipse.persistence.sessions.changesets.ChangeRecord;
import org.eclipse.persistence.sessions.changesets.DirectToFieldChangeRecord;
import org.eclipse.persistence.sessions.changesets.ObjectChangeSet;
import org.eclipse.persistence.sessions.changesets.UnitOfWorkChangeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class SessionAuditListener extends SessionEventAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SessionAuditListener.class);

    private static final String UOW_PROPERTY_DEFERRED_QUERY_SET = "DEFERRED_QUERY_SET";

    @Override
    public void preCommitTransaction(SessionEvent event) {
        Session session = event.getSession();
        List<DatabaseQuery> deferredQueries = (List<DatabaseQuery>) session.getProperty(UOW_PROPERTY_DEFERRED_QUERY_SET);
        if (CollectionUtils.isNotEmpty(deferredQueries)) {
            deferredQueries.forEach(query -> session.executeQuery(query));
        }
    }

    @Override
    public void postCalculateUnitOfWorkChangeSet(SessionEvent event) {
        logger.info("before commiting transaction");
        Collection<ObjectChangeSet> unitOfWorkChangeSet = Optional.ofNullable(event.getProperties().get("UnitOfWorkChangeSet"))
                .filter(changeSet -> changeSet instanceof UnitOfWorkChangeSet)
                .map(object -> (UnitOfWorkChangeSet) object)
                .map(UnitOfWorkChangeSet::getAllChangeSets)
                .map(map -> map.values())
                .map(item -> (Collection<ObjectChangeSet>) item)
                .orElse(new ArrayList<>());

        List<HistoryEntryItem> entryItems = unitOfWorkChangeSet.stream()
                .map(cs -> cs.getChanges())
                .flatMap(item -> item.stream())
                .map(change -> toHistoryItem(event.getSession(), change))
                .collect(Collectors.toList());

        RepeatableWriteUnitOfWork unitOfWork = (RepeatableWriteUnitOfWork) event.getSession();
        List<DatabaseQuery> deferredQueries = Optional.ofNullable((List<DatabaseQuery>) unitOfWork.getParent().getProperty(UOW_PROPERTY_DEFERRED_QUERY_SET))
                .orElse(new ArrayList<>());
        entryItems.forEach(entryItem -> deferredQueries.add(new InsertObjectQuery(entryItem)));
        unitOfWork.getParent().setProperty(UOW_PROPERTY_DEFERRED_QUERY_SET, deferredQueries);
        entryItems.forEach(System.out::println);
    }

    private static HistoryEntryItem toHistoryItem(Session session, ChangeRecord change) {
        String newValue = getNewValue(change);
        // TODO: [jgolda] mądrzejsze konwertowanie obiektów na string według typów??
        return new HistoryEntryItem(Long.valueOf(String.valueOf(change.getOwner().getId())), change.getOwner().getClassType(session).getSimpleName(), change.getAttribute(), String.valueOf(change.getOldValue()), newValue);
    }

    private static String getNewValue(ChangeRecord change) {
        if (change instanceof DirectToFieldChangeRecord ) {
            // TODO: [jgolda] mądrzejsze konwertowanie obiektów na string według typów??
            return String.valueOf(((DirectToFieldChangeRecord) change).getNewValue());
        } else {
            return "";
        }
    }
}
