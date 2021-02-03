package com.example.loginwithsql.data;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;

public class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfUser;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `User`(`uid`,`username`,`password`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        stmt.bindLong(1, value.getUid());
        if (value.getUsername() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUsername());
        }
        if (value.getPassword() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPassword());
        }
      }
    };
  }

  @Override
  public void createUser(User user) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser.insert(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public User getUserByUsername(String strUsername) {
    final String _sql = "SELECT * FROM User Where username like ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (strUsername == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, strUsername);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfUsername = _cursor.getColumnIndexOrThrow("username");
      final int _cursorIndexOfPassword = _cursor.getColumnIndexOrThrow("password");
      final User _result;
      if(_cursor.moveToFirst()) {
        _result = new User();
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        _result.setUid(_tmpUid);
        final String _tmpUsername;
        _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        _result.setUsername(_tmpUsername);
        final String _tmpPassword;
        _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        _result.setPassword(_tmpPassword);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
