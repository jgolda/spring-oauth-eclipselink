package com.github.serserser.springwebapp.db;

import javax.persistence.*;

@Entity
@Table(name = "t_history_items")
@SequenceGenerator(name = "hit_id_seq", sequenceName = "s_hit_id", allocationSize = 1)
public class HistoryEntryItem {

    @Id
    @Column(name = "hit_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hit_id_seq")
    private Long id;

    @Column(name = "hit_owner_id")
    private Long ownerEntityId;

    @Column(name = "hit_class_name")
    private String simpleClassName;

    @Column(name = "hit_field_name")
    private String fieldName;

    @Column(name = "hit_old_value")
    private String oldValue;

    @Column(name = "hit_new_value")
    private String newValue;

    public HistoryEntryItem() {
    }

    public HistoryEntryItem(Long ownerEntityId, String simpleClassName, String fieldName, String oldValue, String newValue) {
        this.ownerEntityId = ownerEntityId;
        this.simpleClassName = simpleClassName;
        this.fieldName = fieldName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerEntityId() {
        return ownerEntityId;
    }

    public void setOwnerEntityId(Long ownerEntityId) {
        this.ownerEntityId = ownerEntityId;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }

    public void setSimpleClassName(String simpleClassName) {
        this.simpleClassName = simpleClassName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Override
    public String toString() {
        return "HistoryEntryItem{" +
                "ownerEntityId=" + ownerEntityId +
                ", simpleClassName='" + simpleClassName + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", oldValue='" + oldValue + '\'' +
                ", newValue='" + newValue + '\'' +
                '}';
    }
}
