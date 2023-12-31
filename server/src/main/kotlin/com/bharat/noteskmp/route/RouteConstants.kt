package com.bharat.noteskmp.route

internal object RouteConstants {
    /** The version segment of the API route. */
    const val API_VERSION = "v1"

    /** Contains constants related to employee routing. */
    object Notes {
        /** The route segment for accessing employee-related operations. */
        const val ROUTE = "note"

        /** The path parameter name for identifying a specific employee. */
        const val NOTE_ID = "note_id"
    }

    /** Contains constants related to employment routing. */
    object User {
        /** The route segment for accessing employment-related operations. */
        const val ROUTE = "user"

        /** The path parameter name for identifying a specific employment record. */
        const val USER_ID = "user_id"

        /**
         * The path template for accessing a specific employment record.
         * Incorporates the employment ID as a path parameter.
         */
        const val USER_ID_PATH = "{${USER_ID}}"
    }

    object Auth {
        const val SIGN_UP_ROUTE = "signup"
        const val LOGIN_ROUTE = "signin"

    }
}