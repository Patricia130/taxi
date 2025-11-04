package com.newtaxiprime.user.appcommon.utils.userchoice

interface UserChoiceSuccessResponse {
    fun onSuccessUserSelected(type: String?, userChoiceData: String?, userChoiceCode: String?)
}