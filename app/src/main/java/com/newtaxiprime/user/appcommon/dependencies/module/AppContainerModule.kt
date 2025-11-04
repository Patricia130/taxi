package com.newtaxiprime.user.appcommon.dependencies.module

/**
 * @package com.newtaxiprime.user
 * @subpackage dependencies.module
 * @category AppContainerModule
 * @author Seen Technologies
 *
 */

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.newtaxiprime.user.R
import com.newtaxiprime.user.appcommon.configs.RunTimePermission
import com.newtaxiprime.user.appcommon.configs.SessionManager
import com.newtaxiprime.user.appcommon.database.SqLiteDb
import com.newtaxiprime.user.appcommon.datamodels.JsonResponse
import com.newtaxiprime.user.appcommon.network.AppController
import com.newtaxiprime.user.appcommon.utils.CommonMethods
import com.newtaxiprime.user.appcommon.utils.userchoice.UserChoice
import com.newtaxiprime.user.taxiapp.views.customize.CustomDialog
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

/*****************************************************************
 * App Container Module
 */
@Module(includes = [com.newtaxiprime.user.appcommon.dependencies.module.ApplicationModule::class])
class AppContainerModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(application.resources.getString(R.string.app_name), Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providesCommonMethods(): CommonMethods {
        return CommonMethods()
    }

    @Provides
    @Singleton
    fun providesSessionManager(): SessionManager {
        return SessionManager()
    }


    @Provides
    @Singleton
    fun providesRunTimePermission(): RunTimePermission {
        return RunTimePermission()
    }

    @Provides
    @Singleton
    fun providesContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providesStringArrayList(): ArrayList<String> {
        return ArrayList()
    }

    @Provides
    @Singleton
    fun providesJsonResponse(): JsonResponse {
        return JsonResponse()
    }


    @Provides
    @Singleton
    fun providesCustomDialog(): CustomDialog {
        return CustomDialog()
    }

    @Provides
    @Singleton
    internal fun providesSqlite(): SqLiteDb {
        return SqLiteDb(AppController.getContext())
    }

    @Provides
    @Singleton
    fun providesUserChoice(): UserChoice {
        return UserChoice()
    }


}
