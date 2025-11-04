package com.newtaxiprime.user.appcommon.views

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.webkit.URLUtil
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.newtaxiprime.user.R
import com.newtaxiprime.user.appcommon.datamodels.Banner
import com.newtaxiprime.user.appcommon.network.AppController
import com.newtaxiprime.user.appcommon.utils.CommonMethods
import com.newtaxiprime.user.taxiapp.views.splash.SplashActivity.Companion.checkVersionModel
import kotlinx.android.synthetic.main.app_activity_add_wallet.*
import java.util.*
import javax.inject.Inject


class BannerActivityCommon : CommonActivity(), BannerAdapter.OnClickListener {
    @BindView(R.id.rv_banner_list)
    lateinit var rvBannerList: RecyclerView

    var bannertList: ArrayList<Banner> = ArrayList()

    @OnClick(R.id.arrow)
    fun onBack() {
        onBackPressed()
    }

    @Inject
    lateinit var commonMethods: CommonMethods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_banner_common)
        AppController.appComponent.inject(this)
        ButterKnife.bind(this)
        /**Commmon Header Text View */
        commonMethods.setheaderText(resources.getString(R.string.banner), common_header)
        initViews()

    }

    private fun initViews() {
        bannertList.clear()
        bannertList.addAll(checkVersionModel.banner)
        rvBannerList.adapter = BannerAdapter(this, bannertList, this)
    }
    override fun onClick(pos: Int) {
        if (checkVersionModel.banner[pos].id == 1) {
            onClickWhatsApp(checkVersionModel.banner[pos].link)
        } else if (checkVersionModel.banner[pos].id == 2) {
            openSkype(this, checkVersionModel.banner[pos].link)
        } else {
            redirectWeb(checkVersionModel.banner[pos].link)
        }

    }

    private fun redirectWeb(link: String) {
        if (URLUtil.isValidUrl(link) || Patterns.WEB_URL.matcher(link).matches()) {
            val redirectLink: String = if (!(link.contains("https://") || link.contains("http://"))) {
                "http://$link"
            } else {
                link
            }
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(redirectLink)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        } else {
            Toast.makeText(this, resources.getString(R.string.not_valid_data), Toast.LENGTH_SHORT).show()
        }
    }


    private fun openSkype(context: Context, skypeId: String) {

        // Make sure the Skype for Android client is installed
        if (!isSkypeClientInstalled(context)) {
            goToMarket(context)
            return
        }
        val mySkypeUri = "skype:" + skypeId + "?chat"
        // Create the Intent from our Skype URI.
        val skypeUri = Uri.parse(mySkypeUri)
        val myIntent = Intent(Intent.ACTION_VIEW, skypeUri)

        myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK



        try {
            context.startActivity(myIntent)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Determine whether the Skype for Android client is installed on this device.
     */
    fun isSkypeClientInstalled(myContext: Context): Boolean {
        val myPackageMgr: PackageManager = myContext.getPackageManager()
        try {
            myPackageMgr.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES)
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
        return true
    }

    /**
     * Install the Skype client through the market: URI scheme.
     */
    fun goToMarket(myContext: Context) {
        val marketUri = Uri.parse("market://details?id=com.skype.raider")
        val myIntent = Intent(Intent.ACTION_VIEW, marketUri)
        myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        myContext.startActivity(myIntent)
    }

    fun onClickWhatsApp(phoneNumberWithCountryCode: String) {
        //val phoneNumberWithCountryCode = "+25412345678"
        val message = ""

        startActivity(
                Intent(Intent.ACTION_VIEW,
                        Uri.parse(String.format(getString(R.string.whatsapp_url), phoneNumberWithCountryCode, message))
                )
        )

    }
}