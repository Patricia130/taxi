package com.newtaxiprime.user.taxiapp.sidebar

/**
 * @package com.newtaxiprime.user
 * @subpackage Side_Bar
 * @category Setting
 * @author Seen Technologies
 *
 */

import android.app.Dialog
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.SystemClock
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.hbb20.CountryCodePicker
import com.squareup.picasso.Picasso
import com.newtaxiprime.user.R
import com.newtaxiprime.user.appcommon.configs.SessionManager
import com.newtaxiprime.user.appcommon.datamodels.JsonResponse
import com.newtaxiprime.user.appcommon.datamodels.settings.CurrencyListModel
import com.newtaxiprime.user.appcommon.datamodels.settings.CurrencyModelList
import com.newtaxiprime.user.appcommon.interfaces.ApiService
import com.newtaxiprime.user.appcommon.interfaces.ServiceListener
import com.newtaxiprime.user.appcommon.network.AppController
import com.newtaxiprime.user.taxiapp.sidebar.currency.CurrencyModel
import com.newtaxiprime.user.appcommon.utils.CommonKeys
import com.newtaxiprime.user.appcommon.utils.CommonMethods
import com.newtaxiprime.user.appcommon.utils.CommonMethods.Companion.DebuggableLogI
import com.newtaxiprime.user.appcommon.utils.CommonMethods.Companion.DebuggableLogV
import com.newtaxiprime.user.appcommon.utils.CommonMethods.Companion.stopSinchService
import com.newtaxiprime.user.appcommon.utils.Enums
import com.newtaxiprime.user.appcommon.utils.Enums.REQ_CURRENCYLIST
import com.newtaxiprime.user.appcommon.utils.Enums.REQ_GET_RIDER_PROFILE
import com.newtaxiprime.user.appcommon.utils.Enums.REQ_LOGOUT
import com.newtaxiprime.user.appcommon.utils.Enums.REQ_UPDATE_CURR
import com.newtaxiprime.user.appcommon.utils.Enums.REQ_UPDATE_LANG
import com.newtaxiprime.user.appcommon.utils.Enums.REQ_UPDATE_LOCATION
import com.newtaxiprime.user.appcommon.utils.RequestCallback
import com.newtaxiprime.user.appcommon.utils.userchoice.UserChoice
import com.newtaxiprime.user.appcommon.utils.userchoice.UserChoiceSuccessResponse
import com.newtaxiprime.user.appcommon.views.CommonActivity
import com.newtaxiprime.user.appcommon.views.SupportActivityCommon
import com.newtaxiprime.user.taxiapp.sidebar.payment.PaymentPage
import com.newtaxiprime.user.taxiapp.sidebar.referral.ShowReferralOptions
import com.newtaxiprime.user.taxiapp.views.customize.CustomDialog
import com.newtaxiprime.user.taxiapp.views.emergency.EmergencyContact
import com.newtaxiprime.user.taxiapp.views.signinsignup.SigninSignupActivity
import com.newtaxiprime.user.taxiapp.views.splash.SplashActivity
import com.newtaxiprime.user.taxiapp.views.termsPolicy.Privacy
import com.newtaxiprime.user.taxiapp.views.termsPolicy.Terms
import kotlinx.android.synthetic.main.app_activity_add_wallet.*
import kotlinx.android.synthetic.main.app_content_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


/* ************************************************************
   Rider ProfileMenu page contain add home and work location and logout
    *********************************************************** */
class ProfileMenu : CommonActivity(), ServiceListener,UserChoiceSuccessResponse {
    private var mLastClickTime: Long = 0
    lateinit var dialog: AlertDialog
    @Inject
    lateinit var sessionManager: SessionManager
    @Inject
    lateinit var commonMethods: CommonMethods
    @Inject
    lateinit var apiService: ApiService
    @Inject
    lateinit var customDialog: CustomDialog
    @Inject
    lateinit var gson: Gson
    @Inject
    lateinit var userChoice: UserChoice

    lateinit var locationHashMap: HashMap<String, String>
    @BindView(R.id.nametext)
    lateinit var nametext: TextView
// @BindView(R.id.textadress)
    //lateinit var textadress: TextView
    //@BindView(R.id.worktext)
    //lateinit var worktext: TextView

