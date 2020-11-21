package com.compani.ilai.bioproducts.utils

import com.compani.ilai.bioproducts.auth.support.Validator

object Constants {

    const val PRODUCER_LIST = "producer"
    const val VEGETABLES_PRODUCT = "vegetable"
    const val MEAT_PRODUCT = "meat"
    const val FRUIT_PRODUCT = "fruit"
    const val CHEESE_PRODUCT = "cheese"
    const val DIVERS_PRODUCT = "divers"

    const val CLIENT_TYPE = "Client"
    const val EMAIl_REGEX = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"

    const val MIN_PASSWORD_LENGTH = 8
}