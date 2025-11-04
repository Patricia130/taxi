package com.newtaxiprime.user.appcommon.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.newtaxiprime.user.R
import com.newtaxiprime.user.appcommon.configs.SessionManager
import com.newtaxiprime.user.appcommon.datamodels.Banner
import com.newtaxiprime.user.appcommon.network.AppController
import com.newtaxiprime.user.appcommon.utils.CommonMethods
import javax.inject.Inject


class BannerAdapter(private val context: Context, private val bannerList: ArrayList<Banner>, public var onClickListener: OnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        AppController.appComponent.inject(this)
    }

    lateinit var dialog: AlertDialog

    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var commonMethods: CommonMethods

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.app_banner_layout_service, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int = bannerList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView
        (holder as ViewHolder).bindData(bannerList[position], position)
    }


    interface OnClickListener {
        fun onClick(pos: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(banner: Banner, position: Int) {
            tvBannerName.setText(banner.name)
            Picasso.get().load(banner.image).into(ivBanner)

            rltBanner.setOnClickListener {
                onClickListener.onClick(position)
            }
        }


        var tvBannerName: TextView
        var rltBanner: RelativeLayout
        var ivBanner: ImageView


        init {
            tvBannerName = itemView.findViewById<View>(R.id.tv_banner_name) as TextView
            rltBanner = itemView.findViewById<View>(R.id.rlt_banner) as RelativeLayout
            ivBanner = itemView.findViewById<View>(R.id.cv_banner) as ImageView

        }
    }


}

