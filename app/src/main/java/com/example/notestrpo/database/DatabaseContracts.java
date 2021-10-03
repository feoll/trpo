package com.example.notestrpo.database;

public final class DatabaseContracts {

    public static final String DATABASE_NAME = "mydb.db";
    public static final int DATABASE_VERSION = 1;

    public static class NoteContract {
        public static final String TABLE_NAME= "notes";

        public static final String _ID = "ID";
        public static final String _TITLE = "TITLE";
        public static final String _SUBTITLE = "SUBTITLE";
        public static final String _DESCRIPTION = "DESCRIPTION";
        public static final String _TIME = "TIME";
        public static final String _COLOR = "COLOR";
        public static final String _URI = "URI";

    }
}
