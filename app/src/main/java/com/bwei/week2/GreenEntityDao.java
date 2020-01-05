package com.bwei.week2;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bawei.erjileibiao.entity.GreenEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GREEN_ENTITY".
*/
public class GreenEntityDao extends AbstractDao<GreenEntity, Long> {

    public static final String TABLENAME = "GREEN_ENTITY";

    /**
     * Properties of entity GreenEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Json = new Property(1, String.class, "json", false, "JSON");
        public final static Property Tag = new Property(2, String.class, "tag", false, "TAG");
    }


    public GreenEntityDao(DaoConfig config) {
        super(config);
    }
    
    public GreenEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GREEN_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"JSON\" TEXT," + // 1: json
                "\"TAG\" TEXT);"); // 2: tag
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GREEN_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GreenEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String json = entity.getJson();
        if (json != null) {
            stmt.bindString(2, json);
        }
 
        String tag = entity.getTag();
        if (tag != null) {
            stmt.bindString(3, tag);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GreenEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String json = entity.getJson();
        if (json != null) {
            stmt.bindString(2, json);
        }
 
        String tag = entity.getTag();
        if (tag != null) {
            stmt.bindString(3, tag);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public GreenEntity readEntity(Cursor cursor, int offset) {
        GreenEntity entity = new GreenEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // json
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // tag
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GreenEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setJson(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTag(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(GreenEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(GreenEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(GreenEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
