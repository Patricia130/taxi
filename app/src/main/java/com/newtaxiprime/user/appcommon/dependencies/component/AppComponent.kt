package com.newtaxiprime.user.appcommon.dependencies.component

/**
 * @package com.newtaxiprime.user
 * @subpackage dependencies.component
 * @category AppComponent
 * @author Seen Technologies
 *
 */


import com.newtaxiprime.user.taxiapp.ScheduleRideDetailActivity
import com.newtaxiprime.user.taxiapp.adapters.CarDetailsListAdapter

import com.newtaxiprime.user.taxiapp.adapters.PastTripsPaginationAdapter
import com.newtaxiprime.user.taxiapp.adapters.PriceRecycleAdapter
import com.newtaxiprime.user.taxiapp.adapters.UpcomingAdapter
import com.newtaxiprime.user.taxiapp.adapters.UpcomingTripsPaginationAdapter
import com.newtaxiprime.user.appcommon.backgroundtask.ImageCompressAsyncTask
import com.newtaxiprime.user.appcommon.configs.RunTimePermission
import com.newtaxiprime.user.appcommon.configs.SessionManager
import com.newtaxiprime.user.taxiapp.database.AddFirebaseDatabase
import com.newtaxiprime.user.appcommon.dependencies.module.AppContainerModule
import com.newtaxiprime.user.appcommon.dependencies.module.ApplicationModule
import com.newtaxiprime.user.appcommon.dependencies.module.NetworkModule
import com.newtaxiprime.user.appcommon.helper.CommonDialog
import com.newtaxiprime.user.appcommon.drawpolyline.DownloadTask
import com.newtaxiprime.user.appcommon.pushnotification.MyFirebaseInstanceIDService
import com.newtaxiprime.user.appcommon.pushnotification.MyFirebaseMessagingService
import com.newtaxiprime.user.taxiapp.sendrequest.CancelYourTripActivity
import com.newtaxiprime.user.taxiapp.sendrequest.DriverNotAcceptActivity
import com.newtaxiprime.user.taxiapp.sendrequest.DriverRatingActivity
import com.newtaxiprime.user.taxiapp.sendrequest.PaymentAmountPage
import com.newtaxiprime.user.taxiapp.sendrequest.SendingRequestActivity
import com.newtaxiprime.user.taxiapp.sidebar.AddHome
import com.newtaxiprime.user.taxiapp.sidebar.DriverContactActivity
import com.newtaxiprime.user.taxiapp.sidebar.EnRoute
import com.newtaxiprime.user.taxiapp.sidebar.FareBreakdown
import com.newtaxiprime.user.taxiapp.sidebar.Profile
import com.newtaxiprime.user.taxiapp.sidebar.Setting
import com.newtaxiprime.user.taxiapp.sidebar.payment.*
import com.newtaxiprime.user.taxiapp.sidebar.referral.ShowReferralOptions
import com.newtaxiprime.user.taxiapp.sidebar.trips.Past
import com.newtaxiprime.user.taxiapp.sidebar.trips.Receipt
import com.newtaxiprime.user.taxiapp.sidebar.trips.TripDetails
import com.newtaxiprime.user.taxiapp.sidebar.trips.Upcoming
import com.newtaxiprime.user.taxiapp.sidebar.trips.YourTrips
import com.newtaxiprime.user.appcommon.utils.CommonMethods
import com.newtaxiprime.user.appcommon.utils.RequestCallback
import com.newtaxiprime.user.appcommon.utils.userchoice.UserChoice
import com.newtaxiprime.user.appcommon.views.*
import com.newtaxiprime.user.taxiapp.sidebar.ProfileMenu
import com.newtaxiprime.user.taxiapp.views.addCardDetails.AddCardActivity
import com.newtaxiprime.user.taxiapp.views.facebookAccountKit.FacebookAccountKitActivity
import com.newtaxiprime.user.taxiapp.views.emergency.EmergencyContact
import com.newtaxiprime.user.taxiapp.views.emergency.SosActivity
import com.newtaxiprime.user.taxiapp.views.firebaseChat.ActivityChat
import com.newtaxiprime.user.taxiapp.views.firebaseChat.AdapterFirebaseRecylcerview
import com.newtaxiprime.user.taxiapp.views.firebaseChat.FirebaseChatHandler
import com.newtaxiprime.user.taxiapp.views.main.MainActivity
import com.newtaxiprime.user.taxiapp.views.main.filter.FeaturesInVehicleAdapter
import com.newtaxiprime.user.taxiapp.views.peakPricing.PeakPricing
import com.newtaxiprime.user.taxiapp.views.search.PlaceSearchActivity
import com.newtaxiprime.user.taxiapp.views.signinsignup.*
import com.newtaxiprime.user.taxiapp.views.splash.SplashActivity
import com.newtaxiprime.user.taxiapp.views.voip.CallProcessingActivity
import com.newtaxiprime.user.taxiapp.views.voip.NewTaxiSinchService