    @BindView(R.id.tv_phone)
    lateinit var tvPhone: TextView
    @BindView(R.id.tv_email)
    lateinit var tvEmail: TextView
    @BindView(R.id.profile_image1)
    lateinit var profile_image: ImageView
    //@BindView(R.id.hometext)
    //lateinit var hometext: TextView
    @BindView(R.id.rlt_editprofile)
    lateinit var rlt_editprofile: RelativeLayout
    @BindView(R.id.rlt_payment)
    lateinit var paymentlayout: RelativeLayout
    @BindView(R.id.referrallayout)
    lateinit var referrallayout: RelativeLayout
    @BindView(R.id.emercontactlayout)
    lateinit var emercontactlayout: RelativeLayout
    @BindView(R.id.manuallayout)
    lateinit var manuallayout: RelativeLayout
    @BindView(R.id.supportlayout)
    lateinit var supportlayout: RelativeLayout
    @BindView(R.id.common_profile)
    lateinit var commonProfile: View
    lateinit var recyclerView1: RecyclerView
    lateinit var languageView: RecyclerView
    lateinit var languagelist: MutableList<CurrencyModel>
    lateinit var currency: String
    lateinit var Language: String
    lateinit var LanguageCode: String
    var currencyList = ArrayList<CurrencyListModel>()
    lateinit var countrys: String
    lateinit var getAddress: String
    lateinit var lat: String
    lateinit var log: String
    lateinit var homegettext: String
    lateinit var workgettext: String
    protected var isInternetAvailable: Boolean = false

    @BindView(R.id.ccp)
    lateinit var ccp: CountryCodePicker
    @OnClick(R.id.back)
    fun back() {
        onBackPressed()
    }





    @OnClick(R.id.rlt_editprofile)
    fun profile() {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }

    @OnClick(R.id.rlt_payment)
    fun payment(){
        if (commonMethods.isOnline(this)) {
            val intent = Intent(this, PaymentPage::class.java)
            intent.putExtra(CommonKeys.TYPE_INTENT_ARGUMENT_KEY, CommonKeys.StatusCode.startPaymentActivityForView)
            startActivityForResult(intent, CommonKeys.ChangePaymentOpetionBeforeRequestCarApi)
            overridePendingTransition(R.anim.ub__slide_in_right, R.anim.ub__slide_out_left)
        } else {
            CommonMethods.showUserMessage(resources.getString(R.string.turnoninternet))
        }
    }

    @OnClick(R.id.referrallayout)
    fun referral(){
        val intent = Intent(this, ShowReferralOptions::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.ub__slide_in_right, R.anim.ub__slide_out_left)
    }

    @OnClick(R.id.emercontactlayout)
    fun emergency(){
        val intent = Intent(this, EmergencyContact::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.ub__slide_in_right, R.anim.ub__slide_out_left)
    }

    @OnClick(R.id.manuallayout)
    fun manual(){
        if (!TextUtils.isEmpty(sessionManager.adminContact)) {
            createAdminpopup(1)
        } else {
            createAdminpopup(0)
        }
    }

    @OnClick(R.id.supportlayout)
    fun support(){
        val intent = Intent(this, SupportActivityCommon::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.ub__slide_in_right, R.anim.ub__slide_out_left)
    }

    @OnClick(R.id.privacylayout)
    fun privacy() {
        val intent = Intent(this, Privacy::class.java)
        startActivity(intent)
    }

    @OnClick(R.id.termslayout)
    fun terms() {
        val intent = Intent(this, Terms::class.java)

        startActivity(intent)
    }

    @OnClick(R.id.logoutlayout)
    fun logOut() {
        val dialog2 = Dialog(this@ProfileMenu)
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog2.setContentView(R.layout.dialog_logout)

        val cancel = dialog2.findViewById<View>(R.id.signout_cancel) as TextView // set the custom dialog components - text, image and button
        val signout = dialog2.findViewById<View>(R.id.signout_signout) as TextView

        cancel.setOnClickListener { dialog2.dismiss() } // if button is clicked, close the custom dialog

        signout.setOnClickListener {
            dialog2.dismiss()
            if (!isInternetAvailable) {
                commonMethods.showMessage(this@ProfileMenu, dialog, resources.getString(R.string.no_connection))
            } else {
                logout()
            }
        }
        dialog2.show()
    }

