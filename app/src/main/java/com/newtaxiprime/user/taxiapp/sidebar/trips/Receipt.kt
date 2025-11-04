package com.newtaxiprime.user.taxiapp.sidebar.trips

/**
 * @package com.newtaxiprime.user
 * @subpackage Side_Bar.trips
 * @category Receipt
 * @author Seen Technologies
 * 
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.newtaxiprime.user.R
import com.newtaxiprime.user.taxiapp.adapters.PriceRecycleAdapter
import com.newtaxiprime.user.appcommon.configs.SessionManager
import com.newtaxiprime.user.appcommon.network.AppController
import com.newtaxiprime.user.taxiapp.sidebar.trips.TripDetails.Companion.tripDetailsModel
import javax.inject.Inject


/* ************************************************************
    Receipt view page tap fragment
    *********************************************************** */
class Receipt : Fragment() {


    @Inject
    lateinit var sessionManager: SessionManager
    @BindView(R.id.basefare_amount)
    lateinit var basefare_amount: TextView
    @BindView(R.id.distance_fare)
    lateinit var distance_fare: TextView
    @BindView(R.id.time_fare)
    lateinit var time_fare: TextView
    @BindView(R.id.fee)
    lateinit var fee: TextView
    @BindView(R.id.totalamount)
    lateinit var totalamount: TextView
    @BindView(R.id.walletamount)
    lateinit var walletamount: TextView
    @BindView(R.id.promoamount)
    lateinit var promoamount: TextView
    @BindView(R.id.payableamount)
    lateinit var payableamount: TextView
    @BindView(R.id.walletamountlayout)
    lateinit var walletamountlayout: RelativeLayout
    @BindView(R.id.promoamountlayout)
    lateinit var promoamountlayout: RelativeLayout
    @BindView(R.id.payableamountlayout)
    lateinit var payableamountlayout: RelativeLayout
    @BindView(R.id.rvPrice)
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.app_activity_receipt, container, false)
        ButterKnife.bind(this, rootView)
        AppController.appComponent.inject(this)

        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        val invoiceModels = tripDetailsModel.riders.get(0).invoice
        val adapter = PriceRecycleAdapter(requireActivity(), invoiceModels)
        recyclerView.adapter = adapter

        /**
         * Show the receipt details for the trip
         */

        return rootView
    }
}