import javax.inject.Singleton

import dagger.Component


/*****************************************************************
 * App Component
 */
@Singleton
@Component(modules = [NetworkModule::class, ApplicationModule::class, AppContainerModule::class])
interface AppComponent {

    fun inject(splashActivity: SplashActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(scheduleRideDetailActivity: ScheduleRideDetailActivity)

    fun inject(sendingRequestActivity: SendingRequestActivity)

    fun inject(driverNotAcceptActivity: DriverNotAcceptActivity)

    fun inject(mainActivity: PlaceSearchActivity)

    fun inject(profileMenu: ProfileMenu)

    fun inject(signin_signup_activity: SigninSignupActivity)

    fun inject(ssResetPassword: SSResetPassword)

    fun inject(ssSocialDetailsActivity: SSRegisterActivity)

    fun inject(driverContactActivity: DriverContactActivity)

    fun inject(addHome: AddHome)

    fun inject(paymentPage: PaymentPage)

    fun inject(paymentAmountPage: PaymentAmountPage)

    fun inject(fareBreakdown: FareBreakdown)

    fun inject(addWalletActivity: AddWalletActivity)

    fun inject(transferMoney: TransferMoney)

    fun inject(promoAmountActivity: PromoAmountActivity)

    fun inject(yourTrips: YourTrips)


    fun inject(tripDetails: TripDetails)

    fun inject(enRoute: EnRoute)

    fun inject(sos_activity: SosActivity)

    fun inject(driverRatingActivity: DriverRatingActivity)

    fun inject(cancelYourTripActivity: CancelYourTripActivity)

    fun inject(commonDialog: CommonDialog)

    fun inject(setting: Setting)

    fun inject(profile: Profile)


    fun inject(emergencyContact: EmergencyContact)

    fun inject(activityChat: ActivityChat)

    fun inject(facebookAccountKitActivity: FacebookAccountKitActivity)

    fun inject(loginActivity: SSLoginActivity)

    fun inject(peakPricing: PeakPricing)

    fun inject(showReferralOptions: ShowReferralOptions)

    fun inject(callProcessingActivity: CallProcessingActivity)

    fun inject(NewTaxiSinchService: NewTaxiSinchService)

    // Fragments
    fun inject(past: Past)

    fun inject(upcoming: Upcoming)

    fun inject(receipt: Receipt)

    // Utilities
    fun inject(runTimePermission: RunTimePermission)

    fun inject(sessionManager: SessionManager)

    fun inject(commonMethods: CommonMethods)

    fun inject(requestCallback: RequestCallback)

    // Adapters

    fun inject(upcomingAdapter: UpcomingAdapter)


    fun inject(promoAmountAdapter: PromoAmountAdapter)

    fun inject(carDetailsListAdapter: CarDetailsListAdapter)

    fun inject(myFirebaseMessagingService: MyFirebaseMessagingService)

    fun inject(myFirebaseInstanceIDService: MyFirebaseInstanceIDService)

    fun inject(firebaseChatHandler: FirebaseChatHandler)

    fun inject(adapterFirebaseRecylcerview: AdapterFirebaseRecylcerview)


    // AsyncTask
    fun inject(imageCompressAsyncTask: ImageCompressAsyncTask)

    fun inject(downloadTask: DownloadTask)

    fun inject(firebaseDatabase: AddFirebaseDatabase)


    fun inject(priceRecycleAdapter: PriceRecycleAdapter)

    fun inject(pastTripsPaginationAdapter: PastTripsPaginationAdapter)

    fun inject(upcomingTripsPaginationAdapter: UpcomingTripsPaginationAdapter)
     fun inject(addCardActivity: AddCardActivity)
    fun inject(paymentMethodAdapter: PaymentMethodAdapter)
    fun inject(featuresInVehicleAdapter: FeaturesInVehicleAdapter)

    fun inject(supportActivityCommon: SupportActivityCommon)

    fun inject(supportAdapter: SupportAdapter)

    fun inject(bannerActivityCommon: BannerActivityCommon)

    fun inject(bannerAdapter: BannerAdapter)

    fun inject(paymentWebViewActivity: PaymentWebViewActivity)
    fun inject(paytmPaymentWebViewActivity: PaytmPaymentWebViewActivity)
    fun inject(mpesaPaymentWebViewActivity: MpesaPaymentWebViewActivity)
    fun inject(flutterwavePaymentWebViewActivity: FlutterwaveWebViewActivity)
    fun inject(razorpayWebViewActivity: RazorpayWebViewActivity)
    fun inject(paystackWebViewActivity: PaystackWebViewActivity)
    fun inject(mollieWebViewActivity: MollieWebViewActivity)

    fun inject(commonActivity: CommonActivity)

    fun inject(userChoice: UserChoice)

}