    /**
     * Home address text clicked
     */
    /**@OnClick(R.id.hometext)
    fun hometext() {
    val intent = Intent(this, AddHome::class.java)
    intent.putExtra("settinghome", "settinghome")
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    startActivity(intent)
    finish()
    }

    @OnClick(R.id.home_lay)
    fun AddHome() {
    hometext()
    }**/
    private fun createAdminpopup(type: Int) {
        val builder1 = AlertDialog.Builder(this)
        if (type == 1) {
            val ReverseNumber = StringBuilder(sessionManager.adminContact)

            builder1.setTitle(resources.getString(R.string.dial, sessionManager.adminContact))
            builder1.setMessage(resources.getString(R.string.contact_admin_for_manual_booking))
            builder1.setCancelable(false)

            builder1.setPositiveButton(resources.getString(R.string.call)) { dialog, _ ->
                dialog.cancel()
                val callnumber = sessionManager.adminContact
                val intent2 = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$callnumber"))
                startActivity(intent2)
            }

            builder1.setNegativeButton(resources.getString(R.string.cancel_s)) { dialog, _ -> dialog.cancel() }
        } else {
            builder1.setMessage(resources.getString(R.string.no_contact_found))
            builder1.setCancelable(false)

            builder1.setPositiveButton(resources.getString(R.string.ok_c)) { dialog, _ -> dialog.cancel() }
        }

        val alert11 = builder1.create()
        if (alert11.window != null)
            alert11.window!!.attributes.windowAnimations = R.style.SlidingDialogAnimation
        alert11.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_menu)
        ButterKnife.bind(this)
        AppController.appComponent.inject(this)
        isInternetAvailable = commonMethods.isOnline(applicationContext)
        dialog = commonMethods.getAlertDialog(this)


        /**Commmon Header Text View */
        commonMethods.setheaderText(resources.getString(R.string.account),common_header)
        /**
         * To change home and work address
         */



        val bottomUp = AnimationUtils.loadAnimation(this,
            R.anim.bottom_up_lay)
        val rlParentLay = findViewById<View>(R.id.parent_lay) as ViewGroup
        //rlParentLay.startAnimation(bottomUp)
        rlParentLay.visibility = View.VISIBLE






