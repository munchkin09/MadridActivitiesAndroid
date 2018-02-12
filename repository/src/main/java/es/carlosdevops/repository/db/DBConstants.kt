package es.carlosdevops.repository.db

internal object DBConstants {
    val TABLE_SHOP = "SHOP"
    val TABLE_ACTIVITIES = "ACTIVITY"

    // Table field constants
    val KEY_ACTIVITY_DATABASE_ID = "_id"
    val KEY_ACTIVITY_ID_JSON = "ID_JSON"
    val KEY_ACTIVITY_NAME = "NAME"
    val KEY_ACTIVITY_IMAGE_URL = "IMAGE_URL"
    val KEY_ACTIVITY_LOGO_IMAGE_URL = "LOGO_IMAGE_URL"

    val KEY_ACTIVITY_ADDRESS = "ADDRESS"
    val KEY_ACTIVITY_URL = "URL"
    val KEY_ACTIVITY_DESCRIPTION = "DESCRIPTION"
    val KEY_ACTIVITY_OPENING_HOURS = "OPENING_HOURS"

    val KEY_ACTIVITY_LATITUDE = "gps_lat"
    val KEY_ACTIVITY_LONGITUDE = "gps_lon"

    val KEY_SHOP_DATABASE_ID = "_id"
    val KEY_SHOP_ID_JSON = "ID_JSON"
    val KEY_SHOP_NAME = "NAME"
    val KEY_SHOP_IMAGE_URL = "IMAGE_URL"
    val KEY_SHOP_LOGO_IMAGE_URL = "LOGO_IMAGE_URL"

    val KEY_SHOP_ADDRESS = "ADDRESS"
    val KEY_SHOP_URL = "URL"
    val KEY_SHOP_DESCRIPTION = "DESCRIPTION"
    val KEY_SHOP_OPENING_HOURS = "OPENING_HOURS"

    val KEY_SHOP_LATITUDE = "gps_lat"
    val KEY_SHOP_LONGITUDE = "gps_lon"

    val ALL_COLUMNS = arrayOf(KEY_SHOP_DATABASE_ID,
            KEY_SHOP_ID_JSON,
            KEY_SHOP_NAME,
            KEY_SHOP_IMAGE_URL,
            KEY_SHOP_LOGO_IMAGE_URL,
            KEY_SHOP_ADDRESS,
            KEY_SHOP_URL,
            KEY_SHOP_DESCRIPTION,
            KEY_SHOP_LATITUDE,
            KEY_SHOP_LONGITUDE,
            KEY_SHOP_OPENING_HOURS)

    val ALL_COLUMNS_ACTIVITY = arrayOf(KEY_ACTIVITY_DATABASE_ID,
            KEY_ACTIVITY_ID_JSON,
            KEY_ACTIVITY_NAME,
            KEY_ACTIVITY_IMAGE_URL,
            KEY_ACTIVITY_LOGO_IMAGE_URL,
            KEY_ACTIVITY_ADDRESS,
            KEY_ACTIVITY_URL,
            KEY_ACTIVITY_DESCRIPTION,
            KEY_ACTIVITY_LATITUDE,
            KEY_ACTIVITY_LONGITUDE,
            KEY_ACTIVITY_OPENING_HOURS)

    val SQL_SCRIPT_CREATE_SHOP_TABLE = (
            "create table " + TABLE_SHOP
                    + "( "
                    + KEY_SHOP_DATABASE_ID + " integer primary key autoincrement, "
                    + KEY_SHOP_ID_JSON + " integer,"
                    + KEY_SHOP_NAME + " text not null,"
                    + KEY_SHOP_IMAGE_URL + " text, "
                    + KEY_SHOP_LOGO_IMAGE_URL + " text, "
                    + KEY_SHOP_ADDRESS + " text,"
                    + KEY_SHOP_URL + " text,"
                    + KEY_SHOP_LATITUDE + " text,"
                    + KEY_SHOP_LONGITUDE + " text, "
                    + KEY_SHOP_DESCRIPTION + " text, "
                    + KEY_SHOP_OPENING_HOURS + " text"
                    + ");")

    val SQL_SCRIPT_CREATE_ACTIVITIES_TABLE = (
            "create table " + TABLE_ACTIVITIES
                    + "( "
                    + KEY_ACTIVITY_DATABASE_ID + " integer primary key autoincrement, "
                    + KEY_ACTIVITY_ID_JSON + " integer,"
                    + KEY_ACTIVITY_NAME + " text not null,"
                    + KEY_ACTIVITY_IMAGE_URL + " text, "
                    + KEY_ACTIVITY_LOGO_IMAGE_URL + " text, "
                    + KEY_ACTIVITY_ADDRESS + " text,"
                    + KEY_ACTIVITY_URL + " text,"
                    + KEY_ACTIVITY_LATITUDE + " text,"
                    + KEY_ACTIVITY_LONGITUDE + " text, "
                    + KEY_ACTIVITY_DESCRIPTION + " text, "
                    + KEY_ACTIVITY_OPENING_HOURS + " text"
                    + ");")

    val DROP_DATABASE_SCRIPTS = ""

    val CREATE_DATABASE_SCRIPTS = arrayOf(SQL_SCRIPT_CREATE_SHOP_TABLE, SQL_SCRIPT_CREATE_ACTIVITIES_TABLE)

}