        /**
         * Change currency button clicked
         */


    }

    public override fun onDestroy() {
        super.onDestroy()
        //ANDROID_HTTP_CLIENT.close()
    }

    /**
     * Back button to close
     */
    override fun onBackPressed() {
        //super.onBackPressed();
        finish()
        overridePendingTransition(R.anim.ub__slide_in_left, R.anim.ub__slide_out_right)
    }

    /**
     * Clear application data while logout
     */
    fun clearApplicationData() {
        val cache = cacheDir
        val appDir = File(cache.parent)
        if (appDir.exists()) {
            val children = appDir.list()
            for (s in children) {
                if ("lib" != s) {
                    deleteDir(File(appDir, s))
                    DebuggableLogI("TAG", "**************** File /data/data/APP_PACKAGE/$s DELETED *******************")

                    // clearApplicationData();
                }
            }
        }
    }

    public override fun onResume() {
        super.onResume()
        Language = sessionManager.language!!
//  language.text = Language
        val profiledetails = sessionManager.profileDetail
        DebuggableLogV("ProfileArraydetail", "ProfileArraydetail$profiledetails")
        if (!TextUtils.isEmpty(profiledetails)) {
            loaddata(profiledetails.toString())
        } else {

            /**
             * Get Rider profile
             */
            if (!isInternetAvailable) {
                //commonMethods.showMessage(this, dialog, getString(R.string.no_connection));
                commonMethods.showMessage(this@ProfileMenu, dialog, resources.getString(R.string.no_connection))
            } else {
                getRiderDetails()
            }

        }

    }

    override fun onSuccess(jsonResp: JsonResponse, data: String) {
        commonMethods.hideProgressDialog()
        if (!jsonResp.isOnline) {
            if (!TextUtils.isEmpty(data))
                commonMethods.showMessage(this, dialog, data)
            return
        }
        when (jsonResp.requestCode) {

            // Get Rider Profile
            REQ_GET_RIDER_PROFILE -> {
                commonMethods.hideProgressDialog()
                if (jsonResp.isSuccess) {
                    onSuccessGetProf(jsonResp)
                } else if (!TextUtils.isEmpty(jsonResp.statusMsg)) {
                    commonMethods.showMessage(this, dialog, jsonResp.statusMsg)
                }
            }

            // Update Rider Location
            REQ_UPDATE_LOCATION -> if (jsonResp.isSuccess) {
                commonMethods.hideProgressDialog()
                val statuscode = jsonResp.statusCode
                if (statuscode.matches("1".toRegex())) {
                    getRiderDetails()
                }
            } else if (!TextUtils.isEmpty(jsonResp.statusMsg)) {
                commonMethods.hideProgressDialog()
                commonMethods.showMessage(this, dialog, jsonResp.statusMsg)
            }
            REQ_CURRENCYLIST -> if (jsonResp.isSuccess) {
                commonMethods.hideProgressDialog()
                onSuccessGetCurr(jsonResp)
            } else if (!TextUtils.isEmpty(jsonResp.statusMsg)) {
                commonMethods.hideProgressDialog()
                commonMethods.showMessage(this, dialog, jsonResp.statusMsg)
            }
            REQ_UPDATE_CURR -> if (jsonResp.isSuccess) {
                commonMethods.hideProgressDialog()
                //Integer wallet_amount = (Integer) commonMethods.getJsonValue(jsonResp.getStrResponse(), "wallet_amount", String.class);

                var wallet_amount = commonMethods.getJsonValue(jsonResp.strResponse, "wallet_amount", String::class.java) as String
                val result = java.lang.Float.parseFloat(wallet_amount)
                val result1 = result.toInt()
                wallet_amount = Integer.toString(result1)

                println("wallet amout : $wallet_amount")
                sessionManager.walletAmount = wallet_amount
            } else if (!TextUtils.isEmpty(jsonResp.statusMsg)) {
                commonMethods.hideProgressDialog()
                commonMethods.showMessage(this, dialog, jsonResp.statusMsg)
            }
            REQ_UPDATE_LANG -> if (jsonResp.isSuccess) {
                DebuggableLogI("Settings", "Language")
                commonMethods.hideProgressDialog()
            } else if (!TextUtils.isEmpty(jsonResp.statusMsg)) {
                commonMethods.hideProgressDialog()
                commonMethods.showMessage(this, dialog, jsonResp.statusMsg)
            }
            REQ_LOGOUT -> if (jsonResp.isSuccess) {
                commonMethods.hideProgressDialog()
                onSuccessLogout()
            } else if (!TextUtils.isEmpty(jsonResp.statusMsg)) {
                commonMethods.hideProgressDialog()
                commonMethods.showMessage(this, dialog, jsonResp.statusMsg)
            }
            else -> commonMethods.hideProgressDialog()
        }
    }

    override fun onFailure(jsonResp: JsonResponse, data: String) {
        if (!TextUtils.isEmpty(jsonResp.statusMsg)) {
            commonMethods.hideProgressDialog()
            commonMethods.showMessage(this, dialog, jsonResp.statusMsg)
        }
    }

    fun onSuccessGetProf(jsonResp: JsonResponse) {
        if (jsonResp.statusCode.matches("1".toRegex())) {
            sessionManager.profileDetail = jsonResp.strResponse
            loaddata(jsonResp.strResponse)
        } else if (jsonResp.statusCode.matches("2".toRegex())) {
            commonMethods.showMessage(this, dialog, jsonResp.statusMsg)

        }
    }


    fun onSuccessLogout() {
        val lang = sessionManager.language
        val langCode = sessionManager.languageCode
        val clientId = sessionManager.appleLoginClientId
        stopSinchService(this)

        sessionManager.clearToken()
        sessionManager.clearAll()
        sessionManager.clearPaymentType()
        CommonKeys.isFirstSetpaymentMethod=false
        Firebase.auth.signOut()

        // Clear local saved data
        clearApplicationData() // Clear cache data

        sessionManager.language = lang
        sessionManager.languageCode = langCode
        sessionManager.appleLoginClientId = clientId

        val intent = Intent(this, SigninSignupActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    /**
     * Show rider details and address
     */
    fun loaddata(profiledetails: String) {
        try {
            val jsonObj = JSONObject(profiledetails)
            val first_name = jsonObj.getString("first_name")
            val last_name = jsonObj.getString("last_name")
            val mobile_number = jsonObj.getString("mobile_number")
            val email_id = jsonObj.getString("email_id")
            val user_thumb_image = jsonObj.getString("profile_image")
            val country_code = jsonObj.getString("country_code")

            ccp.setCountryForNameCode(country_code)
            ccp.setCcpClickable(false)
            tvPhone.setText(mobile_number)
            tvEmail.setText(email_id)
            nametext.text = "$first_name $last_name"
            Picasso.get().load(user_thumb_image).into(profile_image)

            val home = jsonObj.getString("home")
            val work = jsonObj.getString("work")



        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
    /**
     * Logout user API called
     */
    fun logout() {
        commonMethods.showProgressDialog(this)
        apiService.logOut(sessionManager.accessToken!!, sessionManager.type!!).enqueue(RequestCallback(REQ_LOGOUT, this))

    }

    /**
     * Get rider details
     */
    fun getRiderDetails() {
        //        commonMethods.showProgressDialog(this);
        apiService.getRiderProfile(commonMethods.getRiderProfile(false,null)).enqueue(RequestCallback(REQ_GET_RIDER_PROFILE, this))

    }

    /**
     * Get Rider home and work address
     */
    fun updateRiderLoc() {
        commonMethods.showProgressDialog(this)
        apiService.uploadRiderLocation(locationHashMap).enqueue(RequestCallback(REQ_UPDATE_LOCATION, this))
    }



    private fun loadlang() {
        val languages = resources.getStringArray(R.array.language)
        val langCode = resources.getStringArray(R.array.languageCode)
        for (i in languages.indices) {
            val listdata = CurrencyModel()
            listdata.currencyName = languages[i]
            listdata.currencySymbol = langCode[i]
            languagelist.add(listdata)

        }
    }

    fun setLocale(lang: String) {
        val myLocale = Locale(lang)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
    }

    // Get currency list from API
    fun currencyList() {
        commonMethods.showProgressDialog(this)
        apiService.currencyList(sessionManager.accessToken!!).enqueue(RequestCallback(REQ_CURRENCYLIST, this))
    }

    fun onSuccessGetCurr(jsonResp: JsonResponse) {
        val currencyModel = gson.fromJson(jsonResp.strResponse, CurrencyModelList::class.java)
        val currencyListModel = currencyModel.currencyList
        userChoice.getUserCurrency(this,currencyListModel,Enums.USER_CHOICE_CURRENCY,this)
        /*currencyList.clear()
        currencyList.addAll(currencyListModel)
        currencyListAdapter.notifyDataChanged()*/

    }

    // After Get currency to Update currency
    fun updateCurrency() {
        commonMethods.showProgressDialog(this)
        currency = sessionManager.currencyCode!!
        // currency_code.text =sessionManager.currencySymbol +" "+ currency
        apiService.updateCurrency(sessionManager.accessToken!!, currency).enqueue(RequestCallback(REQ_UPDATE_CURR, this))

    }

    fun updateLanguage() {
        // commonMethods.showProgressDialog(this);
        Language = sessionManager.language!!
        LanguageCode = sessionManager.languageCode!!

        //language.text = Language
        apiService.updateLanguage(sessionManager.accessToken!!, LanguageCode).enqueue(RequestCallback(REQ_UPDATE_LANG, this))

    }

    companion object {

        var langclick: Boolean? = false
        var currencyclick: Boolean? = false
        var alertDialogStores1: android.app.AlertDialog? = null
        var alertDialogStores2: android.app.AlertDialog? = null

        /**
         * delete cache data while delete
         */
        fun deleteDir(dir: File?): Boolean {
            if (dir != null && dir.isDirectory) {
                val children = dir.list()
                if (children != null) {
                    for (i in children.indices) {
                        val success = deleteDir(File(dir, children[i]))
                        if (!success) {
                            return false
                        }
                    }
                }
            }

            return dir!!.delete()
        }
    }

    override fun onSuccessUserSelected(type: String?, userChoiceData: String?, userChoiceCode: String?) {
        if (type.equals(Enums.USER_CHOICE_LANGUAGE)){
            val langocde = sessionManager.languageCode
            val lang = sessionManager.language
            //language.text = lang
            updateLanguage()
            setLocale(langocde!!)
            CommonKeys.isFirstSetpaymentMethod=false
            recreate()
            val intent = Intent(this@ProfileMenu, SplashActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        else if (type.equals(Enums.USER_CHOICE_CURRENCY)){
            currency = sessionManager.currencyCode!!
            //currency_code.text =sessionManager.currencySymbol +" "+ currency
            updateCurrency()
        }
    }


}